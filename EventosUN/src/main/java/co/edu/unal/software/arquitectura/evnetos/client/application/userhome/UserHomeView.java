package co.edu.unal.software.arquitectura.evnetos.client.application.userhome;

import javax.inject.Inject;

import com.bradrydzewski.gwt.calendar.client.Calendar;
import com.bradrydzewski.gwt.calendar.client.CalendarView;
import com.bradrydzewski.gwt.calendar.client.CalendarViews;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class UserHomeView extends ViewWithUiHandlers<UserHomeUiHandlers>
		implements UserHomePresenter.MyView {
	interface Binder extends UiBinder<Widget, UserHomeView> {
	}

	@UiField
	SimplePanel main;
	private Widget widget;

	@Inject
	UserHomeView(Binder uiBinder) {
		widget = uiBinder.createAndBindUi(this);
		Calendar calendar = new Calendar();
		calendar.setView(CalendarViews.MONTH);
		calendar.setWidth("100%");
		calendar.setHeight("100%");
		main.add(calendar);
	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return widget;
	}

	@Override
	public void setInSlot(Object slot, IsWidget content) {
		if (slot == UserHomePresenter.SLOT_UserHome) {
			main.setWidget(content);
		} else {
			super.setInSlot(slot, content);
		}
	}
}
