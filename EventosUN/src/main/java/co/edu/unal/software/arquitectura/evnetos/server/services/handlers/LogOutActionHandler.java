package co.edu.unal.software.arquitectura.evnetos.server.services.handlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import co.edu.unal.software.arquitectura.evnetos.shared.actions.LogOutAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.LogOutResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class LogOutActionHandler implements
		ActionHandler<LogOutAction, LogOutResult> {
	private Provider<HttpServletRequest> requestProvider;

	@Inject
	LogOutActionHandler(Provider<HttpServletRequest> requestProvider) {
		this.requestProvider = requestProvider;
	}

	@Override
	public LogOutResult execute(LogOutAction action, ExecutionContext context)
			throws ActionException {
		try {
			HttpServletRequest httpServletRequest = requestProvider.get();
			HttpSession session = httpServletRequest.getSession();
			session.removeAttribute("user");
			return new LogOutResult("");
		} catch (Exception e) {
			throw new ActionException(e.getMessage());
		}
	}

	@Override
	public Class<LogOutAction> getActionType() {
		// TODO Auto-generated method stub
		return LogOutAction.class;
	}

	@Override
	public void undo(LogOutAction action, LogOutResult result,
			ExecutionContext context) throws ActionException {
		// TODO Auto-generated method stub

	}

}
