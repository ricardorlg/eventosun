package co.edu.unal.software.arquitectura.evnetos.client.application.adminlocations.addlocation;

import java.util.Date;

import co.edu.unal.software.arquitectura.evnetos.client.events.AddedLocationEvent;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.SaveLocationAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.SaveLocationResult;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.CurrentUserDto;

import com.google.common.base.Strings;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.TimeField;
import com.sencha.gxt.widget.core.client.info.Info;

public class AddLocationPresenter extends
		PresenterWidget<AddLocationPresenter.MyView> implements
		AddLocationUiHandlers {
	public interface MyView extends PopupView,
			HasUiHandlers<AddLocationUiHandlers> {

		TextField getLocationName();

		TextField getLocationAdress();

		TimeField getOpenTime();

		TimeField getCloseTime();

	}

	private CurrentUserDto currentuser;
	private DispatchAsync dispacther;

	@Inject
	AddLocationPresenter(EventBus eventBus, MyView view,
			CurrentUserDto currentUser, DispatchAsync dispatcher) {
		super(eventBus, view);
		this.currentuser = currentUser;
		this.dispacther = dispatcher;
		getView().setUiHandlers(this);
	}

	public void save() {
		String addres = getView().getLocationAdress().getValue();
		String name = getView().getLocationName().getValue();
		Date openTime = getView().getOpenTime().getValue();
		Date closeTime = getView().getCloseTime().getValue();
		SaveLocationAction.Builder builder = new SaveLocationAction.Builder(
				name, openTime, closeTime, currentuser.getId());
		if (!Strings.isNullOrEmpty(addres)) {
			builder.addres(addres);
		}
		dispacther.execute(builder.build(),
				new AsyncCallback<SaveLocationResult>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());
						getView().hide();
					}

					@Override
					public void onSuccess(SaveLocationResult result) {
						Info.display("Creacion de Espacio",
								"Espacio Creado con exito");
						currentuser.addLocation(result.getLocationAdded());
						getEventBus().fireEvent(new AddedLocationEvent(result.getLocationAdded()));
						getView().hide();
					}
				});
	}

	@Override
	public void cancel() {
		getView().hide();
	}
}
