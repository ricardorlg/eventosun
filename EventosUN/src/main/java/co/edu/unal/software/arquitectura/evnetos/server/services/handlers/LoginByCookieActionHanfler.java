package co.edu.unal.software.arquitectura.evnetos.server.services.handlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import co.edu.unal.software.arquitectura.evnetos.shared.actions.LoginByCookieAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.LoginByCookieResult;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.CurrentUserDto;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class LoginByCookieActionHanfler implements
		ActionHandler<LoginByCookieAction, LoginByCookieResult> {
	private Provider<HttpServletRequest> requestProvider;

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

}
