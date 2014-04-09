package co.edu.unal.software.arquitectura.evnetos.server.services.handlers;

import co.edu.unal.software.arquitectura.evnetos.server.entities.EveunLocation;
import co.edu.unal.software.arquitectura.evnetos.server.entities.EveunUser;
import co.edu.unal.software.arquitectura.evnetos.server.services.dao.LocationDao;
import co.edu.unal.software.arquitectura.evnetos.server.services.dao.UserDao;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.SaveLocationAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.SaveLocationResult;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.LocationDto;

import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class SaveLocationActionHandler implements
		ActionHandler<SaveLocationAction, SaveLocationResult> {
	@Inject
	UserDao userDao;
	@Inject
	LocationDao locationDao;

	@Override
	public SaveLocationResult execute(SaveLocationAction action,
			ExecutionContext context) throws ActionException {
		try {
			EveunLocation location = new EveunLocation();
			location.setLocationName(action.getName());
			if (!Strings.isNullOrEmpty(action.getAddres())) {
				location.setLocationAddress(action.getAddres());

			}
			location.setOpenTime(action.getOpenTime());
			location.setCloseTime(action.getCloseTime());
			EveunUser user = userDao.read(action.getUserId());
			location.setEveunUser(user);
			location = locationDao.save(location);
			user.addEveunLocation(location);
			userDao.update(user);
			return new SaveLocationResult("", copyFromLocationEntity(location));
		} catch (Exception e) {
			throw new ActionException(e);
		}
	}

	@Override
	public Class<SaveLocationAction> getActionType() {
		// TODO Auto-generated method stub
		return SaveLocationAction.class;
	}

	@Override
	public void undo(SaveLocationAction action, SaveLocationResult result,
			ExecutionContext context) throws ActionException {
		// TODO Auto-generated method stub

	}

	private LocationDto copyFromLocationEntity(EveunLocation location) {
		if (Strings.isNullOrEmpty(location.getLocationAddress())) {
			return new LocationDto(location.getIdLocation(),
					location.getLocationName(), location.getOpenTime(),
					location.getCloseTime());
		} else {
			return new LocationDto(location.getIdLocation(),
					location.getLocationName(), location.getLocationAddress(),
					location.getOpenTime(), location.getCloseTime());
		}
	}

}
