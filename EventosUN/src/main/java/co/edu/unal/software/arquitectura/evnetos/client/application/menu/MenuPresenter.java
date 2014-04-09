package co.edu.unal.software.arquitectura.evnetos.client.application.menu;

import co.edu.unal.software.arquitectura.evnetos.client.application.register.RegisterPresenter;
import co.edu.unal.software.arquitectura.evnetos.client.application.register.login.LoginPresenter;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class MenuPresenter extends PresenterWidget<MenuPresenter.MyView>
		implements MenuUiHandlers {
	public interface MyView extends View, HasUiHandlers<MenuUiHandlers> {
	}

	private RegisterPresenter registerPresenter;
	private LoginPresenter loginPresenter;

	@Inject
	MenuPresenter(EventBus eventBus, MyView view,
			RegisterPresenter registerPresenter, LoginPresenter loginPresenter) {
		super(eventBus, view);
		this.registerPresenter = registerPresenter;
		this.loginPresenter = loginPresenter;
		getView().setUiHandlers(this);
	}

	@Override
	public void openRegister() {
		// TODO Auto-generated method stub
		addToPopupSlot(registerPresenter);
	}

	@Override
	public void openLogin() {

		loginPresenter.getView().hide();

		addToPopupSlot(loginPresenter);
	}

}
