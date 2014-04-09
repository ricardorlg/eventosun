package co.edu.unal.software.arquitectura.evnetos.client.application.register;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import co.edu.unal.software.arquitectura.evnetos.client.application.register.login.LoginModule;

public class RegisterModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		install(new LoginModule());
		bindPresenterWidget(RegisterPresenter.class,
				RegisterPresenter.MyView.class, RegisterView.class);
	}
}
