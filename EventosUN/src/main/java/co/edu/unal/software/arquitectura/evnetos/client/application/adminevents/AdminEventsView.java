package co.edu.unal.software.arquitectura.evnetos.client.application.adminevents;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import co.edu.unal.software.arquitectura.evnetos.client.resources.Resources;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.CurrentUserDto;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.EventDto;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridSelectionModel;
import com.sencha.gxt.widget.core.client.grid.GridView;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

public class AdminEventsView extends ViewWithUiHandlers<AdminEventsUiHandlers>
		implements AdminEventsPresenter.MyView {
	private static final EventProperties gridProperties = GWT
			.create(EventProperties.class);

	interface Binder extends UiBinder<Widget, AdminEventsView> {
	}

	private ContentPanel widget;

	@UiField(provided = true)
	ColumnModel<EventDto> columnModel;

	@UiField(provided = true)
	ListStore<EventDto> listStore;

	@UiField
	GridView<EventDto> gridView;

	@UiField
	Grid<EventDto> grid;

	@UiField
	TextButton add;
	@UiField
	TextButton edit;
	@UiField
	TextButton delete;

	@Inject
	AdminEventsView(Binder uiBinder, CurrentUserDto currentUserDto,
			final Resources resources) {

		columnModel = initColumnModel();
		listStore = initListStore();

		widget = (ContentPanel) uiBinder.createAndBindUi(this);
		Menu m = new Menu();

		grid.setContextMenu(m);
		GridSelectionModel<EventDto> sm = new GridSelectionModel<EventDto>();
		sm.setSelectionMode(SelectionMode.SINGLE);
		grid.setSelectionModel(sm);
		gridView.setAutoExpandColumn(columnModel.getColumn(0));
		gridView.setAutoFill(true);
		MenuItem edit = new MenuItem();
		edit.setText("edit");
		edit.setIcon(resources.iconPencil());
		m.add(edit);
		MenuItem delete = new MenuItem();
		delete.setText("Delete");
		delete.setIcon(resources.iconDelete());
		m.add(delete);

	}

	@Override
	public Widget asWidget() {

		return widget;

	}

	private ListStore<EventDto> initListStore() {
		ListStore<EventDto> listStore = new ListStore<EventDto>(
				gridProperties.key());

		return listStore;
	}

	private ColumnModel<EventDto> initColumnModel() {
		ColumnConfig<EventDto, String> nameCol = new ColumnConfig<EventDto, String>(
				gridProperties.eventName(), 50, "Nombre del evento");
		ColumnConfig<EventDto, Date> startDate = new ColumnConfig<EventDto, Date>(
				gridProperties.startDate(), 100, "Hora de apertura");
		startDate.setCell(new DateCell(DateTimeFormat
				.getFormat("dd/MM/yyyy - hh:mm a")));
		ColumnConfig<EventDto, Date> endDate = new ColumnConfig<EventDto, Date>(
				gridProperties.endDate(), 100, "Hora de cierre");
		endDate.setCell(new DateCell(DateTimeFormat
				.getFormat("dd/MM/yyyy - hh:mm a")));

		List<ColumnConfig<EventDto, ?>> columns = new ArrayList<ColumnConfig<EventDto, ?>>();
		columns.add(nameCol);
		columns.add(startDate);
		columns.add(endDate);
		ColumnModel<EventDto> columnModel = new ColumnModel<EventDto>(columns);
		return columnModel;
	}

	@UiHandler("add")
	public void addLocation(SelectEvent e) {
		if (getUiHandlers() != null) {
			getUiHandlers().addEvent();
		}
	}

	@Override
	public ListStore<EventDto> getListStore() {
		return listStore;
	}

	@Override
	public void update() {
		grid.show();
		widget.forceLayout();
	}
}
