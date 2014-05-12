package co.edu.unal.software.arquitectura.evnetos.client.application.adminlocations.addlocation;

import co.edu.unal.software.arquitectura.evnetos.client.resources.Resources;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.rhemsolutions.client.GXTPopupViewWithUiHandlers;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.ParseErrorEvent;
import com.sencha.gxt.widget.core.client.event.ParseErrorEvent.ParseErrorHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.TimeField;
import com.sencha.gxt.widget.core.client.form.validator.MaxLengthValidator;
import com.sencha.gxt.widget.core.client.form.validator.MinLengthValidator;
import com.sencha.gxt.widget.core.client.info.Info;

public class AddLocationView extends
		GXTPopupViewWithUiHandlers<AddLocationUiHandlers> implements
		AddLocationPresenter.MyView {

	private TextField locationName;
	private Window window;
	private FormPanel form;
	private TextField locationAdress;
	private TimeField openTime;
	private TimeField closeTime;
	private Resources resources;

	public Widget asWidget() {

		return window;
	}

	@Inject
	AddLocationView(EventBus eventBus, final Resources resources) {
		super(eventBus);
		this.resources = resources;
		createForm1();
	}

	private void createForm1() {
		window = new Window();
		window.setHeadingText("Registro de Espacio");
		window.setBodyStyle("background: none; padding: 15px");
		window.setWidth(500);
		window.setModal(true);
		window.setActive(true);
		window.setBodyBorder(true);
		window.setShadow(true);
		window.setButtonAlign(BoxLayoutPack.CENTER);
		form = new FormPanel();

		VerticalLayoutContainer p = new VerticalLayoutContainer();
		form.add(p);

		locationName = new TextField();
		locationName.setAllowBlank(false);
		locationName.setEmptyText("Ingresa el Nombre del espacio...");
		locationName.addValidator(new MinLengthValidator(3));
		locationName.addValidator(new MaxLengthValidator(50));
		p.add(new FieldLabel(locationName, "*Nombre del Espacio"),
				new VerticalLayoutData(1, -1));
		locationAdress = new TextField();
		locationAdress.setEmptyText("Ingresa la direccion del espacio...");
		locationAdress.setAllowBlank(true);

		p.add(new FieldLabel(locationAdress, "Direccion"),
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

		p.add(new FieldLabel(openTime, "Hora de Apertura"),
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
		TextButton registerButton = new TextButton("Registrar",
				resources.iconAccept());
		registerButton.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				if (!form.isValid()) {
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
				form.reset();
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
	public TextField getLocationName() {
		return locationName;
	}

	@Override
	public TextField getLocationAdress() {
		return locationAdress;
	}

	@Override
	public TimeField getOpenTime() {
		return openTime;
	}

	@Override
	public TimeField getCloseTime() {
		return closeTime;
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		super.hide();
		form.reset();
	}

}
