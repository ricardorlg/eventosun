package co.edu.unal.software.arquitectura.evnetos.client.application.adminevents.addevent;

import com.gwtplatform.mvp.client.UiHandlers;

public interface AddEventUiHandlers extends UiHandlers {
	void addLocation();

	void errorMessage(String errorMessage);

	void cancel();

	void save();

}
