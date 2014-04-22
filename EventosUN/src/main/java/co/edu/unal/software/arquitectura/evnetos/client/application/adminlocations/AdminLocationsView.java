package co.edu.unal.software.arquitectura.evnetos.client.application.adminlocations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import co.edu.unal.software.arquitectura.evnetos.shared.dto.CurrentUserDto;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.LocationDto;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridView;

public class AdminLocationsView extends
		ViewWithUiHandlers<AdminLocationsUiHandlers> implements
		AdminLocationsPresenter.MyView {
	private static final LocationProperties gridProperties = GWT
			.create(LocationProperties.class);

	interface Binder extends UiBinder<Widget, AdminLocationsView> {
	}

	private ContentPanel widget;
	@UiField(provided = true)
	ColumnModel<LocationDto> columnModel;
	@UiField(provided = true)
	ListStore<LocationDto> listStore;
	@UiField
	GridView<LocationDto> gridView;
	@UiField
	Grid<LocationDto> grid;

	@UiField
	TextButton add;
	@UiField
	TextButton edit;
	@UiField
	TextButton delete;
	private Binder uiBinder;
	private CurrentUserDto currentUser;

	@Inject
	AdminLocationsView(Binder uiBinder, CurrentUserDto currentUserDto) {
		this.uiBinder = uiBinder;
		this.currentUser = currentUserDto;
		columnModel = initColumnModel();
		listStore = initListStore();
		widget = (ContentPanel) uiBinder.createAndBindUi(this);
		gridView.setAutoExpandColumn(columnModel.getColumn(0));
gridView.setAutoFill(true);
	}

	@Override
	public Widget asWidget() {

		return widget;

	}

	private ListStore<LocationDto> initListStore() {
		ListStore<LocationDto> listStore = new ListStore<LocationDto>(
				gridProperties.key());
		System.out.println(currentUser.getLocations().size());
		System.out.println(listStore.addAll(currentUser.getLocations()));
		return listStore;
	}

	private ColumnModel<LocationDto> initColumnModel() {
		ColumnConfig<LocationDto, String> nameCol = new ColumnConfig<LocationDto, String>(
				gridProperties.name(), 50, "Nombre Espacio");
		ColumnConfig<LocationDto, Date> openTime = new ColumnConfig<LocationDto, Date>(
				gridProperties.openTime(), 100, "Hora de apertura");
		openTime.setCell(new DateCell(DateTimeFormat.getFormat("hh:mm a")));
		ColumnConfig<LocationDto, Date> closeTime = new ColumnConfig<LocationDto, Date>(
				gridProperties.closeTime(), 100, "Hora de cierre");
		closeTime.setCell(new DateCell(DateTimeFormat.getFormat("hh:mm a")));

		List<ColumnConfig<LocationDto, ?>> columns = new ArrayList<ColumnConfig<LocationDto, ?>>();
		columns.add(nameCol);
		columns.add(openTime);
		columns.add(closeTime);
		ColumnModel<LocationDto> columnModel = new ColumnModel<LocationDto>(
				columns);
		return columnModel;
	}

	@UiHandler("add")
	public void addLocation(SelectEvent e) {
		if (getUiHandlers() != null) {
			getUiHandlers().OpenAddLocationEditor();
		}
	}

	@Override
	public ListStore<LocationDto> getListStore() {
		return listStore;
	}

	@Override
	public void update() {
		grid.show();
		widget.forceLayout();
	}

}
