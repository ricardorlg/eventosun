package co.edu.unal.software.arquitectura.evnetos.server.services.dao;

import javax.persistence.EntityManager;

import co.edu.unal.software.arquitectura.evnetos.server.entities.EveunEvent;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

public class EventDao {
	@Inject
	Provider<EntityManager> emProvider;

	@Transactional
	public EveunEvent save(EveunEvent event) {
		emProvider.get().persist(event);
		return event;
	}

	public EveunEvent read(int id) {

		EveunEvent event = emProvider.get().find(EveunEvent.class, id);
		emProvider.get().refresh(event);
		return event;
	}

	@Transactional
	public void delete(EveunEvent event) {
		EntityManager em = emProvider.get();

		em.remove(em.getReference(EveunEvent.class, event.getIdEvent()));
	}
}
