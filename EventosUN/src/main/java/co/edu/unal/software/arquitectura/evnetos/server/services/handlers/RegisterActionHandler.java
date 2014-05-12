package co.edu.unal.software.arquitectura.evnetos.server.services.handlers;

import javax.persistence.PersistenceException;

import co.edu.unal.software.arquitectura.evnetos.server.entities.EveunUser;
import co.edu.unal.software.arquitectura.evnetos.server.services.dao.UserDao;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.RegisterAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.RegisterResult;

import com.google.common.base.Strings;
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
		user.setEmail(accion.getCorreo());
		user.setUsername(accion.getUsername().toLowerCase());
		user.setPassword(accion.getClave());
		user.setRole(accion.getRole());
		if (!Strings.isNullOrEmpty(accion.getTelefono())) {
			user.setPhone(accion.getTelefono());
		}
		if (!Strings.isNullOrEmpty(accion.getApellidos())) {
			user.setLastname(accion.getApellidos());
		}
		try {
			userDao.save(user);
			System.out.println(user.getIdUser());
			return new RegisterResult("Usuario Registrado con Exito");
		} catch (PersistenceException e) {
			throw new ActionException("UserName en uso", e.getCause());
		}

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
