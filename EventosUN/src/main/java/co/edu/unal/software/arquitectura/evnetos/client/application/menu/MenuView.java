package co.edu.unal.software.arquitectura.evnetos.client.application.menu;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class MenuView extends ViewWithUiHandlers<MenuUiHandlers> implements
		MenuPresenter.MyView {
	public interface Binder extends UiBinder<HTMLPanel, MenuView> {
	}

	@UiField
	HTMLPanel panel;

	@Inject
	MenuView(Binder binder) {
		initWidget(binder.createAndBindUi(this));
	}
}
