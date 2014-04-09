package co.edu.unal.software.arquitectura.evnetos.client.application;

import co.edu.unal.software.arquitectura.evnetos.client.application.error.ErrorModule;
import co.edu.unal.software.arquitectura.evnetos.client.application.home.HomeModule;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import co.edu.unal.software.arquitectura.evnetos.client.application.menu.MenuModule;
import co.edu.unal.software.arquitectura.evnetos.client.application.register.RegisterModule;
import co.edu.unal.software.arquitectura.evnetos.client.application.userhome.UserHomeModule;
import co.edu.unal.software.arquitectura.evnetos.client.application.adminlocations.AdminLocationsModule;

public class ApplicationModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		install(new AdminLocationsModule());
		install(new UserHomeModule());
		install(new RegisterModule());
		install(new MenuModule());
		install(new ErrorModule());
		install(new HomeModule());

		bindPresenter(ApplicationPresenter.class,
				ApplicationPresenter.MyView.class, ApplicationView.class,
				ApplicationPresenter.MyProxy.class);
	}
}
