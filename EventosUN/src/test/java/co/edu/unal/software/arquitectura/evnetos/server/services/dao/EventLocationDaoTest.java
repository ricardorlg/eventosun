package co.edu.unal.software.arquitectura.evnetos.server.services.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import com.google.inject.Inject;

import co.edu.unal.software.arquitectura.evnetos.server.entities.EveunEvent;
import co.edu.unal.software.arquitectura.evnetos.server.entities.EveunEventLocation;
import co.edu.unal.software.arquitectura.evnetos.server.entities.EveunEventLocationPK;
import co.edu.unal.software.arquitectura.evnetos.server.entities.EveunLocation;
import co.edu.unal.software.arquitectura.evnetos.server.entities.EveunUser;
import co.edu.unal.software.arquitectura.evnetos.server.guice.DispatchServletModule;
import co.edu.unal.software.arquitectura.evnetos.server.services.dao.util.MyInitializer;
import co.edu.unal.software.arquitectura.evnetos.shared.util.UserRole;

public class EventLocationDaoTest extends BaseClassTest {
	@Inject
	LocationDao locationDao;
	@Inject
	UserDao userDao;

	@Inject
	EventDao eventDao;
	@Inject
	EventLocationDao eventLocationDao;
	private EveunUser user;
	private EveunLocation location;

	@Override
	@BeforeClass
	protected void setUp() {
		// TODO Auto-generated method stub
		super.setUp();
		createUser();
		createLocation();
	}

	@Test(groups = "save")
	public void save() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 2);
		EveunEvent event = new EveunEvent();
		event.setEventName("evento de prueba");
		event.setEventDescription("Evento de prueba");
		event.setEventResponsible("ricardo");
		event.setStartTime(new Date());
		event.setEndTime(c.getTime());
		event = eventDao.save(event);
		EveunEventLocation eventLocation = new EveunEventLocation();
		EveunEventLocationPK eventLocationPK = new EveunEventLocationPK();
		eventLocationPK.setEVEUN_EVENT_id_event(event.getIdEvent());
		eventLocationPK.setEVEUN_LOCATION_id_location(location.getIdLocation());
		eventLocation.setId(eventLocationPK);
		eventLocation.setStartTime(new Date());
		eventLocation.setEndTime(c.getTime());
		eventLocation.setEveunEvent(event);
		eventLocation.setEveunLocation(location);
		eventLocationDao.save(eventLocation);
		location.addEveunEventLocation(eventLocation);
		event.addEveunEventLocation(eventLocation);

		user.addEveunEvent(event);
		user.addEveunLocation(location);
		userDao.update(user);

	}

	@Test(dependsOnGroups = "save")
	public void findByLocationTest() {
		List<EveunEventLocation> result = eventLocationDao
				.findByLocation(location.getIdLocation());
		Assert.assertTrue(!result.isEmpty());
	}

	@Test(dependsOnGroups = "save")
	public void findByLocationAndDateTest() {
		Long result = eventLocationDao.findByLocationAndStartDate(
				location.getIdLocation(), new Date());
		Assert.assertTrue(result.longValue() > 0);
	}

	private void createUser() {
		user = new EveunUser();
		user.setName("ricardo");
		user.setLastname("larrahondo");
		user.setEmail("ricardo@prueba.com");
		user.setPhone("1234567");
		user.setRole(UserRole.GENERAL);
		user.setUsername("ricardorlg");
		user.setPassword("mayhem123");
		user = userDao.save(user);
	}

	private void createLocation() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, 2);

		location = new EveunLocation();
		location.setLocationName("Ubicacion de prueba");
		location.setLocationAddress("mi casa");
		location.setEveunUser(user);
		location.setOpenTime(new Date());
		location.setCloseTime(c.getTime());
		location = locationDao.save(location);

	}

}
