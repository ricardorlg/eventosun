package co.edu.unal.software.arquitectura.evnetos.client.application.menu;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

public class MenuView extends ViewWithUiHandlers<MenuUiHandlers> implements
		MenuPresenter.MyView {
	public interface Binder extends UiBinder<Widget, MenuView> {
	}

	@UiField
	TextButton loginButton;
	@UiField
	TextButton registerButton;

	@Inject
	MenuView(Binder binder) {
		initWidget(binder.createAndBindUi(this));
	}

	@UiHandler("registerButton")
	public void register(SelectEvent e) {
		if (getUiHandlers() != null) {
			getUiHandlers().openRegister();
		}
	}
}
