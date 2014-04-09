package co.edu.unal.software.arquitectura.evnetos.client.application.register.login;

import com.gwtplatform.mvp.client.UiHandlers;

public interface LoginUiHandlers extends UiHandlers {
	void login(String username, String password);
}
