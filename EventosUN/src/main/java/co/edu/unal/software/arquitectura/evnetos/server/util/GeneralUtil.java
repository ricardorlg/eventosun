package co.edu.unal.software.arquitectura.evnetos.server.util;

import co.edu.unal.software.arquitectura.evnetos.server.entities.EveunLocation;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.CurrentUserDto;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.LocationDto;

import com.google.common.base.Strings;

public class GeneralUtil {

	public void copyUser(CurrentUserDto src, CurrentUserDto destiny) {
	}

	public static LocationDto copyFromLocationEntity(EveunLocation location) {
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
