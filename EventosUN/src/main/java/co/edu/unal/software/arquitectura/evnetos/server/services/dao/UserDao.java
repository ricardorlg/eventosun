package co.edu.unal.software.arquitectura.evnetos.server.services.dao;

import javax.persistence.EntityManager;

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
}
