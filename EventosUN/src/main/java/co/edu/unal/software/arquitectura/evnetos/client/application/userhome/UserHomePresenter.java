package co.edu.unal.software.arquitectura.evnetos.client.application.userhome;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import co.edu.unal.software.arquitectura.evnetos.client.application.home.HomePagePresenter;
import co.edu.unal.software.arquitectura.evnetos.client.place.NameTokens;

public class UserHomePresenter extends
		Presenter<UserHomePresenter.MyView, UserHomePresenter.MyProxy>
		implements UserHomeUiHandlers {
	interface MyView extends View, HasUiHandlers<UserHomeUiHandlers> {
	}

	@ContentSlot
	public static final Type<RevealContentHandler<?>> SLOT_UserHome = new Type<RevealContentHandler<?>>();

	@NameToken(NameTokens.userHome)
	@ProxyCodeSplit
	public interface MyProxy extends ProxyPlace<UserHomePresenter> {
	}

	@Inject
	public UserHomePresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, HomePagePresenter.SLOT_HomePresenter);

		getView().setUiHandlers(this);
	}

	protected void onBind() {
		super.onBind();
	}

	protected void onHide() {
		super.onHide();
	}

	protected void onReset() {
		super.onReset();
	}

}
