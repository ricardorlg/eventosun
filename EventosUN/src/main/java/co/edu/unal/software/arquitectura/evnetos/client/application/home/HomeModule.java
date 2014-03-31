package co.edu.unal.software.arquitectura.evnetos.client.application.home;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class HomeModule extends AbstractPresenterModule {
  @Override
  protected void configure() {
    bindPresenter(HomePagePresenter.class, HomePagePresenter.MyView.class, HomePageView.class,
        HomePagePresenter.MyProxy.class);
  }
}
