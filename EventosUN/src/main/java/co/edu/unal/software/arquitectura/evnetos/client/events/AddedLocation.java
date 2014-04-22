package co.edu.unal.software.arquitectura.evnetos.client.events;

import co.edu.unal.software.arquitectura.evnetos.shared.dto.LocationDto;

import com.gwtplatform.dispatch.annotation.GenEvent;
import com.gwtplatform.dispatch.annotation.Order;

@GenEvent
public class AddedLocation {
	@Order(1)
	LocationDto location;
}
