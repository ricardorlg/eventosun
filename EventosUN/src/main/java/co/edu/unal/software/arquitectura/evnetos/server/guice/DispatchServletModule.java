package co.edu.unal.software.arquitectura.evnetos.server.guice;

import co.edu.unal.software.arquitectura.evnetos.server.services.dao.EventDao;
import co.edu.unal.software.arquitectura.evnetos.server.services.dao.EventLocationDao;
import co.edu.unal.software.arquitectura.evnetos.server.services.dao.LocationDao;
import co.edu.unal.software.arquitectura.evnetos.server.services.dao.UserDao;

import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;
import com.gwtplatform.dispatch.rpc.server.guice.DispatchServiceImpl;
import com.gwtplatform.dispatch.rpc.shared.ActionImpl;

public class DispatchServletModule extends ServletModule {
	@Override
	public void configureServlets() {
		install(new JpaPersistModule("EventosUN"));
		filter("/*").through(PersistFilter.class);
		bind(UserDao.class);
		bind(LocationDao.class);
		bind(EventDao.class);
		bind(EventLocationDao.class);
		serve("/" + ActionImpl.DEFAULT_SERVICE_NAME + "*").with(
				DispatchServiceImpl.class);
	}
}
