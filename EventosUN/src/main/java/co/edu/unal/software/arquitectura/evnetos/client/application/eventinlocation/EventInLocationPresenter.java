package co.edu.unal.software.arquitectura.evnetos.client.application.eventinlocation;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;

public class EventInLocationPresenter extends
		PresenterWidget<EventInLocationPresenter.MyView> {
	public interface MyView extends PopupView {
		void loadData(String eventName, String description,
				String locationName, String openDate, String closeDate);
	}

	@Inject
	EventInLocationPresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);

	}

	public void setData(String eventName, String description,
			String locationName, String openDate, String closeDate) {

		getView().loadData(eventName, description, locationName, openDate,
				closeDate);
	}
}
