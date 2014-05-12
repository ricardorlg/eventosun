package co.edu.unal.software.arquitectura.evnetos.server.services.handlers;

import co.edu.unal.software.arquitectura.evnetos.server.services.dao.EventLocationDao;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.VerifyDisponibilityAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.VerifyDisponibilityResult;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class VerifyDisponibilityActionHanler implements
		ActionHandler<VerifyDisponibilityAction, VerifyDisponibilityResult> {
	@Inject
	EventLocationDao eventLocationDao;

	@Override
	public VerifyDisponibilityResult execute(VerifyDisponibilityAction action,
			ExecutionContext context) throws ActionException {
		// TODO Auto-generated method stub
		boolean r = eventLocationDao.findByLocationAndStartDate(
				action.getLocationId(), action.getStartDate()) > 0;
		return new VerifyDisponibilityResult(r);
	}

	@Override
	public Class<VerifyDisponibilityAction> getActionType() {
		// TODO Auto-generated method stub
		return VerifyDisponibilityAction.class;
	}

	@Override
	public void undo(VerifyDisponibilityAction action,
			VerifyDisponibilityResult result, ExecutionContext context)
			throws ActionException {
		// TODO Auto-generated method stub

	}

}
