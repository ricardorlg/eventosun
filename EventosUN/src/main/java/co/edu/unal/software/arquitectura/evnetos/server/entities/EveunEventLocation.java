package co.edu.unal.software.arquitectura.evnetos.server.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_time")
	private Date endTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_time")
	private Date startTime;

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

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
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