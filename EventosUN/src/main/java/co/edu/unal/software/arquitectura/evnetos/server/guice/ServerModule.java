package co.edu.unal.software.arquitectura.evnetos.server.guice;

import co.edu.unal.software.arquitectura.evnetos.server.services.handlers.RegisterActionHandler;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.RegisterAction;

import com.gwtplatform.dispatch.rpc.server.guice.HandlerModule;

public class ServerModule extends HandlerModule {
	@Override
	protected void configureHandlers() {
		bindHandler(RegisterAction.class, RegisterActionHandler.class);
	}
}
