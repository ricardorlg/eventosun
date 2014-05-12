package co.edu.unal.software.arquitectura.evnetos.shared.actions;

import java.util.List;

import co.edu.unal.software.arquitectura.evnetos.shared.dto.EventLocationDto;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.Out;

@GenDispatch(isSecure = false)
public class LoadAllEventsByLocation {
	@Out(1)
	List<EventLocationDto> events;
}
