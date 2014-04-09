package co.edu.unal.software.arquitectura.evnetos.client.place;

import co.edu.unal.software.arquitectura.evnetos.shared.dto.CurrentUserDto;
import co.edu.unal.software.arquitectura.evnetos.shared.util.UserRole;

import com.google.inject.Inject;
import com.gwtplatform.mvp.client.proxy.Gatekeeper;

public class AdminLocationGateKeeper implements Gatekeeper {

	private CurrentUserDto currentUser;

	@Inject
	AdminLocationGateKeeper(CurrentUserDto currentUserDto) {
		this.currentUser = currentUserDto;
	}

	@Override
	public boolean canReveal() {
		return (currentUser.isLoggedIn() == true && currentUser.getRole() == UserRole.LOCATION_ADMIN);
	}

}
