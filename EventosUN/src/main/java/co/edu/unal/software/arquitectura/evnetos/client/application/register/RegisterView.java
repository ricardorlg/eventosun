package co.edu.unal.software.arquitectura.evnetos.client.application.register;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.rhemsolutions.client.GXTPopupViewWithUiHandlers;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextField;

public class RegisterView extends
		GXTPopupViewWithUiHandlers<RegisterUiHandlers> implements
		RegisterPresenter.MyView {
	public interface Binder extends UiBinder<Widget, RegisterView> {
	}

	@UiField
	TextField nombres;
	@UiField
	TextField apellidos;
	@UiField
	TextField email;
	@UiField
	TextField username;
	@UiField
	NumberField<Integer> telefono;
	@UiField
	PasswordField password;
	@UiField
	TextButton registrar;
	@UiField
	TextButton cancel;
	@UiField(provided = true)
	NumberPropertyEditor<Integer> integerPropertyEditor = new NumberPropertyEditor.IntegerPropertyEditor();
	@UiField(provided = true)
	NumberFormat numberFormat = NumberFormat.getFormat("0");

	@Inject
	RegisterView(Binder binder, EventBus eventBus) {
		super(eventBus);
		initWidget(binder.createAndBindUi(this));

	}

	@UiHandler("registrar")
	public void registrarUsuario(SelectEvent e) {
		if (getUiHandlers() != null) {
			getUiHandlers().doRegister();
		}
	}

	@UiHandler("cancel")
	public void cancelRegister(SelectEvent e) {
		if (getUiHandlers() != null) {
			getUiHandlers().cancel();
		}
	}

	@Override
	public void hide() {
		nombres.clear();
		apellidos.clear();
		email.clear();
		telefono.clear();
		username.clear();
		password.clear();
		super.hide();
	}

	public TextField getNombres() {
		return nombres;
	}

	public TextField getApellidos() {
		return apellidos;
	}

	public TextField getEmail() {
		return email;
	}

	public TextField getUsername() {
		return username;
	}

	public NumberField<Integer> getTelefono() {
		return telefono;
	}

	public PasswordField getPassword() {
		return password;
	}

}
