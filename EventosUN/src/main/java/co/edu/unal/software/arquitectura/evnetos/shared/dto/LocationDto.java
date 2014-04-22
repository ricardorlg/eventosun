package co.edu.unal.software.arquitectura.evnetos.shared.dto;

import java.io.Serializable;
import java.util.Date;

public class LocationDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8768682656018436143L;
	private int id;
	private String name;
	private String addres;
	private Date openTime;
	private Date closeTime;
public LocationDto(){
	
}

	public LocationDto(int id, String name, Date openTime, Date closeTime) {
	super();
	this.id = id;
	this.name = name;
	this.openTime = openTime;
	this.closeTime = closeTime;
}

	public LocationDto(int id, String name, String addres, Date openTime,
			Date closeTime) {
		super();
		this.id = id;
		this.name = name;
		this.addres = addres;
		this.openTime = openTime;
		this.closeTime = closeTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddres() {
		return addres;
	}

	public void setAddres(String addres) {
		this.addres = addres;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addres == null) ? 0 : addres.hashCode());
		result = prime * result
				+ ((closeTime == null) ? 0 : closeTime.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((openTime == null) ? 0 : openTime.hashCode());
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
		LocationDto other = (LocationDto) obj;
		if (addres == null) {
			if (other.addres != null)
				return false;
		} else if (!addres.equals(other.addres))
			return false;
		if (closeTime == null) {
			if (other.closeTime != null)
				return false;
		} else if (!closeTime.equals(other.closeTime))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (openTime == null) {
			if (other.openTime != null)
				return false;
		} else if (!openTime.equals(other.openTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LocationDto [id=" + id + ", name=" + name + ", addres="
				+ addres + ", openTime=" + openTime + ", closeTime="
				+ closeTime + "]";
	}

}
