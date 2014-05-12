package co.edu.unal.software.arquitectura.evnetos.client.gin;

import co.edu.unal.software.arquitectura.evnetos.shared.actions.LoginByCookieAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.LoginByCookieResult;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.CurrentUserDto;

import com.google.common.base.Strings;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Bootstrapper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.sencha.gxt.widget.core.client.box.ProgressMessageBox;

public class BootstrapperImpl implements Bootstrapper {

	private final PlaceManager placeManager;
	private final DispatchAsync dispatcher;
	private final CurrentUserDto currentUserDto;

	@Inject
	public BootstrapperImpl(PlaceManager placeManager,
			DispatchAsync dispatcher, CurrentUserDto currentUserDto) {
		this.placeManager = placeManager;
		this.dispatcher = dispatcher;
		this.currentUserDto = currentUserDto;
	}

	@Override
	public void onBootstrap() {
		doSomeCustomLogic();
	}

	private void doSomeCustomLogic() {
		if (Strings.isNullOrEmpty(Cookies.getCookie("sessionUserid"))) {
			placeManager.revealDefaultPlace();

		} else {
			ProgressMessageBox prg = new ProgressMessageBox("Por favor espere",
					"Cargando sesion de Usuario");
			prg.setProgressText("verificando estado");
			prg.show();
			dispatcher.execute(new LoginByCookieAction(),
					new AsyncCallback<LoginByCookieResult>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert(caught.getMessage());

						}

						@Override
						public void onSuccess(LoginByCookieResult result) {
							if (result.getUser() != null) {
								currentUserDto.copy(result.getUser());
							}

						}
					});
			prg.hide();
			placeManager.revealCurrentPlace();
		}
	}

}
