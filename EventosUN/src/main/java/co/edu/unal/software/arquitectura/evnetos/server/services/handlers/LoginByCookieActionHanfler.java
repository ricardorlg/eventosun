package co.edu.unal.software.arquitectura.evnetos.server.services.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import co.edu.unal.software.arquitectura.evnetos.server.entities.EveunEvent;
import co.edu.unal.software.arquitectura.evnetos.server.entities.EveunLocation;
import co.edu.unal.software.arquitectura.evnetos.server.services.dao.UserDao;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.LoginByCookieAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.LoginByCookieResult;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.CurrentUserDto;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.EventDto;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.LocationDto;

import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class LoginByCookieActionHanfler implements
		ActionHandler<LoginByCookieAction, LoginByCookieResult> {
	private Provider<HttpServletRequest> requestProvider;
	@Inject
	UserDao dao;

	@Inject
	LoginByCookieActionHanfler(Provider<HttpServletRequest> requestProvider) {
		this.requestProvider = requestProvider;
	}

	@Override
	public LoginByCookieResult execute(LoginByCookieAction action,
			ExecutionContext context) throws ActionException {
		CurrentUserDto user = null;
		String resultMessage = "";
		HttpServletRequest httpServletRequest = requestProvider.get();
		HttpSession session = httpServletRequest.getSession();
		Object userObj = session.getAttribute("user");
		if (userObj != null && userObj instanceof CurrentUserDto) {
			user = (CurrentUserDto) userObj;
			List<EveunLocation> locations = dao.read(user.getId())
					.getEveunLocations();
			user.setLocations(locationsFromServer(locations));
			List<EveunEvent> eventos = dao.read(user.getId()).getEveunEvents();
			user.setEvents(EventsFromServer(eventos));
		} else {
			resultMessage = "Session Invalida";
		}
		return new LoginByCookieResult(resultMessage, user);
	}

	@Override
	public Class<LoginByCookieAction> getActionType() {
		// TODO Auto-generated method stub
		return LoginByCookieAction.class;
	}

	@Override
	public void undo(LoginByCookieAction action, LoginByCookieResult result,
			ExecutionContext context) throws ActionException {
		// TODO Auto-generated method stub

	}

	private ArrayList<LocationDto> locationsFromServer(List<EveunLocation> locat) {
		ArrayList<LocationDto> listLocations = new ArrayList<LocationDto>();
		for (EveunLocation even : locat) {
			LocationDto loca = new LocationDto(even.getIdLocation(),
					even.getLocationName(), even.getOpenTime(),
					even.getCloseTime());
			if (Strings.isNullOrEmpty(even.getLocationAddress())) {
				loca.setAddres(even.getLocationAddress());
			}
			listLocations.add(loca);
		}
		return listLocations;
	}

	private ArrayList<EventDto> EventsFromServer(List<EveunEvent> events) {
		ArrayList<EventDto> listEvents = new ArrayList<EventDto>();
		for (EveunEvent even : events) {
			EventDto eve = new EventDto();
			eve.setId(even.getIdEvent());
			eve.setEventName(even.getEventName());
			eve.setStartDate(even.getStartTime());
			eve.setEndDate(even.getEndTime());
			if (Strings.isNullOrEmpty(even.getEventDescription())) {
				eve.setEventDescription(even.getEventDescription());
			}
			listEvents.add(eve);
		}
		return listEvents;
	}

}
