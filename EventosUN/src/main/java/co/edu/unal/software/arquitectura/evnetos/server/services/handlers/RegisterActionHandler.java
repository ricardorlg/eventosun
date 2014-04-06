package co.edu.unal.software.arquitectura.evnetos.server.services.handlers;

import co.edu.unal.software.arquitectura.evnetos.server.entities.EveunUser;
import co.edu.unal.software.arquitectura.evnetos.server.services.dao.UserDao;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.RegisterAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.RegisterResult;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class RegisterActionHandler implements
		ActionHandler<RegisterAction, RegisterResult> {
	@Inject
	UserDao userDao;

	@Override
	public RegisterResult execute(RegisterAction accion, ExecutionContext arg1)
			throws ActionException {
		// TODO Auto-generated method stub
		EveunUser user = new EveunUser();
		user.setName(accion.getNombres());
		user.setLastname(accion.getApellidos());
		user.setEmail(accion.getCorreo());
		user.setUsername(accion.getUsername());
		user.setPassword(accion.getClave());
		if (accion.getTelefono() != null) {
			user.setPhone(accion.getTelefono().toString());
		}
		userDao.save(user);
		System.out.println(user.getIdUser());
		return new RegisterResult("Usuario Registrado con Exito");
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
