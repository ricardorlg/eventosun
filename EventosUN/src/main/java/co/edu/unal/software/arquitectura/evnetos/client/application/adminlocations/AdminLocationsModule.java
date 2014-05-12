package co.edu.unal.software.arquitectura.evnetos.client.application.adminlocations;

import co.edu.unal.software.arquitectura.evnetos.client.application.adminlocations.addlocation.AddLocationModule;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class AdminLocationsModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		install(new AddLocationModule());
		bindPresenter(AdminLocationsPresenter.class,
				AdminLocationsPresenter.MyView.class, AdminLocationsView.class,
				AdminLocationsPresenter.MyProxy.class);
	}
}
