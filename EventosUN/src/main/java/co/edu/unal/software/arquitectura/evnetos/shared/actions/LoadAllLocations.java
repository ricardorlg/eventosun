package co.edu.unal.software.arquitectura.evnetos.shared.actions;

import java.util.List;

import co.edu.unal.software.arquitectura.evnetos.shared.dto.LocationDto;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.Out;

@GenDispatch(isSecure = false)
public class LoadAllLocations {
	@Out(1)
	String message;

	@Out(2)
	List<LocationDto> locations;
}
