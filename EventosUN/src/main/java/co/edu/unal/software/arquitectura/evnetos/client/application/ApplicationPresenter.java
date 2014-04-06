package co.edu.unal.software.arquitectura.evnetos.client.application;

import co.edu.unal.software.arquitectura.evnetos.client.application.menu.MenuPresenter;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;

public class ApplicationPresenter extends
		Presenter<ApplicationPresenter.MyView, ApplicationPresenter.MyProxy> {
	public interface MyView extends View {
	}

	@ContentSlot
	public static final Type<RevealContentHandler<?>> SLOT_LayoutPresenter = new Type<RevealContentHandler<?>>();
	@ContentSlot
	public static final Type<RevealContentHandler<?>> HeaderSlot = new Type<RevealContentHandler<?>>();

	@ProxyStandard
	public interface MyProxy extends Proxy<ApplicationPresenter> {
	}

	@Inject
	public ApplicationPresenter(EventBus eventBus, MyView view, MyProxy proxy,
			MenuPresenter menuPresenter) {
		super(eventBus, view, proxy, RevealType.Root);
		setInSlot(HeaderSlot, menuPresenter);

	}

	@Override
	protected void onReveal() {
		super.onReveal();

	}
}
