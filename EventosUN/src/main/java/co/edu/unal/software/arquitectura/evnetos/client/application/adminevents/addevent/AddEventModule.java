package co.edu.unal.software.arquitectura.evnetos.client.application.adminevents.addevent;

import co.edu.unal.software.arquitectura.evnetos.client.application.adminevents.addevent.eventlocationrelation.EventLocationRelationModule;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class AddEventModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		install(new EventLocationRelationModule());
		bindPresenterWidget(AddEventPresenter.class,
				AddEventPresenter.MyView.class, AddEventView.class);
	}
}
