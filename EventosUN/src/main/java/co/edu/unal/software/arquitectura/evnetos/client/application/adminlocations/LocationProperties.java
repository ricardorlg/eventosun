package co.edu.unal.software.arquitectura.evnetos.client.application.adminlocations;

import java.util.Date;

import co.edu.unal.software.arquitectura.evnetos.shared.dto.LocationDto;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface LocationProperties extends PropertyAccess<LocationDto> {
	@Path("id")
	ModelKeyProvider<LocationDto> key();

	@Path("name")
	LabelProvider<LocationDto> nameLabel();

	ValueProvider<LocationDto, String> name();

	ValueProvider<LocationDto, Date> openTime();

	ValueProvider<LocationDto, Date> closeTime();

}
