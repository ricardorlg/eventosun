package co.edu.unal.software.arquitectura.evnetos.server.services.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import co.edu.unal.software.arquitectura.evnetos.server.entities.EveunEventLocation;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

public class EventLocationDao {
	@Inject
	Provider<EntityManager> emProvider;

	@Transactional
	public EveunEventLocation save(EveunEventLocation eventLocation) {
		emProvider.get().persist(eventLocation);
		return eventLocation;
	}

	public List<EveunEventLocation> findByLocation(int locationId) {
		EntityManager em = emProvider.get();
		TypedQuery<EveunEventLocation> query = em
				.createQuery(
						"select l from EveunEventLocation l where l.eveunLocation.idLocation=:locationId",
						EveunEventLocation.class);
		query.setParameter("locationId", locationId);
		return query.getResultList();
	}

	public Long findByLocationAndStartDate(int locationId, Date startDate) {
		EntityManager em = emProvider.get();
		TypedQuery<Long> query = em
				.createQuery(
						"select count(l) from EveunEventLocation l where l.eveunLocation.idLocation=:locationId and l.endTime>=:startDate",
						Long.class);
		query.setParameter("locationId", locationId);
		query.setParameter("startDate", startDate);
		return query.getSingleResult();
	}

	public List<EveunEventLocation> findAll() {
		EntityManager em = emProvider.get();
		return em.createNamedQuery("EveunEventLocation.findAll",
				EveunEventLocation.class).getResultList();
	}
}
