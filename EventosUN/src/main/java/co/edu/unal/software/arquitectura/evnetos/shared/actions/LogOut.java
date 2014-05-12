package co.edu.unal.software.arquitectura.evnetos.shared.actions;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.Out;

@GenDispatch(isSecure = false)
public class LogOut {
	@Out(1)
	private String resultMessage;

}
