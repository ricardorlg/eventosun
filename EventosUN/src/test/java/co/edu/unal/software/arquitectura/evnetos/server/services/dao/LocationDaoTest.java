package co.edu.unal.software.arquitectura.evnetos.server.services.dao;

import java.util.Calendar;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import com.google.inject.Inject;

import co.edu.unal.software.arquitectura.evnetos.server.entities.EveunLocation;
import co.edu.unal.software.arquitectura.evnetos.server.entities.EveunUser;
import co.edu.unal.software.arquitectura.evnetos.server.guice.DispatchServletModule;
import co.edu.unal.software.arquitectura.evnetos.server.services.dao.util.MyInitializer;
import co.edu.unal.software.arquitectura.evnetos.shared.util.UserRole;

@Guice(modules = DispatchServletModule.class)
public class LocationDaoTest {
	@Inject
	LocationDao locationDao;
	@Inject
	UserDao userDao;
	@Inject
	MyInitializer myInitializer;
	private EveunUser user;

	@BeforeClass
	public void setUp() {
		user = new EveunUser();

		user.setName("ricardo");
		user.setLastname("larrahondo");
		user.setEmail("ricardo@prueba.com");
		user.setPhone("1234567");
		user.setRole(UserRole.GENERAL);
		user.setUsername("ricardorlg");
		user.setPassword("mayhem123");
		userDao.save(user);
	}

	@Test(enabled = false)
	public void delete() {
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled = false)
	public void readint() {
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled = false)
	public void readString() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void save() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, 2);

		EveunLocation location = new EveunLocation();
		location.setLocationName("Ubicacion de prueba");
		location.setLocationAddress("mi casa");
		location.setEveunUser(user);
		location.setOpenTime(new Date());
		location.setCloseTime(c.getTime());
		location = locationDao.save(location);
		user.addEveunLocation(location);
		userDao.update(user);
		int i=userDao.read(user.getIdUser()).getEveunLocations().size();
		System.out.println(i);
		Assert.assertTrue(location.getIdLocation() > 0);
	}
}
