package co.edu.unal.software.arquitectura.evnetos.client.application.userhome;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.software.arquitectura.evnetos.client.application.ApplicationPresenter;
import co.edu.unal.software.arquitectura.evnetos.client.application.eventinlocation.EventInLocationPresenter;
import co.edu.unal.software.arquitectura.evnetos.client.place.NameTokens;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.LoadAllEventsByLocationAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.LoadAllEventsByLocationResult;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.EventLocationDto;

import com.bradrydzewski.gwt.calendar.client.Appointment;
import com.bradrydzewski.gwt.calendar.client.Calendar;
import com.google.common.base.Strings;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.sencha.gxt.widget.core.client.box.AutoProgressMessageBox;

public class UserHomePresenter extends
		Presenter<UserHomePresenter.MyView, UserHomePresenter.MyProxy>
		implements UserHomeUiHandlers {
	interface MyView extends View, HasUiHandlers<UserHomeUiHandlers> {

		Calendar getCalendar();
	}

	@NameToken(NameTokens.userHome)
	@ProxyCodeSplit
	public interface MyProxy extends ProxyPlace<UserHomePresenter> {
	}

	private DispatchAsync dispatcher;
	private EventInLocationPresenter eventInfo;

	@Inject
	public UserHomePresenter(EventBus eventBus, MyView view, MyProxy proxy,
			DispatchAsync dispatcher, EventInLocationPresenter eventInfo) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_LayoutPresenter);
		this.dispatcher = dispatcher;
		this.eventInfo = eventInfo;
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
		getView().getCalendar().clearAppointments();
		final AutoProgressMessageBox messageBox = new AutoProgressMessageBox(
				"Cargando eventos", "Estamos cargando los eventos de este mes");
		messageBox.auto();
		messageBox.setProgressText("Cargando.....");
		dispatcher.execute(new LoadAllEventsByLocationAction(),
				new AsyncCallback<LoadAllEventsByLocationResult>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getLocalizedMessage());
						messageBox.hide();
					}

					@Override
					public void onSuccess(LoadAllEventsByLocationResult result) {
						createAppoiments(result.getEvents());
						messageBox.hide();
					}
				});

	}

	private void createAppoiments(List<EventLocationDto> events) {
		ArrayList<Appointment> appointments = new ArrayList<Appointment>();
		for (EventLocationDto dto : events) {
			Appointment ap = new Appointment();
			ap.setReadOnly(true);
			ap.setLocation(dto.getLocationName());
			ap.setTitle(dto.getEventName());
			ap.setStart(dto.getStartDate());
			ap.setEnd(dto.getCloseDate());
			if (!Strings.isNullOrEmpty(dto.getEventDescription())) {
				ap.setDescription(dto.getEventDescription());
			}
			appointments.add(ap);
		}

		getView().getCalendar().suspendLayout();
		getView().getCalendar().addAppointments(appointments);
		getView().getCalendar().resumeLayout();

	}

	@Override
	public void loadEventInfo(Appointment appointment) {
		String sTime = DateTimeFormat.getFormat("dd/MM/yyyy - hh:mm a").format(
				appointment.getStart());
		String eTime = DateTimeFormat.getFormat("dd/MM/yyyy - hh:mm a").format(
				appointment.getEnd());
		eventInfo.setData(appointment.getTitle(), appointment.getDescription(),
				appointment.getLocation(), sTime, eTime);
		addToPopupSlot(eventInfo);
		System.out.println(appointment.getLocation());
	}

}
