package co.edu.unal.software.arquitectura.evnetos.client.application.menu;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import co.edu.unal.software.arquitectura.evnetos.client.application.menu.serverresponse.ServerResponseModule;

public class MenuModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		install(new ServerResponseModule());
		bindSingletonPresenterWidget(MenuPresenter.class,
				MenuPresenter.MyView.class, MenuView.class);
	}
}
