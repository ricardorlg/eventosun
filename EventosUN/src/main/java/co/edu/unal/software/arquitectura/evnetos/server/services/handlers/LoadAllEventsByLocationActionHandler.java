package co.edu.unal.software.arquitectura.evnetos.server.services.handlers;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.software.arquitectura.evnetos.server.entities.EveunEventLocation;
import co.edu.unal.software.arquitectura.evnetos.server.services.dao.EventLocationDao;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.LoadAllEventsByLocationAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.LoadAllEventsByLocationResult;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.EventLocationDto;

import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class LoadAllEventsByLocationActionHandler
		implements
		ActionHandler<LoadAllEventsByLocationAction, LoadAllEventsByLocationResult> {

	@Inject
	EventLocationDao dao;

	@Override
	public LoadAllEventsByLocationResult execute(
			LoadAllEventsByLocationAction action, ExecutionContext context)
			throws ActionException {
		List<EveunEventLocation> eventos = dao.findAll();
		return new LoadAllEventsByLocationResult(parseEvents(eventos));
	}

	private List<EventLocationDto> parseEvents(List<EveunEventLocation> eventos) {
		// TODO Auto-generated method stub
		List<EventLocationDto> eventosDto = new ArrayList<EventLocationDto>();
		for (EveunEventLocation eventLocation : eventos) {
			EventLocationDto evDto = new EventLocationDto();
			evDto.setLocationId(eventLocation.getEveunLocation()
					.getIdLocation());
			evDto.setEventName(eventLocation.getEveunEvent().getEventName());
			evDto.setStartDate(eventLocation.getStartTime());
			evDto.setCloseDate(eventLocation.getEndTime());
			evDto.setLocationName(eventLocation.getEveunLocation()
					.getLocationName());
			if (!Strings.isNullOrEmpty(eventLocation.getEveunEvent()
					.getEventDescription())) {
				evDto.setEventDescription(eventLocation.getEveunEvent()
						.getEventDescription());
			}
			eventosDto.add(evDto);
		}
		return eventosDto;
	}

	@Override
	public Class<LoadAllEventsByLocationAction> getActionType() {
		// TODO Auto-generated method stub
		return LoadAllEventsByLocationAction.class;
	}

	@Override
	public void undo(LoadAllEventsByLocationAction action,
			LoadAllEventsByLocationResult result, ExecutionContext context)
			throws ActionException {
		// TODO Auto-generated method stub

	}

}
