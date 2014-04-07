package co.edu.unal.software.arquitectura.evnetos.client.application.register;

import java.util.Arrays;

import co.edu.unal.software.arquitectura.evnetos.client.resources.Resources;
import co.edu.unal.software.arquitectura.evnetos.shared.util.UserRole;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.rhemsolutions.client.GXTPopupViewWithUiHandlers;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.data.shared.StringLabelProvider;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.validator.MaxLengthValidator;
import com.sencha.gxt.widget.core.client.form.validator.MinLengthValidator;
import com.sencha.gxt.widget.core.client.form.validator.RegExValidator;

public class RegisterView extends
		GXTPopupViewWithUiHandlers<RegisterUiHandlers> implements
		RegisterPresenter.MyView {

	private TextField firstName;
	private Window window;
	private FormPanel form;
	private TextField lastName;
	private TextField email;
	private TextField telefono;
	private TextField userName;
	private PasswordField password;
	private SimpleComboBox<UserRole> roles;
	private Resources resources;
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";
	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";

	public Widget asWidget() {

		return window;
	}

	@Inject
	RegisterView(EventBus eventBus, final Resources resources) {
		super(eventBus);
		this.resources = resources;
		createForm1();
	}

	private void createForm1() {
		window = new Window();
		window.setHeadingText("Registro de Usuarios");
		window.setBodyStyle("background: none; padding: 15px");
		window.setWidth(400);
		window.setModal(true);
		window.setActive(true);
		window.setBodyBorder(true);
		window.setShadow(true);
		window.setButtonAlign(BoxLayoutPack.CENTER);
		form = new FormPanel();

		VerticalLayoutContainer p = new VerticalLayoutContainer();
		form.add(p);

		firstName = new TextField();
		firstName.setAllowBlank(false);
		firstName.setEmptyText("Ingresa tu Nombre...");
		firstName.addValidator(new MinLengthValidator(3));
		firstName.addValidator(new MaxLengthValidator(30));
		p.add(new FieldLabel(firstName, "*Nombres"), new VerticalLayoutData(1,
				-1));
		lastName = new TextField();
		lastName.setEmptyText("Ingresa tu Nombre...");
		lastName.setAllowBlank(true);

		p.add(new FieldLabel(lastName, "Apellidos"), new VerticalLayoutData(1,
				-1));
		email = new TextField();
		email.setAllowBlank(false);
		email.addValidator(new RegExValidator(EMAIL_PATTERN, "Invalid Email"));
		email.setEmptyText("Ingresa tu correo");
		p.add(new FieldLabel(email, "*Email"), new VerticalLayoutData(1, -1));

		telefono = new TextField();
		telefono.setAllowBlank(true);
		telefono.addValidator(new RegExValidator("\\d{7,10}",
				"Telefono Invalido"));
		telefono.setEmptyText("Ingresa tu telefono");
		p.add(new FieldLabel(telefono, "Telefono"), new VerticalLayoutData(1,
				-1));
		userName = new TextField();
		userName.setAllowBlank(false);
		userName.addValidator(new RegExValidator(USERNAME_PATTERN,
				"Nombre de Usuario Invalido"));
		userName.setEmptyText("Ingresa tu Nombre de Usuario");
		p.add(new FieldLabel(userName, "*UserName"), new VerticalLayoutData(1,
				-1));

		password = new PasswordField();
		password.setAllowBlank(false);
		password.addValidator(new RegExValidator(PASSWORD_PATTERN,
				"Password invalido"));
		p.add(new FieldLabel(password, "*Password"), new VerticalLayoutData(1,
				-1));
		roles = new SimpleComboBox<UserRole>(
				new StringLabelProvider<UserRole>());
		roles.add(Arrays.asList(UserRole.values()));
		roles.setEditable(false);
		roles.setAllowBlank(false);
		roles.setForceSelection(true);
		roles.setTriggerAction(TriggerAction.ALL);
		roles.setEmptyText("Selecciona un Rol");
		p.add(new FieldLabel(roles, "*Rol"), new VerticalLayoutData(1, -1));
		TextButton registerButton = new TextButton("Registrar",
				resources.accept());
		registerButton.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				if (!form.isValid()) {
					return;
				}
				if (getUiHandlers() != null) {
					getUiHandlers().doRegister();
				}

			}
		});
		TextButton cancelButton = new TextButton("Cancelar", resources.delete());
		cancelButton.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				form.reset();
				if (getUiHandlers() != null) {
					getUiHandlers().cancel();
				}
			}
		});
		window.addButton(registerButton);
		window.addButton(cancelButton);
		window.add(form);
	}

	@Override
	public TextField getNombres() {
		// TODO Auto-generated method stub
		return firstName;
	}

	@Override
	public TextField getApellidos() {
		// TODO Auto-generated method stub
		return lastName;
	}

	@Override
	public TextField getEmail() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public TextField getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public TextField getTelefono() {
		// TODO Auto-generated method stub
		return telefono;
	}

	@Override
	public PasswordField getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public SimpleComboBox<UserRole> getRoles() {
		return roles;
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		super.hide();
		form.reset();
	}

}
