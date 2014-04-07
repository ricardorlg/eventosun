package co.edu.unal.software.arquitectura.evnetos.client.application.register.login;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;

public class LoginPresenter extends PresenterWidget<LoginPresenter.MyView>
		implements LoginUiHandlers {
	public interface MyView extends PopupView, HasUiHandlers<LoginUiHandlers> {
	}

	@Inject
	LoginPresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);

		getView().setUiHandlers(this);
	}

}
