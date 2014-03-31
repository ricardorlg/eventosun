package co.edu.unal.software.arquitectura.evnetos.server.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

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

	private String email;

	private String lastname;

	private String name;

	private String password;

	private String phone;

	private String username;

	// bi-directional many-to-one association to EveunEvent
	@OneToMany(mappedBy = "eveunUser")
	private List<EveunEvent> eveunEvents;

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

}