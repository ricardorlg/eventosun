package co.edu.unal.software.arquitectura.evnetos.client.application.menu.serverresponse;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PopupViewImpl;

public class ServerResponseView extends PopupViewImpl implements
		ServerResponsePresenter.MyView {
	public interface Binder extends UiBinder<PopupPanel, ServerResponseView> {
	}
 @UiField
 HTML response;
	@Inject
	ServerResponseView(Binder uiBinder, EventBus eventBus) {
		super(eventBus);

		initWidget(uiBinder.createAndBindUi(this));
	}
	@Override
	public HTML getResponse() {
		return response;
	}
}
