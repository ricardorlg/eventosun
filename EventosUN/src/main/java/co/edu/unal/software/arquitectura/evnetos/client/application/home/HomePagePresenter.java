package co.edu.unal.software.arquitectura.evnetos.client.application.home;

import co.edu.unal.software.arquitectura.evnetos.client.application.ApplicationPresenter;
import co.edu.unal.software.arquitectura.evnetos.client.place.NameTokens;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;

public class HomePagePresenter extends
		Presenter<HomePagePresenter.MyView, HomePagePresenter.MyProxy> {
	public interface MyView extends View {
	}

	@ContentSlot
	public static final Type<RevealContentHandler<?>> SLOT_HomePresenter = new Type<RevealContentHandler<?>>();

	@ProxyStandard
	@NameToken(NameTokens.home)
	public interface MyProxy extends ProxyPlace<HomePagePresenter> {
	}

	@Inject
	public HomePagePresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_LayoutPresenter);
	}
}
