package co.edu.unal.software.arquitectura.evnetos.client.application.home;

import co.edu.unal.software.arquitectura.evnetos.client.application.ApplicationPresenter;
import co.edu.unal.software.arquitectura.evnetos.client.place.NameTokens;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.CurrentUserDto;

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
		void renderVideo();
	}

	@ProxyStandard
	@NameToken(NameTokens.home)
	public interface MyProxy extends ProxyPlace<HomePagePresenter> {
	}

	private CurrentUserDto currentUser;

	@Inject
	public HomePagePresenter(EventBus eventBus, MyView view, MyProxy proxy,
			CurrentUserDto currentUserDto) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_LayoutPresenter);
		this.currentUser = currentUserDto;
	}

}
