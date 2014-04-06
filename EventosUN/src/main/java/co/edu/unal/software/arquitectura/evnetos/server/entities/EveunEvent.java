package co.edu.unal.software.arquitectura.evnetos.server.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

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

	// bi-directional many-to-one association to EveunUser
	@ManyToOne
	@JoinColumn(name = "ID_USER")
	private EveunUser eveunUser;

	// bi-directional many-to-one association to EveunEventLocation
	@OneToMany(mappedBy = "eveunEvent")
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