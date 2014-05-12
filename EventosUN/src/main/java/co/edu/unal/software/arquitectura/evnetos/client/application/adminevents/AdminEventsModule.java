package co.edu.unal.software.arquitectura.evnetos.client.application.adminevents;

import co.edu.unal.software.arquitectura.evnetos.client.application.adminevents.addevent.AddEventModule;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class AdminEventsModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		install(new AddEventModule());
		bindPresenter(AdminEventsPresenter.class,
				AdminEventsPresenter.MyView.class, AdminEventsView.class,
				AdminEventsPresenter.MyProxy.class);
	}
}
