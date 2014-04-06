package co.edu.unal.software.arquitectura.evnetos.client.application.menu;

import co.edu.unal.software.arquitectura.evnetos.client.application.register.RegisterPresenter;

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

	@Inject
	MenuPresenter(EventBus eventBus, MyView view,
			RegisterPresenter registerPresenter) {
		super(eventBus, view);
		this.registerPresenter = registerPresenter;
		getView().setUiHandlers(this);
	}

	@Override
	public void openRegister() {
		// TODO Auto-generated method stub
		addToPopupSlot(registerPresenter);
	}

}
