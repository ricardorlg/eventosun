package co.edu.unal.software.arquitectura.evnetos.server.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the eveun_event database table.
 * 
 */
@Entity
@Table(name = "eveun_event")
@NamedQuery(name = "EveunEvent.findAll", query = "SELECT e FROM EveunEvent e")
public class EveunEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_event")
	private int idEvent;

	@Column(name = "event_description")
	private String eventDescription;

	@Column(name = "event_name")
	private String eventName;

	@Column(name = "event_responsible")
	private String eventResponsible;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_time")
	private Date endTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_time")
	private Date startTime;

	// bi-directional many-to-one association to EveunUser
	@ManyToOne
	@JoinColumn(name = "ID_USER")
	private EveunUser eveunUser;

	// bi-directional many-to-one association to EveunEventLocation
	@OneToMany(mappedBy = "eveunEvent", cascade = { CascadeType.REMOVE,
			CascadeType.REFRESH })
	private List<EveunEventLocation> eveunEventLocations;

	public EveunEvent() {
	}

	public int getIdEvent() {
		return this.idEvent;
	}

	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}

	public String getEventDescription() {
		return this.eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getEventName() {
		return this.eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventResponsible() {
		return this.eventResponsible;
	}

	public void setEventResponsible(String eventResponsible) {
		this.eventResponsible = eventResponsible;
	}

	public EveunUser getEveunUser() {
		return this.eveunUser;
	}

	public void setEveunUser(EveunUser eveunUser) {
		this.eveunUser = eveunUser;
	}

	public List<EveunEventLocation> getEveunEventLocations() {
		return this.eveunEventLocations;
	}

	public void setEveunEventLocations(
			List<EveunEventLocation> eveunEventLocations) {
		this.eveunEventLocations = eveunEventLocations;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public EveunEventLocation addEveunEventLocation(
			EveunEventLocation eveunEventLocation) {
		getEveunEventLocations().add(eveunEventLocation);
		eveunEventLocation.setEveunEvent(this);

		return eveunEventLocation;
	}

	public EveunEventLocation removeEveunEventLocation(
			EveunEventLocation eveunEventLocation) {
		getEveunEventLocations().remove(eveunEventLocation);
		eveunEventLocation.setEveunEvent(null);

		return eveunEventLocation;
	}

}