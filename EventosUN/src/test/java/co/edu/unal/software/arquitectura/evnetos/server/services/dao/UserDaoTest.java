package co.edu.unal.software.arquitectura.evnetos.server.services.dao;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;

import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import co.edu.unal.software.arquitectura.evnetos.server.entities.EveunUser;
import co.edu.unal.software.arquitectura.evnetos.server.guice.DispatchServletModule;
import co.edu.unal.software.arquitectura.evnetos.server.services.dao.util.MyInitializer;
import co.edu.unal.software.arquitectura.evnetos.shared.util.UserRole;

import com.google.inject.Inject;

@Guice(modules = DispatchServletModule.class)
public class UserDaoTest {
	@Inject
	UserDao userDao;
	@Inject
	MyInitializer myInitializer;
	private int u1;
	private final String usernameUser2 = "ricardorlg2";
	private EveunUser user;

	@Test
	public void UserDaoShouldNotBeNull() {
		Assert.assertNotNull(userDao);
	}

	@Test(dependsOnGroups = { "readUsers", "updateUsers" }, expectedExceptions = EntityNotFoundException.class)
	public void delete() {
		userDao.delete(user);
		userDao.delete(user);
	}

	@Test(dependsOnGroups = { "saveUsers", "readUsers" }, groups = "updateUsers")
	public void updateTest() {
		user.setName("juan");
		userDao.update(user);
		Assert.assertEquals(userDao.read(user.getIdUser()).getName(), "juan");
	}

	@Test(dependsOnGroups = "saveUsers", groups = "readUsers")
	public void readint() {
		EveunUser userReaded = userDao.read(u1);
		Assert.assertNotNull(userReaded);
	}

	@Test(dependsOnGroups = "saveUsers", groups = "readUsers")
	public void readString() {
		EveunUser userReaded = userDao.read(usernameUser2);
		Assert.assertNotNull(userReaded);
	}

	@Test(groups = "saveUsers")
	public void saveWithAllFields() {
		user = new EveunUser();
		user.setName("ricardo");
		user.setLastname("larrahondo");
		user.setEmail("ricardo@prueba.com");
		user.setPhone("1234567");
		user.setRole(UserRole.GENERAL);
		user.setUsername("ricardorlg");
		user.setPassword("mayhem123");
		user = userDao.save(user);
		Assert.assertTrue(user.getIdUser() > 0);
		u1 = user.getIdUser();
	}

	@Test(groups = "saveUsers")
	public void saveWithRequiredFields() {
		EveunUser user2 = new EveunUser();
		user2.setName("ricardo2");
		user2.setEmail("ricardo@prueba.com");
		user2.setRole(UserRole.GENERAL);
		user2.setUsername("ricardorlg2");
		user2.setPassword("mayhem123");
		user2 = userDao.save(user2);
		Assert.assertTrue(user2.getIdUser() > 0);
	}

	@Test(expectedExceptions = RollbackException.class, groups = "saveUsers")
	public void usernameShouldNotBeNull() {
		EveunUser user = new EveunUser();
		user.setName("ricardo");
		user.setLastname("larrahondo");
		user.setEmail("ricardo@prueba.com");
		user.setPhone("1234567");
		user.setRole(UserRole.GENERAL);
		user.setPassword("mayhem123");
		user = userDao.save(user);
	}

	@Test(expectedExceptions = PersistenceException.class, groups = "saveUsers")
	public void usernameShouldBeUnique() {
		EveunUser user = new EveunUser();
		user.setName("ricardo");
		user.setEmail("ricardo@prueba.com");
		user.setRole(UserRole.GENERAL);
		user.setUsername("ricardorlg");
		user.setPassword("mayhem123");
		user = userDao.save(user);

	}

}
