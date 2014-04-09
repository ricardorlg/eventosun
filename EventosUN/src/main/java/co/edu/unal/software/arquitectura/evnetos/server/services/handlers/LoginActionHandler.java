package co.edu.unal.software.arquitectura.evnetos.server.services.handlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import co.edu.unal.software.arquitectura.evnetos.server.entities.EveunUser;
import co.edu.unal.software.arquitectura.evnetos.server.services.dao.UserDao;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.LoginAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.LoginResult;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.CurrentUserDto;

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
		EveunUser user = userDao.read(action.getUsername());
		if (user.getPassword().equals(action.getPassword())) {
			CurrentUserDto userDto = new CurrentUserDto(false,
					user.getUsername(), "", user.getRole());
			storeUserInSession(userDto);
			return new LoginResult("", userDto);
		} else {
			return new LoginResult(
					"Nombre de usuario o contraseña incorrectos", null);
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

}
