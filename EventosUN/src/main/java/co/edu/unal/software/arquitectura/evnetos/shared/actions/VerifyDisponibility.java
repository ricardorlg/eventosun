package co.edu.unal.software.arquitectura.evnetos.shared.actions;

import java.util.Date;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.annotation.Out;

@GenDispatch(isSecure = false)
public class VerifyDisponibility {
	@In(1)
	int locationId;
	@In(2)
	Date startDate;
	@Out(1)
	boolean disponible;
}
