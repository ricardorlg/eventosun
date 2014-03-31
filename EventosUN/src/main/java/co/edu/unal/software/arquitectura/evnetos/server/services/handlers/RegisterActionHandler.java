package co.edu.unal.software.arquitectura.evnetos.server.services.handlers;

import co.edu.unal.software.arquitectura.evnetos.shared.actions.RegisterAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.RegisterResult;

import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class RegisterActionHandler implements
		ActionHandler<RegisterAction, RegisterResult> {

	@Override
	public RegisterResult execute(RegisterAction arg0, ExecutionContext arg1)
			throws ActionException {
		// TODO Auto-generated method stub
		return new RegisterResult("Prueba Rpc");
	}

	@Override
	public Class<RegisterAction> getActionType() {
		// TODO Auto-generated method stub
		return RegisterAction.class;
	}

	@Override
	public void undo(RegisterAction arg0, RegisterResult arg1,
			ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub

	}

}
