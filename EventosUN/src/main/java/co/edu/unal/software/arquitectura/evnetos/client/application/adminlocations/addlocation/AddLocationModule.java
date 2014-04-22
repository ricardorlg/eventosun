package co.edu.unal.software.arquitectura.evnetos.client.application.adminlocations.addlocation;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class AddLocationModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenterWidget(AddLocationPresenter.class,
				AddLocationPresenter.MyView.class, AddLocationView.class);
	}
}
