package co.edu.unal.software.arquitectura.evnetos.server.services.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import co.edu.unal.software.arquitectura.evnetos.server.entities.EveunLocation;
import co.edu.unal.software.arquitectura.evnetos.server.entities.EveunUser;
import co.edu.unal.software.arquitectura.evnetos.server.services.dao.UserDao;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.LoginAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.LoginResult;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.CurrentUserDto;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.LocationDto;
import co.edu.unal.software.arquitectura.evnetos.shared.util.UserRole;

import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class LoginActionHandler implements
		ActionHandler<LoginAction, LoginResult> {
	private Provider<HttpServletRequest> requestProvider;
	@Inject
	private UserDao userDao;

	@Inject
	LoginActionHandler(Provider<HttpServletRequest> requestProvider) {
		this.requestProvider = requestProvider;
	}

	@Override
	public LoginResult execute(LoginAction action, ExecutionContext context)
			throws ActionException {
		try {
			EveunUser user = userDao.read(action.getUsername());
			if (user.getPassword().equals(action.getPassword())) {
				CurrentUserDto userDto = new CurrentUserDto(user.getIdUser(),
						false, user.getUsername(), "", user.getRole());
				if (user.getRole() == UserRole.LOCATION_ADMIN) {
					userDto.setLocations(locationsFromServer(user
							.getEveunLocations()));
				}
				storeUserInSession(userDto);
				return new LoginResult("", userDto);
			} else {
				return new LoginResult(
						"Nombre de usuario o contraseña incorrectos", null);
			}
		} catch (NoResultException e) {
			throw new ActionException("Usuario no Encontrado", e.getCause());
		}
	}

	@Override
	public Class<LoginAction> getActionType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void undo(LoginAction action, LoginResult result,
			ExecutionContext context) throws ActionException {
		// TODO Auto-generated method stub

	}

	private void storeUserInSession(CurrentUserDto user) {
		HttpServletRequest httpServletRequest = requestProvider.get();
		HttpSession session = httpServletRequest.getSession(true);
		user.setLoggedIn(true);
		user.setSessionId(session.getId());
		session.setAttribute("user", user);
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
}
