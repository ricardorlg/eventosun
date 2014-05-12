package co.edu.unal.software.arquitectura.evnetos.server.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import co.edu.unal.software.arquitectura.evnetos.shared.util.UserRole;

/**
 * The persistent class for the eveun_user database table.
 * 
 */
@Entity
@Table(name = "eveun_user")
@NamedQuery(name = "EveunUser.findAll", query = "SELECT e FROM EveunUser e")
public class EveunUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_USER")
	private int idUser;
	@NotNull
	private String email;

	private String lastname;
	@NotNull
	private String name;
	@NotNull
	private String password;

	private String phone;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserRole role;

	@Column(unique = true, nullable = false)
	private String username;

	// bi-directional many-to-one association to EveunEvent
	@OneToMany(mappedBy = "eveunUser", cascade = { CascadeType.MERGE,
			CascadeType.REMOVE })
	private List<EveunEvent> eveunEvents;

	@OneToMany(mappedBy = "eveunUser", cascade = { CascadeType.MERGE,
			CascadeType.REMOVE })
	private List<EveunLocation> eveunLocations = new ArrayList<EveunLocation>();

	public EveunUser() {
	}

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<EveunEvent> getEveunEvents() {
		return this.eveunEvents;
	}

	public void setEveunEvents(List<EveunEvent> eveunEvents) {
		this.eveunEvents = eveunEvents;
	}

	public List<EveunLocation> getEveunLocations() {
		return eveunLocations;
	}

	public void setEveunLocations(List<EveunLocation> eveunLocations) {
		this.eveunLocations = eveunLocations;
	}

	public EveunEvent addEveunEvent(EveunEvent eveunEvent) {
		getEveunEvents().add(eveunEvent);
		eveunEvent.setEveunUser(this);

		return eveunEvent;
	}

	public EveunEvent removeEveunEvent(EveunEvent eveunEvent) {
		getEveunEvents().remove(eveunEvent);
		eveunEvent.setEveunUser(null);

		return eveunEvent;
	}

	public EveunLocation addEveunLocation(EveunLocation eveunLocation) {
		getEveunLocations().add(eveunLocation);
		eveunLocation.setEveunUser(this);

		return eveunLocation;
	}

	public EveunLocation removeEveunLocation(EveunLocation eveunLocation) {
		getEveunLocations().remove(eveunLocation);
		eveunLocation.setEveunUser(null);

		return eveunLocation;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

}