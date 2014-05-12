package co.edu.unal.software.arquitectura.evnetos.client.application.adminevents;

import java.util.Date;

import co.edu.unal.software.arquitectura.evnetos.shared.dto.EventDto;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface EventProperties extends PropertyAccess<EventDto> {
	@Path("id")
	ModelKeyProvider<EventDto> key();

	@Path("eventName")
	LabelProvider<EventDto> nameLabel();

	ValueProvider<EventDto, String> eventName();

	ValueProvider<EventDto, Date> startDate();

	ValueProvider<EventDto, Date> endDate();
}
