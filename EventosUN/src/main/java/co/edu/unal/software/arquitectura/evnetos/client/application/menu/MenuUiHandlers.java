package co.edu.unal.software.arquitectura.evnetos.client.application.menu;

import com.gwtplatform.mvp.client.UiHandlers;

public interface MenuUiHandlers extends UiHandlers {
	void openRegister();

	void openLogin();

	void eventsClicked();

	void adminLocationsClicked();

	void logOut();

	void adminEventsClicked();
}
