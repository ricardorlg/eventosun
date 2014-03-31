package co.edu.unal.software.arquitectura.evnetos.client.application.register;

import co.edu.unal.software.arquitectura.evnetos.shared.actions.RegisterAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.RegisterResult;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.PasswordField;
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

		public NumberField<Integer> getTelefono();

		public PasswordField getPassword();
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
		RegisterAction registerAction;
		String nombres = getView().getNombres().getText();
		String apellidos = getView().getApellidos().getText();
		String correo = getView().getEmail().getText();
		String username = getView().getUsername().getText();
		Integer tel = getView().getTelefono().getValue();
		String clave = getView().getPassword().getSelectedText();
		if (tel != null && tel.toString().length() > 7) {
			registerAction = new RegisterAction.Builder(nombres, apellidos,
					correo, username, clave).telefono(tel).build();

		} else {
			registerAction = new RegisterAction(nombres, apellidos, correo,
					username, clave);
		}
		getView().hide();
		dispatcher.execute(registerAction, new AsyncCallback<RegisterResult>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert(caught.getLocalizedMessage());
				
			}

			@Override
			public void onSuccess(RegisterResult result) {
				Info.display("Registro de Usuario", result.getMensaje());
			}
		});
	}

	@Override
	public void cancel() {
		getView().hide();

	}

}
