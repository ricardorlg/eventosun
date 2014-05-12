package co.edu.unal.software.arquitectura.evnetos.client.application.userhome;

import com.bradrydzewski.gwt.calendar.client.Appointment;
import com.gwtplatform.mvp.client.UiHandlers;

public interface UserHomeUiHandlers extends UiHandlers {
	void loadEventInfo(Appointment appointment);
}
