package co.edu.unal.software.arquitectura.evnetos.client.application.adminevents.addevent;

import java.util.Date;
import java.util.List;

import co.edu.unal.software.arquitectura.evnetos.client.resources.Resources;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.rhemsolutions.client.GXTPopupViewWithUiHandlers;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.core.client.util.DateWrapper;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.ParseErrorEvent;
import com.sencha.gxt.widget.core.client.event.ParseErrorEvent.ParseErrorHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.TimeField;
import com.sencha.gxt.widget.core.client.form.validator.AbstractValidator;
import com.sencha.gxt.widget.core.client.form.validator.EmptyValidator;
import com.sencha.gxt.widget.core.client.form.validator.MaxLengthValidator;
import com.sencha.gxt.widget.core.client.form.validator.MinDateValidator;
import com.sencha.gxt.widget.core.client.form.validator.MinLengthValidator;
import com.sencha.gxt.widget.core.client.info.Info;

public class AddEventView extends
		GXTPopupViewWithUiHandlers<AddEventUiHandlers> implements
		AddEventPresenter.MyView {

	private TextField eventName;
	private Window window;
	private FormPanel form;
	private TimeField openTime;
	private TimeField closeTime;
	private Resources resources;
	private TextArea eventDescription;
	private DateField openDate;
	private DateField closeDate;
	private VerticalLayoutContainer p2;

	public Widget asWidget() {

		return window;
	}

	@Inject
	AddEventView(EventBus eventBus, final Resources resources) {
		super(eventBus);
		this.resources = resources;
		createForm1();
	}

	private void createForm1() {
		window = new Window();
		window.setHeadingText("Registro de Eventos");
		window.setHeight(500);
		window.setWidth(700);
		window.setButtonAlign(BoxLayoutPack.CENTER);
		form = new FormPanel();
		form.setSize("100%", "100%");
		form.setLabelAlign(LabelAlign.TOP);
		TabPanel tabs = new TabPanel();
		form.setWidget(tabs);

		VerticalLayoutContainer p = new VerticalLayoutContainer();
		tabs.add(p, "Detalles del Evento");

		eventName = new TextField();
		eventName.setAllowBlank(false);
		eventName.setEmptyText("Ingresa el Nombre del Evento...");
		eventName.addValidator(new MinLengthValidator(3));
		eventName.addValidator(new MaxLengthValidator(50));
		p.add(new FieldLabel(eventName, "*Nombre del Evento"),
				new VerticalLayoutData(1, -1));
		eventDescription = new TextArea();

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
					if (value.before(openDate.getCurrentValue()))
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
		p.add(new FieldLabel(eventDescription, "Descripcion del evento"),
				new VerticalLayoutData(1, 1));
		p = new VerticalLayoutContainer();
		p2 = new VerticalLayoutContainer();
		p2.setScrollMode(ScrollMode.AUTOY);
		p2.setAdjustForScroll(true);

		TextButton newLocation = new TextButton("Agregar Espacio",
				resources.iconAccept());
		p.add(newLocation, new VerticalLayoutData(1, -1));
		p.add(p2, new VerticalLayoutData(1, 1));
		tabs.add(p, "Detalles del espacio");
		newLocation.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				if (getUiHandlers() != null) {
					getUiHandlers().addLocation();
				}

			}
		});
		TextButton registerButton = new TextButton("Registrar",
				resources.iconAdd());
		registerButton.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				if (!form.isValid()) {
					if (getUiHandlers() != null) {
						getUiHandlers().errorMessage(
								"Existan campos vacios en el formulario");
					}
					return;
				}
				if (getUiHandlers() != null) {
					getUiHandlers().save();
				}

			}
		});
		TextButton cancelButton = new TextButton("Cancelar",
				resources.iconDelete());
		cancelButton.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				if (getUiHandlers() != null) {
					getUiHandlers().cancel();
				}
			}
		});
		window.addButton(registerButton);
		window.addButton(cancelButton);
		window.add(form);
	}

	@Override
	public void hide() {
		super.hide();
		form.reset();
		setInSlot(AddEventPresenter.slotContent, null);
		form.forceLayout();
	}

	@Override
	public void setInSlot(Object slot, IsWidget content) {
		if (slot == AddEventPresenter.slotContent) {
			p2.clear();
			if (content != null) {
				p2.add(content, new VerticalLayoutData(1, -1));
			}
		} else {
			super.setInSlot(slot, content);
		}
	}

	@Override
	public void addToSlot(Object slot, IsWidget content) {
		if (slot == AddEventPresenter.slotContent) {
			if (content != null) {
				p2.add(content, new VerticalLayoutData(1, -1, new Margins(10)));

			}
		} else {
			super.setInSlot(slot, content);
		}
	}

	@Override
	public TextField getEventName() {
		return eventName;
	}

	@Override
	public TextArea getEventDescription() {
		return eventDescription;
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
