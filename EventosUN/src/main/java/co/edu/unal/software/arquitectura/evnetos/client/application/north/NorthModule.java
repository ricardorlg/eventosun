package co.edu.unal.software.arquitectura.evnetos.client.application.north;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class NorthModule extends AbstractPresenterModule {
  @Override
  protected void configure() {
    bindPresenter(NorthPresenter.class, NorthPresenter.MyView.class, NorthView.class, NorthPresenter.MyProxy.class);
  }
}
