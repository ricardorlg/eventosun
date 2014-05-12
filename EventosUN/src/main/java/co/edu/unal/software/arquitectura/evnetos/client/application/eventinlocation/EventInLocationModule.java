package co.edu.unal.software.arquitectura.evnetos.client.application.eventinlocation;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class EventInLocationModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenterWidget(EventInLocationPresenter.class,
				EventInLocationPresenter.MyView.class,
				EventInLocationView.class);
	}
}
