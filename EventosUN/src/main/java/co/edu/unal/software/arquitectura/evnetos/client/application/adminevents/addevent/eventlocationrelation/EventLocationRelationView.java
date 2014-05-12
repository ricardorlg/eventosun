package co.edu.unal.software.arquitectura.evnetos.client.application.adminevents.addevent.eventlocationrelation;

import java.util.Date;
import java.util.List;

import co.edu.unal.software.arquitectura.evnetos.client.application.adminlocations.LocationProperties;
import co.edu.unal.software.arquitectura.evnetos.client.resources.Resources;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.LocationDto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.core.client.util.DateWrapper;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.ParseErrorEvent;
import com.sencha.gxt.widget.core.client.event.ParseErrorEvent.ParseErrorHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FieldSet;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.form.TimeField;
import com.sencha.gxt.widget.core.client.form.validator.AbstractValidator;
import com.sencha.gxt.widget.core.client.form.validator.EmptyValidator;
import com.sencha.gxt.widget.core.client.form.validator.MinDateValidator;
import com.sencha.gxt.widget.core.client.info.Info;

public class EventLocationRelationView extends
		ViewWithUiHandlers<EventLocationRelationUiHandlers> implements
		EventLocationRelationPresenter.MyView {

	private FramedPanel form2;
	private LocationProperties locationProperties;
	private ListStore<LocationDto> store;
	private DateField openDate;
	private TimeField openTime;
	private DateField closeDate;
	private TimeField closeTime;
	private Resources resources;
	private TextButton verificar;
	private ComboBox<LocationDto> locations;

	@Inject
	EventLocationRelationView(final Resources resources) {
		this.resources = resources;
		createForm2();
	}

	private void createForm2() {
		form2 = new FramedPanel();
		form2.setHeaderVisible(true);
		form2.setWidth("100%");
		form2.setCollapsible(true);
		final FormPanel panel = new FormPanel();
		panel.setLabelAlign(LabelAlign.TOP);

		FieldSet fieldSet = new FieldSet();
		fieldSet.setHeadingText("Informacion del Espacio");
		form2.add(panel, new MarginData(10));
		panel.add(fieldSet);
		VerticalLayoutContainer p = new VerticalLayoutContainer();
		fieldSet.add(p);
		locationProperties = GWT.create(LocationProperties.class);
		store = new ListStore<LocationDto>(locationProperties.key());
		locations = new ComboBox<LocationDto>(store,
				locationProperties.nameLabel());
		locations.setAllowBlank(false);
		locations.setForceSelection(true);
		locations.setTriggerAction(TriggerAction.ALL);
		locations.setEmptyText("Escoge un espacio");
		locations.addValueChangeHandler(new ValueChangeHandler<LocationDto>() {

			@Override
			public void onValueChange(ValueChangeEvent<LocationDto> event) {
				form2.setHeadingText(event.getValue().getName());
			}
		});

		p.add(new FieldLabel(locations, "Espacios"), new VerticalLayoutData(1,
				-1));

		openDate = new DateField();
		openDate.addValidator(new EmptyValidator<Date>());
		openDate.addValidator(new MinDateValidator(new DateWrapper()
				.clearTime().asDate()));
		p.add(new FieldLabel(openDate, "Fecha de inicio"),
				new VerticalLayoutData(1, -1));
		openTime = new TimeField();
		openTime.setAllowBlank(false);
		openTime.setFormat(DateTimeFormat.getFormat("hh:mm a"));
		openTime.setIncrement(30);
		openTime.addParseErrorHandler(new ParseErrorHandler() {

			@Override
			public void onParseError(ParseErrorEvent event) {
				Info.display("Parse Error", event.getErrorValue()
						+ " could not be parsed as a valid time");
			}
		});

		p.add(new FieldLabel(openTime, "Hora de Inicio"),
				new VerticalLayoutData(1, -1));
		closeDate = new DateField();
		closeDate.addValidator(new EmptyValidator<Date>());
		closeDate.addValidator(new AbstractValidator<Date>() {
			String messageA = "You should first choose an open date";
			String messageB = "The close date shoul be equals or greater to open Date";

			@Override
			public List<EditorError> validate(Editor<Date> editor, Date value) {

				if (openDate.getValue() == null) {
					return createError(closeDate, messageA, value);
				} else {
					if (value != null
							&& value.before(openDate.getCurrentValue()))
						return createError(editor, messageB, value);
				}
				return null;
			}
		});
		p.add(new FieldLabel(closeDate, "Fecha de cierre"),
				new VerticalLayoutData(1, -1));
		closeTime = new TimeField();
		closeTime.setAllowBlank(false);
		closeTime.setFormat(DateTimeFormat.getFormat("hh:mm a"));
		closeTime.addParseErrorHandler(new ParseErrorHandler() {

			@Override
			public void onParseError(ParseErrorEvent event) {
				Info.display("Parse Error", event.getErrorValue()
						+ " could not be parsed as a valid time");
			}
		});

		closeTime.setIncrement(30);
		p.add(new FieldLabel(closeTime, "Hora de cierre"),
				new VerticalLayoutData(1, -1));
		verificar = new TextButton("Verifica Disponibilidad",
				resources.iconAccept());
		verificar.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				if (panel.isValid()) {
					if (getUiHandlers() != null) {
						getUiHandlers().verificarDisponibilidad();
					}
				}

			}
		});
		form2.addButton(verificar);
		form2.addButton(new TextButton("Quitar", resources.iconDelete()));

	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return form2;
	}

	@Override
	public ListStore<LocationDto> getStore() {
		return store;
	}

	@Override
	public void setStore(ListStore<LocationDto> store) {
		this.store = store;
	}

	@Override
	public ComboBox<LocationDto> getLocations() {
		return locations;
	}

	@Override
	public DateField getOpenDate() {
		return openDate;
	}

	@Override
	public TimeField getOpenTime() {
		return openTime;
	}

	@Override
	public DateField getCloseDate() {
		return closeDate;
	}

	@Override
	public TimeField getCloseTime() {
		return closeTime;
	}

	@Override
	public Date getOpenDateValue() {
		DateWrapper timeValueWrapper = new DateWrapper(openTime.getValue());
		DateWrapper dateValueWrapper = new DateWrapper(openDate.getValue());
		// copy time into date
		dateValueWrapper = dateValueWrapper.clearTime();
		dateValueWrapper = dateValueWrapper.addHours(timeValueWrapper
				.getHours());
		dateValueWrapper = dateValueWrapper.addMinutes(timeValueWrapper
				.getMinutes());
		dateValueWrapper = dateValueWrapper.addSeconds(timeValueWrapper
				.getSeconds());
		// return new Date containing date and time part
		return new Date(dateValueWrapper.getTime());
	}

	@Override
	public Date getCloseDateValue() {
		DateWrapper timeValueWrapper = new DateWrapper(closeTime.getValue());
		DateWrapper dateValueWrapper = new DateWrapper(closeDate.getValue());
		// copy time into date
		dateValueWrapper = dateValueWrapper.clearTime();
		dateValueWrapper = dateValueWrapper.addHours(timeValueWrapper
				.getHours());
		dateValueWrapper = dateValueWrapper.addMinutes(timeValueWrapper
				.getMinutes());
		dateValueWrapper = dateValueWrapper.addSeconds(timeValueWrapper
				.getSeconds());
		// return new Date containing date and time part
		return new Date(dateValueWrapper.getTime());
	}
}
