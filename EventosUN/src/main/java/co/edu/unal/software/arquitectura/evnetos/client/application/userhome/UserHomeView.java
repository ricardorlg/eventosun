package co.edu.unal.software.arquitectura.evnetos.client.application.userhome;

import java.util.Date;

import javax.inject.Inject;

import com.bradrydzewski.gwt.calendar.client.Appointment;
import com.bradrydzewski.gwt.calendar.client.Calendar;
import com.bradrydzewski.gwt.calendar.client.CalendarViews;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.DateField;

public class UserHomeView extends ViewWithUiHandlers<UserHomeUiHandlers>
		implements UserHomePresenter.MyView {
	interface Binder extends UiBinder<Widget, UserHomeView> {
	}

	@UiField
	Viewport panelCentral;
	@UiField
	DateField date;
	@UiField
	TextButton mes;
	@UiField
	TextButton dia;
	private Widget widget;
	private Calendar calendar;

	@Inject
	UserHomeView(Binder uiBinder) {
		widget = uiBinder.createAndBindUi(this);
		calendar = new Calendar();
		date.setAutoValidate(true);
		date.getDatePicker().addValueChangeHandler(
				new ValueChangeHandler<Date>() {

					@Override
					public void onValueChange(ValueChangeEvent<Date> event) {
						calendar.setDate(event.getValue());

					}
				});
		calendar.setView(CalendarViews.DAY);
		calendar.setWidth("100%");
		calendar.setHeight("100%");
		calendar.addOpenHandler(new OpenHandler<Appointment>() {

			@Override
			public void onOpen(OpenEvent<Appointment> event) {
				if (getUiHandlers() != null) {
					getUiHandlers().loadEventInfo(event.getTarget());
				}
			}
		});
		panelCentral.add(calendar);
	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return widget;
	}

	@UiHandler("mes")
	public void changeView(SelectEvent ev) {
		calendar.setView(CalendarViews.MONTH);
	}

	@UiHandler("dia")
	public void changeDayView(SelectEvent ev) {
		calendar.setView(CalendarViews.DAY);
	}

	@Override
	public Calendar getCalendar() {
		return calendar;
	}
}
