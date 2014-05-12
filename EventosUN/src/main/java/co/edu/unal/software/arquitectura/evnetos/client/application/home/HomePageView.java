package co.edu.unal.software.arquitectura.evnetos.client.application.home;

import co.edu.unal.software.arquitectura.evnetos.client.resources.Resources;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.CurrentUserDto;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

/**
 * See more on GXT <a
 * href="http://docs.sencha.com/gxt-guides/3/ui/layout/LayoutContainers.html"
 * >Layout Containers</a>
 */
public class HomePageView extends ViewImpl implements HomePagePresenter.MyView {
	public interface Binder extends UiBinder<Widget, HomePageView> {
	}

	@UiField
	public SimplePanel mainPanel;
	public Widget widget;

	@Inject
	public HomePageView(Binder uiBinder, CurrentUserDto currentUserDto,
			Resources recursos) {
		widget = uiBinder.createAndBindUi(this);
		Image im = new Image(recursos.getLogoUnal());
		im.setSize("100%", "100%");

		mainPanel.add(im);

	}

	@Override
	public Widget asWidget() {

		return widget;
	}

}
