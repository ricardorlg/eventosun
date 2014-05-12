package co.edu.unal.software.arquitectura.evnetos.shared.actions;

import java.util.Date;

import co.edu.unal.software.arquitectura.evnetos.shared.dto.LocationDto;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.annotation.Optional;
import com.gwtplatform.dispatch.annotation.Out;

@GenDispatch(isSecure = false)
public class SaveLocation {
	@In(1)
	private String name;
	@In(2)
	@Optional
	private String addres;
	@In(3)
	private Date openTime;
	@In(4)
	private Date closeTime;
	@In(5)
	private int userId;
	@Out(1)
	private String resultMessage;
	@Out(2)
	private LocationDto locationAdded;
}
