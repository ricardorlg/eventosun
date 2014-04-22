package co.edu.unal.software.arquitectura.evnetos.server.guice;

import co.edu.unal.software.arquitectura.evnetos.server.services.handlers.LogOutActionHandler;
import co.edu.unal.software.arquitectura.evnetos.server.services.handlers.LoginActionHandler;
import co.edu.unal.software.arquitectura.evnetos.server.services.handlers.LoginByCookieActionHanfler;
import co.edu.unal.software.arquitectura.evnetos.server.services.handlers.RegisterActionHandler;
import co.edu.unal.software.arquitectura.evnetos.server.services.handlers.SaveLocationActionHandler;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.LogOutAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.LoginAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.LoginByCookieAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.RegisterAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.SaveLocationAction;

import com.gwtplatform.dispatch.rpc.server.guice.HandlerModule;

public class ServerModule extends HandlerModule {
	@Override
	protected void configureHandlers() {
		bindHandler(RegisterAction.class, RegisterActionHandler.class);
		bindHandler(LoginAction.class, LoginActionHandler.class);
		bindHandler(LoginByCookieAction.class, LoginByCookieActionHanfler.class);
		bindHandler(LogOutAction.class, LogOutActionHandler.class);
		bindHandler(SaveLocationAction.class, SaveLocationActionHandler.class);
	}
}
