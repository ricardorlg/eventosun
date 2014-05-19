package co.edu.unal.software.arquitectura.evnetos.client.application.menu.serverresponse;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ServerResponseModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindSingletonPresenterWidget(ServerResponsePresenter.class,
				ServerResponsePresenter.MyView.class, ServerResponseView.class);
	}
}
