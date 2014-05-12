package co.edu.unal.software.arquitectura.evnetos.shared.actions;

import co.edu.unal.software.arquitectura.evnetos.shared.dto.CurrentUserDto;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.Out;

@GenDispatch(isSecure = false)
public class LoginByCookie {
	@Out(1)
	private String resultMessage;
	@Out(2)
	private CurrentUserDto user;

}
