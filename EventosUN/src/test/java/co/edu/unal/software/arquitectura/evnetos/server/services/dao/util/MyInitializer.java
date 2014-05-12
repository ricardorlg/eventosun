package co.edu.unal.software.arquitectura.evnetos.server.services.dao.util;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;

public class MyInitializer {
	private PersistService service;

	@Inject
	 MyInitializer(PersistService service) {
		
		this.service=service;

		// At this point JPA is started and ready.
	}
	public PersistService getService() {
		return service;
	}
}
