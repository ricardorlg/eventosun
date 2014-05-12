package co.edu.unal.software.arquitectura.evnetos.client.application.adminevents.addevent.eventlocationrelation;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class EventLocationRelationModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenterWidget(EventLocationRelationPresenter.class,
				EventLocationRelationPresenter.MyView.class,
				EventLocationRelationView.class);
	}
}
