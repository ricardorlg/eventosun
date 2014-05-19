package co.edu.unal.software.arquitectura.evnetos.client.application.register.login;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PopupViewWithUiHandlers;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.validator.RegExValidator;

public class LoginView extends PopupViewWithUiHandlers<LoginUiHandlers>
		implements LoginPresenter.MyView {
	public interface Binder extends UiBinder<PopupPanel, LoginView> {
	}

	private static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";
	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";
	@UiField
	FormPanel LoginPanel;
	@UiField
	TextField username;
	@UiField
	PasswordField password;
	@UiField
	TextButton loginButton;
	@UiField
	FramedPanel framePanel;
	private Widget widget;

	@Inject
	LoginView(Binder uiBinder, EventBus eventBus) {
		super(eventBus);
		widget = uiBinder.createAndBindUi(this);
		framePanel.setButtonAlign(BoxLayoutPack.START);
		username.focus();
		username.addValidator(new RegExValidator(USERNAME_PATTERN,
				"UserName invalido"));
		password.addValidator(new RegExValidator(PASSWORD_PATTERN,
				"Password invalida"));

	}

	@UiHandler("loginButton")
	public void login(SelectEvent e) {
		doLogin();
	}

	private void doLogin() {
		if (!LoginPanel.isValid()) {
			System.out.println("invalido");
			return;
		}
		if (getUiHandlers() != null) {
			getUiHandlers().login(username.getValue(), password.getValue());
		}
	}

	@Override
	public TextField getUsername() {
		return username;
	}

	@Override
	public void hide() {

		reset();
		LoginPanel.forceLayout();
		super.hide();
	}

	public void reset() {
		LoginPanel.reset();
	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return widget;
	}
}
