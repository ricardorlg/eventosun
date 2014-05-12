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
import javax.validation.constraints.NotNull;

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

	@Column(unique = true, nullable = false)
	private String locationName;

	@Temporal(TemporalType.TIME)
	@NotNull
	private Date openTime;

	@Temporal(TemporalType.TIME)
	@NotNull
	private Date closeTime;

	// bi-directional many-to-one association to EveunEventLocation
	@OneToMany(mappedBy = "eveunLocation", cascade = { CascadeType.REMOVE,
			CascadeType.REFRESH })
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

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Date getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}

	public EveunUser getEveunUser() {
		return eveunUser;
	}

	public void setEveunUser(EveunUser eveunUser) {
		this.eveunUser = eveunUser;
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