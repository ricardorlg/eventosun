package co.edu.unal.software.arquitectura.evnetos.client.application.links;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class LinksModule extends AbstractPresenterModule {
  @Override
  protected void configure() {
    bindPresenter(LinksPresenter.class, LinksPresenter.MyView.class, LinksView.class, LinksPresenter.MyProxy.class);
  }
}
