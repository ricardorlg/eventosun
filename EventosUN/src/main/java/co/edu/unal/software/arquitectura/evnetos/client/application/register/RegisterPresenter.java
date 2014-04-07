package co.edu.unal.software.arquitectura.evnetos.client.application.register;

import co.edu.unal.software.arquitectura.evnetos.shared.actions.RegisterAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.RegisterAction.Builder;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.RegisterResult;
import co.edu.unal.software.arquitectura.evnetos.shared.util.UserRole;

import com.google.common.base.Strings;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;

public class RegisterPresenter extends
		PresenterWidget<RegisterPresenter.MyView> implements RegisterUiHandlers {
	public interface MyView extends PopupView,
			HasUiHandlers<RegisterUiHandlers> {
		public TextField getNombres();

		public TextField getApellidos();

		public TextField getEmail();

		public TextField getUsername();

		public TextField getTelefono();

		public PasswordField getPassword();

		public SimpleComboBox<UserRole> getRoles();
	}

	private DispatchAsync dispatcher;

	@Inject
	RegisterPresenter(EventBus eventBus, MyView view,
			final DispatchAsync dispatcher) {
		super(eventBus, view);
		this.dispatcher = dispatcher;
		getView().setUiHandlers(this);
	}

	@Override
	public void doRegister() {
		RegisterAction.Builder registerAction;
		String nombres = getView().getNombres().getText();
		String correo = getView().getEmail().getText();
		String username = getView().getUsername().getText();
		String clave = getView().getPassword().getText();
		UserRole role = getView().getRoles().getValue();
		String tel = getView().getTelefono().getValue();
		String apellidos = getView().getApellidos().getValue();
		registerAction = new Builder(nombres, correo, username, clave, role);
		if (!Strings.isNullOrEmpty(tel)) {
			registerAction.telefono(tel);

		}
		if (!Strings.isNullOrEmpty(apellidos)) {
			registerAction.apellidos(apellidos);
		}
		dispatcher.execute(registerAction.build(),
				new AsyncCallback<RegisterResult>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						if (caught.getMessage().equalsIgnoreCase(
								"UserName en uso")) {
							Window.alert("Username en uso");
						} else {
							Window.alert(caught.getLocalizedMessage());
							getView().hide();
						}

					}

					@Override
					public void onSuccess(RegisterResult result) {
						Info.display("Registro de Usuario", result.getMensaje());
						getView().hide();

					}
				});
	}

	@Override
	public void cancel() {
		getView().hide();

	}

}
