package co.edu.unal.software.arquitectura.evnetos.server.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the eveun_location database table.
 * 
 */
@Entity
@Table(name = "eveun_location")
@NamedQuery(name = "EveunLocation.findAll", query = "SELECT e FROM EveunLocation e")
public class EveunLocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_location")
	private int idLocation;

	@Column(name = "location_address")
	private String locationAddress;

	@Column(name = "location_name")
	private String locationName;

	// bi-directional many-to-one association to EveunEventLocation
	@OneToMany(mappedBy = "eveunLocation")
	private List<EveunEventLocation> eveunEventLocations;
	
	@ManyToOne
	@JoinColumn(name = "ID_USER")
	private EveunUser eveunUser;

	public EveunLocation() {
	}

	public int getIdLocation() {
		return this.idLocation;
	}

	public void setIdLocation(int idLocation) {
		this.idLocation = idLocation;
	}

	public String getLocationAddress() {
		return this.locationAddress;
	}

	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}

	public String getLocationName() {
		return this.locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
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
		eveunEventLocation.setEveunLocation(this);

		return eveunEventLocation;
	}

	public EveunEventLocation removeEveunEventLocation(
			EveunEventLocation eveunEventLocation) {
		getEveunEventLocations().remove(eveunEventLocation);
		eveunEventLocation.setEveunLocation(null);

		return eveunEventLocation;
	}

}