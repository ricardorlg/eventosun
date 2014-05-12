package co.edu.unal.software.arquitectura.evnetos.client.application.adminevents;

import co.edu.unal.software.arquitectura.evnetos.client.application.ApplicationPresenter;
import co.edu.unal.software.arquitectura.evnetos.client.application.adminevents.addevent.AddEventPresenter;
import co.edu.unal.software.arquitectura.evnetos.client.events.AddedEventoEvent;
import co.edu.unal.software.arquitectura.evnetos.client.events.AddedEventoEvent.AddedEventoHandler;
import co.edu.unal.software.arquitectura.evnetos.client.place.NameTokens;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.CurrentUserDto;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.EventDto;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.sencha.gxt.data.shared.ListStore;

public class AdminEventsPresenter extends
		Presenter<AdminEventsPresenter.MyView, AdminEventsPresenter.MyProxy>
		implements AdminEventsUiHandlers, AddedEventoHandler {
	interface MyView extends View, HasUiHandlers<AdminEventsUiHandlers> {

		ListStore<EventDto> getListStore();

		void update();
	}

	@NameToken(NameTokens.adminEvents)
	@ProxyCodeSplit
	public interface MyProxy extends ProxyPlace<AdminEventsPresenter> {
	}

	private AddEventPresenter addEventPresentr;
	private CurrentUserDto currentUser;

	@Inject
	public AdminEventsPresenter(EventBus eventBus, MyView view, MyProxy proxy,
			AddEventPresenter addEventPresenter, CurrentUserDto currentUser) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_LayoutPresenter);
		this.currentUser = currentUser;
		this.addEventPresentr = addEventPresenter;
		getView().setUiHandlers(this);
	}

	@Override
	public void addEvent() {
		addToPopupSlot(addEventPresentr, false);

	}

	@Override
	public void onAddedEvento(AddedEventoEvent event) {
		getView().getListStore().clear();
		getView().getListStore().addAll(currentUser.getEvents());
		getView().update();
	}

	protected void onBind() {
		super.onBind();
		addVisibleHandler(AddedEventoEvent.getType(), this);
	}

	protected void onHide() {
		super.onHide();
	}

	protected void onReset() {
		super.onReset();
		System.out.println(currentUser.getLocations());
		getView().getListStore().clear();
		getView().getListStore().addAll(currentUser.getEvents());
		getView().update();
	}

}
