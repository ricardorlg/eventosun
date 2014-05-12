package co.edu.unal.software.arquitectura.evnetos.client.application.register;

import co.edu.unal.software.arquitectura.evnetos.client.application.register.login.LoginModule;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class RegisterModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		install(new LoginModule());
		bindPresenterWidget(RegisterPresenter.class,
				RegisterPresenter.MyView.class, RegisterView.class);
	}
}
