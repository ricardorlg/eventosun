package co.edu.unal.software.arquitectura.evnetos.server.services.dao;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import com.google.inject.Inject;

import co.edu.unal.software.arquitectura.evnetos.server.guice.DispatchServletModule;
import co.edu.unal.software.arquitectura.evnetos.server.services.dao.util.MyInitializer;

@Guice(modules = DispatchServletModule.class)
public class BaseClassTest {
	@Inject
	MyInitializer myInitializer;

	@BeforeClass
	protected void setUp() {
		myInitializer.getService().start();
	}

	@AfterClass
	protected void tearDown() {
		myInitializer.getService().stop();
	}
}
