package co.edu.unal.software.arquitectura.evnetos.shared.actions;

import java.util.List;

import co.edu.unal.software.arquitectura.evnetos.shared.dto.EventDto;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.EventLocationDto;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.annotation.Out;

@GenDispatch(isSecure = false)
public class SaveEvent {
	@In(1)
	int userId;
	@In(2)
	EventDto eventToCreate;
	@In(3)
	List<EventLocationDto> relationEventLocations;
	@Out(1)
	EventDto eventCreated;

}
