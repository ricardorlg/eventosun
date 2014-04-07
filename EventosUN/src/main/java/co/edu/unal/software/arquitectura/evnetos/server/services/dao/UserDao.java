package co.edu.unal.software.arquitectura.evnetos.server.services.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import co.edu.unal.software.arquitectura.evnetos.server.entities.EveunUser;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

public class UserDao {
	@Inject
	Provider<EntityManager> emProvider;

	@Transactional
	public EveunUser save(EveunUser user) {
		emProvider.get().persist(user);
		return user;
	}

	public EveunUser read(int id) {
		EveunUser user = emProvider.get().find(EveunUser.class, id);
		return user;
	}

	public EveunUser read(String userName) {
		TypedQuery<EveunUser> user = emProvider
				.get()
				.createQuery(
						"select u from EveunUser u where u.username = :userName",
						EveunUser.class).setParameter("userName", userName);
		return user.getSingleResult();
	}

	@Transactional
	public void delete(EveunUser eu) {
		EntityManager em = emProvider.get();

		em.remove(em.getReference(EveunUser.class, eu.getIdUser()));
	}

	@Transactional
	public EveunUser update(EveunUser toUpdate) {
		return emProvider.get().merge(toUpdate);
	}
}
