package co.edu.unal.software.arquitectura.evnetos.server.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the eveun_event_location database table.
 * 
 */
@Entity
@Table(name = "eveun_event_location")
@NamedQuery(name = "EveunEventLocation.findAll", query = "SELECT e FROM EveunEventLocation e")
public class EveunEventLocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EveunEventLocationPK id;

	@Column(name = "end_time")
	private Timestamp endTime;

	@Column(name = "start_time")
	private Timestamp startTime;

	// bi-directional many-to-one association to EveunEvent
	@ManyToOne
	@JoinColumn(name = "EVEUN_EVENT_id_event")
	private EveunEvent eveunEvent;

	// bi-directional many-to-one association to EveunLocation
	@ManyToOne
	@JoinColumn(name = "EVEUN_LOCATION_id_location")
	private EveunLocation eveunLocation;

	public EveunEventLocation() {
	}

	public EveunEventLocationPK getId() {
		return this.id;
	}

	public void setId(EveunEventLocationPK id) {
		this.id = id;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public EveunEvent getEveunEvent() {
		return this.eveunEvent;
	}

	public void setEveunEvent(EveunEvent eveunEvent) {
		this.eveunEvent = eveunEvent;
	}

	public EveunLocation getEveunLocation() {
		return this.eveunLocation;
	}

	public void setEveunLocation(EveunLocation eveunLocation) {
		this.eveunLocation = eveunLocation;
	}

}