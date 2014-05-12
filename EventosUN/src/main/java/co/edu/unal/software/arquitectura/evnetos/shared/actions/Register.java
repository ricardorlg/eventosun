package co.edu.unal.software.arquitectura.evnetos.shared.actions;

import co.edu.unal.software.arquitectura.evnetos.shared.util.UserRole;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.annotation.Optional;
import com.gwtplatform.dispatch.annotation.Out;

@GenDispatch(isSecure = false)
public class Register {
	@In(1)
	String nombres;
	@In(2)
	@Optional
	String apellidos;
	@In(3)
	String correo;
	@In(4)
	String username;
	@In(5)
	@Optional
	String telefono;
	@In(6)
	String clave;
	@In(7)
	UserRole role;
	@Out(1)
	String mensaje;
}
