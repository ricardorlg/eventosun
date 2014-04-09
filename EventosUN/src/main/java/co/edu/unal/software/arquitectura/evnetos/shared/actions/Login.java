package co.edu.unal.software.arquitectura.evnetos.shared.actions;

import co.edu.unal.software.arquitectura.evnetos.shared.dto.CurrentUserDto;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.annotation.Out;

@GenDispatch
public class Login {
	@In(1)
	private String username;
	@In(2)
	private String password;
	@Out(1)
	private String resultMessage;
	@Out(2)
	private CurrentUserDto user;
}
