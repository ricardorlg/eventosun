package co.edu.unal.software.arquitectura.evnetos.server.services.dao.util;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;

public class MyInitializer {
	@Inject
	MyInitializer(PersistService service) {
		service.start();

		// At this point JPA is started and ready.
	}
}
