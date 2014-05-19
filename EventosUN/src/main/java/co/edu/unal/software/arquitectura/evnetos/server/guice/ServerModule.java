package co.edu.unal.software.arquitectura.evnetos.server.guice;

import co.edu.unal.software.arquitectura.evnetos.server.services.handlers.EventCreationActionHandler;
import co.edu.unal.software.arquitectura.evnetos.server.services.handlers.LoadAllEventsByLocationActionHandler;
import co.edu.unal.software.arquitectura.evnetos.server.services.handlers.LoadAllLocationsActionHandler;
import co.edu.unal.software.arquitectura.evnetos.server.services.handlers.LoadServerInfoActionHandler;
import co.edu.unal.software.arquitectura.evnetos.server.services.handlers.LogOutActionHandler;
import co.edu.unal.software.arquitectura.evnetos.server.services.handlers.LoginActionHandler;
import co.edu.unal.software.arquitectura.evnetos.server.services.handlers.LoginByCookieActionHanfler;
import co.edu.unal.software.arquitectura.evnetos.server.services.handlers.RegisterActionHandler;
import co.edu.unal.software.arquitectura.evnetos.server.services.handlers.SaveLocationActionHandler;
import co.edu.unal.software.arquitectura.evnetos.server.services.handlers.VerifyDisponibilityActionHanler;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.LoadAllEventsByLocationAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.LoadAllLocationsAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.LoadServerInfoAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.LogOutAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.LoginAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.LoginByCookieAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.RegisterAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.SaveEventAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.SaveLocationAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.VerifyDisponibilityAction;

import com.gwtplatform.dispatch.rpc.server.guice.HandlerModule;

public class ServerModule extends HandlerModule {
	@Override
	protected void configureHandlers() {
		bindHandler(RegisterAction.class, RegisterActionHandler.class);
		bindHandler(LoginAction.class, LoginActionHandler.class);
		bindHandler(LoginByCookieAction.class, LoginByCookieActionHanfler.class);
		bindHandler(LogOutAction.class, LogOutActionHandler.class);
		bindHandler(SaveLocationAction.class, SaveLocationActionHandler.class);
		bindHandler(LoadAllLocationsAction.class,
				LoadAllLocationsActionHandler.class);
		bindHandler(VerifyDisponibilityAction.class,
				VerifyDisponibilityActionHanler.class);
		bindHandler(SaveEventAction.class, EventCreationActionHandler.class);
		bindHandler(LoadAllEventsByLocationAction.class,
				LoadAllEventsByLocationActionHandler.class);
		bindHandler(LoadServerInfoAction.class,
				LoadServerInfoActionHandler.class);
	}
}
