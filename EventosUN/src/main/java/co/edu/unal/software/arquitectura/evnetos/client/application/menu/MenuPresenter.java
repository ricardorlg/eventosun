package co.edu.unal.software.arquitectura.evnetos.client.application.menu;

import co.edu.unal.software.arquitectura.evnetos.client.application.menu.serverresponse.ServerResponsePresenter;
import co.edu.unal.software.arquitectura.evnetos.client.application.register.RegisterPresenter;
import co.edu.unal.software.arquitectura.evnetos.client.application.register.login.LoginPresenter;
import co.edu.unal.software.arquitectura.evnetos.client.events.LoginEvent;
import co.edu.unal.software.arquitectura.evnetos.client.events.LoginEvent.LoginHandler;
import co.edu.unal.software.arquitectura.evnetos.client.place.NameTokens;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.LoadServerInfoAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.LoadServerInfoResult;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.LogOutAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.LogOutResult;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.CurrentUserDto;
import co.edu.unal.software.arquitectura.evnetos.shared.util.UserRole;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

public class MenuPresenter extends PresenterWidget<MenuPresenter.MyView>
		implements MenuUiHandlers, LoginHandler {
	public interface MyView extends View, HasUiHandlers<MenuUiHandlers> {
		void renderUserMenus();

		void enabledActionsAdminLocations();

		void renderGeneralMenu();

		void enabledActionsAdminEvents();
	}

	private RegisterPresenter registerPresenter;
	private LoginPresenter loginPresenter;
	private final PlaceManager placeManager;
	private final CurrentUserDto currentUser;
	private ServerResponsePresenter responsePresenter;
	private DispatchAsync dispatcher;

	@Inject
	MenuPresenter(EventBus eventBus, MyView view,
			RegisterPresenter registerPresenter, LoginPresenter loginPresenter,
			ServerResponsePresenter responsePresenter,
			PlaceManager placeManager, CurrentUserDto currentUser,
			DispatchAsync dispatcher) {
		super(eventBus, view);
		this.registerPresenter = registerPresenter;
		this.loginPresenter = loginPresenter;
		this.placeManager = placeManager;
		this.currentUser = currentUser;
		this.dispatcher = dispatcher;
		this.responsePresenter = responsePresenter;
		getView().setUiHandlers(this);

		renderMenu();

	}

	@Override
	public void openRegister() {
		// TODO Auto-generated method stub
		addToPopupSlot(registerPresenter);
	}

	@Override
	protected void onBind() {
		// TODO Auto-generated method stub
		super.onBind();

		addVisibleHandler(LoginEvent.getType(), this);
	}

	@Override
	public void openLogin() {

		loginPresenter.getView().hide();

		addToPopupSlot(loginPresenter);
	}

	@Override
	public void eventsClicked() {
		placeManager.revealPlace(new PlaceRequest.Builder().nameToken(
				NameTokens.userHome).build());
	}

	@Override
	public void adminLocationsClicked() {
		placeManager.revealPlace(new PlaceRequest.Builder().nameToken(
				NameTokens.adminLocations).build());

	}

	@Override
	public void onLogin(LoginEvent event) {
		renderMenu();
	}

	private void renderMenu() {
		if (currentUser.isLoggedIn()
				&& currentUser.getRole() == UserRole.LOCATION_ADMIN) {
			getView().enabledActionsAdminLocations();
		} else if (currentUser.isLoggedIn()
				&& currentUser.getRole() == UserRole.EVENT_ADMIN) {
			getView().enabledActionsAdminEvents();
		} else {
			if (this.currentUser.isLoggedIn()) {
				getView().renderGeneralMenu();
			} else {
				getView().renderUserMenus();
			}
		}

	}

	@Override
	public void logOut() {
		dispatcher.execute(new LogOutAction(),
				new AsyncCallback<LogOutResult>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());
					}

					@Override
					public void onSuccess(LogOutResult result) {
						currentUser.reset();
						Cookies.removeCookie("sessionUserid");
						placeManager.revealPlace(new PlaceRequest.Builder()
								.nameToken(NameTokens.home).build());
						getView().renderUserMenus();
					}
				});
	}

	@Override
	public void adminEventsClicked() {
		placeManager.revealPlace(new PlaceRequest.Builder().nameToken(
				NameTokens.adminEvents).build());
	}

	@Override
	public void loadServerInfo() {
		dispatcher.execute(new LoadServerInfoAction(),
				new AsyncCallback<LoadServerInfoResult>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getLocalizedMessage());

					}

					@Override
					public void onSuccess(LoadServerInfoResult result) {
						responsePresenter.setResponse(result
								.getServerResponse());
						addToPopupSlot(responsePresenter);
					}
				});
	}

}
