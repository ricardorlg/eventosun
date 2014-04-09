package co.edu.unal.software.arquitectura.evnetos.client.application.adminlocations;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.sencha.gxt.data.shared.ListStore;

import co.edu.unal.software.arquitectura.evnetos.client.application.adminlocations.addlocation.AddLocationPresenter;
import co.edu.unal.software.arquitectura.evnetos.client.application.home.HomePagePresenter;
import co.edu.unal.software.arquitectura.evnetos.client.events.AddedLocationEvent;
import co.edu.unal.software.arquitectura.evnetos.client.events.AddedLocationEvent.AddedLocationHandler;
import co.edu.unal.software.arquitectura.evnetos.client.place.AdminLocationGateKeeper;
import co.edu.unal.software.arquitectura.evnetos.client.place.NameTokens;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.CurrentUserDto;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.LocationDto;

public class AdminLocationsPresenter
		extends
		Presenter<AdminLocationsPresenter.MyView, AdminLocationsPresenter.MyProxy>
		implements AdminLocationsUiHandlers, AddedLocationHandler {
	interface MyView extends View, HasUiHandlers<AdminLocationsUiHandlers> {

		ListStore<LocationDto> getListStore();

		void update();
	}

	private AddLocationPresenter addLocationPresenter;
	private CurrentUserDto currentUser;
	@ContentSlot
	public static final Type<RevealContentHandler<?>> SLOT_AdminLocations = new Type<RevealContentHandler<?>>();

	@NameToken(NameTokens.adminLocations)
	@ProxyCodeSplit
	public interface MyProxy extends ProxyPlace<AdminLocationsPresenter> {
	}

	@Inject
	public AdminLocationsPresenter(EventBus eventBus, MyView view,
			MyProxy proxy, AddLocationPresenter addLocationPresenter,
			CurrentUserDto currentUser) {
		super(eventBus, view, proxy, HomePagePresenter.SLOT_HomePresenter);
		this.addLocationPresenter = addLocationPresenter;
		this.currentUser = currentUser;
		getView().setUiHandlers(this);
	}

	protected void onBind() {
		super.onBind();
		addVisibleHandler(AddedLocationEvent.getType(), this);
	}

	protected void onHide() {
		super.onHide();
	}

	protected void onReset() {
		super.onReset();
		System.out.println(currentUser.getLocations());
		getView().getListStore().clear();
		getView().getListStore().addAll(currentUser.getLocations());
		getView().update();
	}

	@Override
	public void OpenAddLocationEditor() {
		addToPopupSlot(addLocationPresenter);
	}

	@Override
	public void onAddedLocation(AddedLocationEvent event) {
		getView().getListStore().clear();
		getView().getListStore().addAll(currentUser.getLocations());
		getView().update();
	}

}
