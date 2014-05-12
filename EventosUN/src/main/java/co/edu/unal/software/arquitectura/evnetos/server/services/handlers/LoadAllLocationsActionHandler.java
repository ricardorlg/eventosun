package co.edu.unal.software.arquitectura.evnetos.server.services.handlers;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.software.arquitectura.evnetos.server.entities.EveunLocation;
import co.edu.unal.software.arquitectura.evnetos.server.services.dao.LocationDao;
import co.edu.unal.software.arquitectura.evnetos.server.util.GeneralUtil;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.LoadAllLocationsAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.LoadAllLocationsResult;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.LocationDto;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class LoadAllLocationsActionHandler implements
		ActionHandler<LoadAllLocationsAction, LoadAllLocationsResult> {

	@Inject
	LocationDao locationDao;

	@Override
	public LoadAllLocationsResult execute(LoadAllLocationsAction action,
			ExecutionContext context) throws ActionException {
		try {
			List<EveunLocation> locationsResultLits = locationDao
					.readAllLocations();
			List<LocationDto> locationDtoList = new ArrayList<LocationDto>();
			for (EveunLocation location : locationsResultLits) {
				locationDtoList.add(GeneralUtil
						.copyFromLocationEntity(location));
			}
			return new LoadAllLocationsResult("", locationDtoList);
		} catch (Exception e) {
			return new LoadAllLocationsResult(e.getLocalizedMessage(), null);
		}

	}

	@Override
	public Class<LoadAllLocationsAction> getActionType() {
		// TODO Auto-generated method stub
		return LoadAllLocationsAction.class;
	}

	@Override
	public void undo(LoadAllLocationsAction action,
			LoadAllLocationsResult result, ExecutionContext context)
			throws ActionException {
		// TODO Auto-generated method stub

	}

}
