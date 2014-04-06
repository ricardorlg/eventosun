package co.edu.unal.software.arquitectura.evnetos.server.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the eveun_event_location database table.
 * 
 */
@Embeddable
public class EveunEventLocationPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable = false, updatable = false)
	private int EVEUN_EVENT_id_event;

	@Column(insertable = false, updatable = false)
	private int EVEUN_LOCATION_id_location;

	public EveunEventLocationPK() {
	}

	public int getEVEUN_EVENT_id_event() {
		return this.EVEUN_EVENT_id_event;
	}

	public void setEVEUN_EVENT_id_event(int EVEUN_EVENT_id_event) {
		this.EVEUN_EVENT_id_event = EVEUN_EVENT_id_event;
	}

	public int getEVEUN_LOCATION_id_location() {
		return this.EVEUN_LOCATION_id_location;
	}

	public void setEVEUN_LOCATION_id_location(int EVEUN_LOCATION_id_location) {
		this.EVEUN_LOCATION_id_location = EVEUN_LOCATION_id_location;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EveunEventLocationPK)) {
			return false;
		}
		EveunEventLocationPK castOther = (EveunEventLocationPK) other;
		return (this.EVEUN_EVENT_id_event == castOther.EVEUN_EVENT_id_event)
				&& (this.EVEUN_LOCATION_id_location == castOther.EVEUN_LOCATION_id_location);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.EVEUN_EVENT_id_event;
		hash = hash * prime + this.EVEUN_LOCATION_id_location;

		return hash;
	}
}