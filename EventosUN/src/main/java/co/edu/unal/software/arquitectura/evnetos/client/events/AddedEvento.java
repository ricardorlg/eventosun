package co.edu.unal.software.arquitectura.evnetos.client.events;

import co.edu.unal.software.arquitectura.evnetos.shared.dto.EventDto;

import com.gwtplatform.dispatch.annotation.GenEvent;
import com.gwtplatform.dispatch.annotation.Order;

@GenEvent
public class AddedEvento {
	@Order(1)
	EventDto eventDto;
}
