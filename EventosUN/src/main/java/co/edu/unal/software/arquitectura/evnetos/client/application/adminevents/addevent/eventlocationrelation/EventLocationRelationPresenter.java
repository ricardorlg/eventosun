package co.edu.unal.software.arquitectura.evnetos.client.application.adminevents.addevent.eventlocationrelation;

import java.util.Date;

import co.edu.unal.software.arquitectura.evnetos.shared.actions.LoadAllLocationsAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.LoadAllLocationsResult;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.VerifyDisponibilityAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.VerifyDisponibilityResult;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.EventLocationDto;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.LocationDto;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.box.MessageBox;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.TimeField;

public class EventLocationRelationPresenter extends
		PresenterWidget<EventLocationRelationPresenter.MyView> implements
		EventLocationRelationUiHandlers {
	public interface MyView extends View,
			HasUiHandlers<EventLocationRelationUiHandlers> {

		ListStore<LocationDto> getStore();

		void setStore(ListStore<LocationDto> store);

		ComboBox<LocationDto> getLocations();

		DateField getOpenDate();

		TimeField getOpenTime();

		DateField getCloseDate();

		TimeField getCloseTime();

		Date getOpenDateValue();

		Date getCloseDateValue();
	}

	private DispatchAsync dispatcher;
	private Boolean disponible;

	@Inject
	EventLocationRelationPresenter(EventBus eventBus, MyView view,
			DispatchAsync dispatcher) {
		super(eventBus, view);
		this.dispatcher = dispatcher;
		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		// TODO Auto-generated method stub
		super.onBind();

		dispatcher.execute(new LoadAllLocationsAction(),
				new AsyncCallback<LoadAllLocationsResult>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());

					}

					@Override
					public void onSuccess(LoadAllLocationsResult result) {

						if (result.getMessage().isEmpty()) {
							getView().getStore().addAll(result.getLocations());
						} else {
							Window.alert(result.getMessage());
						}

					}
				});

	}

	@Override
	public void verificarDisponibilidad() {
		int id = getView().getLocations().getValue().getId();
		Date openDate = getView().getOpenDateValue();
		dispatcher.execute(new VerifyDisponibilityAction(id, openDate),
				new AsyncCallback<VerifyDisponibilityResult>() {

					private MessageBox messageBox;

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getLocalizedMessage());

					}

					@Override
					public void onSuccess(VerifyDisponibilityResult result) {
						if (!result.isDisponible()) {
							messageBox = new MessageBox(
									"Disponibilidad de espacio",
									"El espacio esta disponible");
							disponible = new Boolean(true);

						} else {
							messageBox = new AlertMessageBox(
									"Disponibilidad de espacio",
									"El espacio no esta disponible");
							disponible = new Boolean(false);

						}
						messageBox.show();
					}
				});

	}

	public Boolean getDisponible() {
		return disponible;
	}

	public EventLocationDto getRelation() {
		EventLocationDto relation = new EventLocationDto();
		relation.setLocationId(getView().getLocations().getValue().getId());
		relation.setStartDate(getView().getOpenDateValue());
		relation.setCloseDate(getView().getCloseDateValue());
		return relation;
	}
}
