package co.edu.unal.software.arquitectura.evnetos.client.application.adminevents.addevent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.edu.unal.software.arquitectura.evnetos.client.application.adminevents.addevent.eventlocationrelation.EventLocationRelationPresenter;
import co.edu.unal.software.arquitectura.evnetos.client.events.AddedEventoEvent;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.SaveEventAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.SaveEventResult;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.CurrentUserDto;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.EventDto;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.EventLocationDto;

import com.google.common.base.Strings;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;

public class AddEventPresenter extends
		PresenterWidget<AddEventPresenter.MyView> implements AddEventUiHandlers {
	public interface MyView extends PopupView,
			HasUiHandlers<AddEventUiHandlers> {

		TextField getEventName();

		TextArea getEventDescription();

		Date getOpenDateValue();

		Date getCloseDateValue();

	}

	public final static Object slotContent = new Object();
	private Provider<EventLocationRelationPresenter> eventLocationProvider;
	private List<EventLocationRelationPresenter> locationsAsoociated = new ArrayList<EventLocationRelationPresenter>();
	private CurrentUserDto currentUserDto;
	private DispatchAsync dispatcher;

	@Inject
	AddEventPresenter(EventBus eventBus, MyView view,
			Provider<EventLocationRelationPresenter> eventLocationProvider,
			CurrentUserDto currentUserDto, DispatchAsync dispatcher) {
		super(eventBus, view);
		this.currentUserDto = currentUserDto;
		this.eventLocationProvider = eventLocationProvider;
		this.dispatcher = dispatcher;
		getView().setUiHandlers(this);
	}

	@Override
	public void addLocation() {
		EventLocationRelationPresenter widwet = eventLocationProvider.get();
		addToSlot(slotContent, widwet);
		locationsAsoociated.add(widwet);

	}

	@Override
	public void cancel() {
		getView().hide();
		setInSlot(slotContent, null);
		locationsAsoociated.clear();
	}

	@Override
	public void save() {
		boolean canSave = true;
		if (locationsAsoociated.isEmpty()) {
			errorMessage("Debe reservar almenos un espacio para este evento");
			canSave = false;
		} else {
			for (EventLocationRelationPresenter locationRelationPresenter : locationsAsoociated) {
				if (locationRelationPresenter.getDisponible() != null) {
					if (!locationRelationPresenter.getDisponible()
							.booleanValue()) {
						errorMessage("Hay espacios reservados que no estan disponibles en la franja d ehorario elegida");
						canSave = false;
						break;
					}
				} else {
					errorMessage("no ha verificado la disponibilidad de alguno de los espacios reservaados");
					canSave = false;
				}
			}
		}
		if (canSave) {
			List<EventLocationDto> relations = new ArrayList<EventLocationDto>();
			for (EventLocationRelationPresenter locationRelationPresenter : locationsAsoociated) {
				relations.add(locationRelationPresenter.getRelation());
			}
			EventDto eventToCreate = new EventDto();
			eventToCreate.setEventName(getView().getEventName().getValue());
			eventToCreate.setStartDate(getView().getOpenDateValue());
			eventToCreate.setEndDate(getView().getCloseDateValue());
			if (!Strings.isNullOrEmpty(getView().getEventDescription()
					.getValue())) {
				eventToCreate.setEventDescription(getView()
						.getEventDescription().getValue());
			}

			SaveEventAction saveAction = new SaveEventAction(
					currentUserDto.getId(), eventToCreate, relations);

			dispatcher.execute(saveAction,
					new AsyncCallback<SaveEventResult>() {

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							Window.alert(caught.getLocalizedMessage());
						}

						@Override
						public void onSuccess(SaveEventResult result) {
							if (result.getEventCreated().getId() == 0) {
								errorMessage("Se ha producido Un error guardando el evento");
							} else {
								Info.display("Guardar Evento",
										"El evento Ha sido Creado con exito");
								getView().hide();
								currentUserDto.addEvent(result
										.getEventCreated());
								getEventBus().fireEvent(
										new AddedEventoEvent(result
												.getEventCreated()));
							}

						}
					});
		}
	}

	@Override
	public void errorMessage(String errorMessage) {
		AlertMessageBox alertMessageBox = new AlertMessageBox("Guardar Evento",
				errorMessage);
		alertMessageBox.show();
	}
}
