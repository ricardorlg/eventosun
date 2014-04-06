package co.edu.unal.software.arquitectura.evnetos.client.application.register;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class RegisterModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenterWidget(RegisterPresenter.class,
				RegisterPresenter.MyView.class, RegisterView.class);
	}
}
