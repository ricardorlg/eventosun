package co.edu.unal.software.arquitectura.evnetos.shared.dto;

import java.io.Serializable;
import java.util.ArrayList;

import co.edu.unal.software.arquitectura.evnetos.shared.util.UserRole;

public class CurrentUserDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7028492905822940740L;
	private int id;
	private boolean loggedIn;
	private String userName;
	private String sessionId;
	private UserRole role;
	private ArrayList<LocationDto> locations = new ArrayList<LocationDto>();

	public CurrentUserDto() {
		// For serialization
	}

	public CurrentUserDto(int id, boolean loggedIn, String userName,
			String sessionId, UserRole role) {
		super();
		this.id = id;
		this.loggedIn = loggedIn;
		this.userName = userName;
		this.sessionId = sessionId;
		this.role = role;
	}

	public void copy(CurrentUserDto origin) {
		this.id = origin.id;
		this.loggedIn = origin.loggedIn;
		this.userName = origin.userName;
		this.role = origin.role;
		this.sessionId = origin.sessionId;
		this.locations = origin.locations;
	}

	public void reset() {
		id = -1;
		loggedIn = false;
		userName = null;
		sessionId = null;
		role = null;
	}

	public void addLocation(LocationDto e) {
		locations.add(e);
	}

	public void removeLocation(LocationDto loc) {
		locations.remove(loc);
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public ArrayList<LocationDto> getLocations() {
		return locations;
	}

	public void setLocations(ArrayList<LocationDto> locations) {
		this.locations = locations;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (loggedIn ? 1231 : 1237);
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result
				+ ((sessionId == null) ? 0 : sessionId.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CurrentUserDto other = (CurrentUserDto) obj;
		if (loggedIn != other.loggedIn)
			return false;
		if (role != other.role)
			return false;
		if (sessionId == null) {
			if (other.sessionId != null)
				return false;
		} else if (!sessionId.equals(other.sessionId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CurrentUserDto [loggedIn=" + loggedIn + ", userName="
				+ userName + ", sessionId=" + sessionId + ", role=" + role
				+ "]";
	}

}
