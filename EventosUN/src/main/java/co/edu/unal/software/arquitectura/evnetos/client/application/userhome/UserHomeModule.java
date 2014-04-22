package co.edu.unal.software.arquitectura.evnetos.client.application.userhome;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class UserHomeModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(UserHomePresenter.class, UserHomePresenter.MyView.class,
				UserHomeView.class, UserHomePresenter.MyProxy.class);
	}
}
