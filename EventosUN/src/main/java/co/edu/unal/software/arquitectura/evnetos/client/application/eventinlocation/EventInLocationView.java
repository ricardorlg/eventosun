package co.edu.unal.software.arquitectura.evnetos.client.application.eventinlocation;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.rhemsolutions.client.GXTPopupViewImpl;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;

public class EventInLocationView extends GXTPopupViewImpl implements
		EventInLocationPresenter.MyView {

	private Window window;
	private TextField eventName;
	private TextArea eventDescription;
	private TextField locationName;
	private TextField openDate;
	private TextField closeDate;

	@Inject
	EventInLocationView(EventBus eventBus) {
		super(eventBus);
		window = new Window();
		window.setHeadingText("Informacion del Evento");
		window.setHeight(300);
		window.setWidth(500);
		window.setButtonAlign(BoxLayoutPack.CENTER);
		FormPanel form = new FormPanel();
		form.setSize("100%", "100%");
		form.setLabelAlign(LabelAlign.TOP);

		VerticalLayoutContainer p = new VerticalLayoutContainer();
		form.setWidget(p);

		eventName = new TextField();
		eventName.setReadOnly(true);
		p.add(new FieldLabel(eventName, "*Nombre del Evento"),
				new VerticalLayoutData(1, -1));
		locationName = new TextField();
		locationName.setReadOnly(true);
		p.add(new FieldLabel(locationName, "*Nombre del Espacio"),
				new VerticalLayoutData(1, -1));
		eventDescription = new TextArea();

		openDate = new TextField();
		openDate.setReadOnly(true);

		p.add(new FieldLabel(openDate, "Fecha de inicio"),
				new VerticalLayoutData(1, -1));

		closeDate = new TextField();
		closeDate.setReadOnly(true);
		p.add(new FieldLabel(closeDate, "Fecha de cierre"),
				new VerticalLayoutData(1, -1));
		p.add(new FieldLabel(eventDescription, "Descripcion del evento"),
				new VerticalLayoutData(1, 1));
		window.add(form);
	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return window;
	}

	@Override
	public void loadData(String eventNameI, String description,
			String locationNameI, String openDateI, String closeDateI) {
		eventName.setText(eventNameI);
		locationName.setText(locationNameI);
		openDate.setText(openDateI);
		closeDate.setText(closeDateI);
		eventDescription.setText(description);

	}
}
