package co.edu.unal.software.arquitectura.evnetos.client.application;

import co.edu.unal.software.arquitectura.evnetos.client.application.home.HomeModule;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import co.edu.unal.software.arquitectura.evnetos.client.application.error.ErrorModule;
import co.edu.unal.software.arquitectura.evnetos.client.application.north.NorthModule;
import co.edu.unal.software.arquitectura.evnetos.client.application.east.EastModule;
import co.edu.unal.software.arquitectura.evnetos.client.application.west.WestModule;
import co.edu.unal.software.arquitectura.evnetos.client.application.links.LinksModule;

public class ApplicationModule extends AbstractPresenterModule {
  @Override
  protected void configure() {
    install(new LinksModule());
    install(new WestModule());
    install(new EastModule());
    install(new NorthModule());
    install(new ErrorModule());
    install(new HomeModule());

    bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
        ApplicationPresenter.MyProxy.class);
  }
}
