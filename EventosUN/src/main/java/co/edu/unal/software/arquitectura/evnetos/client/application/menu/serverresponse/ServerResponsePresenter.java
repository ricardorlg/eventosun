package co.edu.unal.software.arquitectura.evnetos.client.application.menu.serverresponse;

import com.google.gwt.user.client.ui.HTML;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;

public class ServerResponsePresenter extends
		PresenterWidget<ServerResponsePresenter.MyView> {
	public interface MyView extends PopupView {

		HTML getResponse();
	}

	@Inject
	ServerResponsePresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);

	}

	public void setResponse(String response) {
		getView().getResponse().setHTML(response);
	}

}
