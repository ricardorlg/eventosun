package co.edu.unal.software.arquitectura.evnetos.server.services.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import co.edu.unal.software.arquitectura.evnetos.server.entities.EveunLocation;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

public class LocationDao {
	@Inject
	Provider<EntityManager> emProvider;

	@Transactional
	public EveunLocation save(EveunLocation location) {
		emProvider.get().persist(location);
		return location;
	}

	public EveunLocation read(int id) {
		EveunLocation location = emProvider.get().find(EveunLocation.class, id);

		return location;
	}

	public List<EveunLocation> readAllLocations() {
		return emProvider.get()
				.createNamedQuery("EveunLocation.findAll", EveunLocation.class)
				.getResultList();
	}

	public EveunLocation read(String locationName) {
		TypedQuery<EveunLocation> locationQueryByName = emProvider
				.get()
				.createQuery(
						"select l from EveunLocation l where l.locationName = :locationName",
						EveunLocation.class)
				.setParameter("locationName", locationName);
		return locationQueryByName.getSingleResult();
	}

	@Transactional
	public void delete(EveunLocation eu) {
		EntityManager em = emProvider.get();

		em.remove(em.getReference(EveunLocation.class, eu.getIdLocation()));
	}
	/*
	 * @Transactional public EveunUser update(EveunUser toUpdate) { return
	 * emProvider.get().merge(toUpdate); }
	 */
}
