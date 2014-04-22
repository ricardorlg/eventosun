package co.edu.unal.software.arquitectura.evnetos.shared.util;

public enum UserRole {

	GENERAL("Usuario General"), EVENT_ADMIN("Administrador de eventos"), LOCATION_ADMIN(
			"Administrador de espacios");

	private String name;

	UserRole(String n) {
		name = n;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}

}
