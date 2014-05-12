package co.edu.unal.software.arquitectura.evnetos.server.services.handlers;

import co.edu.unal.software.arquitectura.evnetos.server.entities.EveunEvent;
import co.edu.unal.software.arquitectura.evnetos.server.entities.EveunEventLocation;
import co.edu.unal.software.arquitectura.evnetos.server.entities.EveunEventLocationPK;
import co.edu.unal.software.arquitectura.evnetos.server.entities.EveunLocation;
import co.edu.unal.software.arquitectura.evnetos.server.entities.EveunUser;
import co.edu.unal.software.arquitectura.evnetos.server.services.dao.EventDao;
import co.edu.unal.software.arquitectura.evnetos.server.services.dao.EventLocationDao;
import co.edu.unal.software.arquitectura.evnetos.server.services.dao.LocationDao;
import co.edu.unal.software.arquitectura.evnetos.server.services.dao.UserDao;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.SaveEventAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.SaveEventResult;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.EventDto;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.EventLocationDto;

import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class EventCreationActionHandler implements
		ActionHandler<SaveEventAction, SaveEventResult> {
	@Inject
	LocationDao locationDao;
	@Inject
	UserDao userDao;
	@Inject
	EventDao eventDao;
	@Inject
	EventLocationDao eventLocationDao;

	@Override
	public SaveEventResult execute(SaveEventAction action,
			ExecutionContext context) throws ActionException {
		EveunEvent event = new EveunEvent();
		event.setEventName(action.getEventToCreate().getEventName());
		event.setStartTime(action.getEventToCreate().getStartDate());
		event.setEndTime(action.getEventToCreate().getEndDate());
		if (!Strings.isNullOrEmpty(action.getEventToCreate()
				.getEventDescription())) {
			event.setEventDescription(action.getEventToCreate()
					.getEventDescription());
		}
		event = eventDao.save(event);
		for (EventLocationDto relation : action.getRelationEventLocations()) {
			EveunLocation location = locationDao.read(relation.getLocationId());
			EveunEventLocation eventLocation = new EveunEventLocation();
			EveunEventLocationPK eventLocationPK = new EveunEventLocationPK();
			eventLocationPK.setEVEUN_EVENT_id_event(event.getIdEvent());
			eventLocationPK.setEVEUN_LOCATION_id_location(location
					.getIdLocation());
			eventLocation.setId(eventLocationPK);
			eventLocation.setStartTime(relation.getStartDate());
			eventLocation.setEndTime(relation.getCloseDate());
			eventLocation.setEveunEvent(event);
			eventLocation.setEveunLocation(location);
			eventLocationDao.save(eventLocation);
			event.addEveunEventLocation(eventLocation);
			location.addEveunEventLocation(eventLocation);
		}

		EveunUser user = userDao.read(action.getUserId());
		user.addEveunEvent(event);
		userDao.update(user);

		return new SaveEventResult(evenDtoConverted(event));
	}

	@Override
	public Class<SaveEventAction> getActionType() {
		// TODO Auto-generated method stub
		return SaveEventAction.class;
	}

	@Override
	public void undo(SaveEventAction action, SaveEventResult result,
			ExecutionContext context) throws ActionException {
		// TODO Auto-generated method stub

	}

	private EventDto evenDtoConverted(EveunEvent eventCreated) {
		EventDto created = new EventDto();
		created.setId(eventCreated.getIdEvent());
		created.setEventName(eventCreated.getEventName());
		created.setStartDate(eventCreated.getStartTime());
		created.setEndDate(eventCreated.getEndTime());
		if (!Strings.isNullOrEmpty(eventCreated.getEventDescription())) {
			created.setEventDescription(eventCreated.getEventDescription());
		}
		return created;
	}

}
