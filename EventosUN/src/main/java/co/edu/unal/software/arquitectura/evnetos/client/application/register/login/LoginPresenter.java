package co.edu.unal.software.arquitectura.evnetos.client.application.register.login;

import java.util.Date;

import javax.persistence.NoResultException;

import co.edu.unal.software.arquitectura.evnetos.client.events.LoginEvent;
import co.edu.unal.software.arquitectura.evnetos.client.place.NameTokens;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.LoginAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.LoginResult;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.CurrentUserDto;

import com.google.common.base.Strings;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.sencha.gxt.widget.core.client.info.Info;

public class LoginPresenter extends PresenterWidget<LoginPresenter.MyView>
		implements LoginUiHandlers {
	public interface MyView extends PopupView, HasUiHandlers<LoginUiHandlers> {
	}

	private DispatchAsync dispatcher;
	private CurrentUserDto currentUser;
	private final PlaceManager placeManager;

	@Inject
	LoginPresenter(final EventBus eventBus, final MyView view,
			DispatchAsync dispatcher, CurrentUserDto currentUser,
			PlaceManager placeManager) {
		super(eventBus, view);

		this.dispatcher = dispatcher;
		this.currentUser = currentUser;
		this.placeManager = placeManager;
		getView().setUiHandlers(this);
		System.out.println(this.currentUser);
	}

	public void login(String username, String password) {
		dispatcher.execute(new LoginAction(username, password),
				new AsyncCallback<LoginResult>() {

					@Override
					public void onFailure(Throwable caught) {
						if (caught.getMessage().equals("Usuario no Encontrado")) {
							Window.alert("Usuario no encontrado");

						}
						Window.alert(caught.getMessage());
					}

					@Override
					public void onSuccess(LoginResult result) {
						if (Strings.isNullOrEmpty(result.getResultMessage())) {
							String sessionID = result.getUser().getSessionId();
							final long DURATION = 1000 * 60 * 60 * 24 * 14;
							Date expires = new Date(System.currentTimeMillis()
									+ DURATION);
							Cookies.setCookie("sessionUserid", sessionID,
									expires, null, "/", false);
							System.out.println(result.getUser());
							currentUser.copy(result.getUser());
							getEventBus().fireEvent(new LoginEvent());

							placeManager.revealPlace(new PlaceRequest.Builder()
									.nameToken(NameTokens.userHome).build());
							getView().hide();
						} else {
							Window.alert(result.getResultMessage());
						}
					}
				});
	}
}
