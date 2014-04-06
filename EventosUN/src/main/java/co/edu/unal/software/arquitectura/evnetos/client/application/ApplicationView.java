package co.edu.unal.software.arquitectura.evnetos.client.application;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.sencha.gxt.widget.core.client.container.NorthSouthContainer;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;

/**
 * See more on GXT <a
 * href="http://docs.sencha.com/gxt-guides/3/ui/layout/LayoutContainers.html"
 * >Layout Containers</a>
 */
public class ApplicationView extends ViewImpl implements
		ApplicationPresenter.MyView {
	public interface Binder extends UiBinder<Widget, ApplicationView> {
	}

	@UiField
	SimpleContainer contenedor;
	@UiField
	NorthSouthContainer headerContainer;

	@Inject
	public ApplicationView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setInSlot(Object slot, IsWidget content) {
		if (slot == ApplicationPresenter.SLOT_LayoutPresenter) {
			if (content != null) {
				contenedor.clear();
				contenedor.add(content);
				contenedor.forceLayout();

			}
		} else if (slot == ApplicationPresenter.HeaderSlot) {
			if (content != null) {
				if (headerContainer.getSouthWidget() != null) {
					headerContainer.setSouthWidget(null);
				}
				headerContainer.setSouthWidget(content);

			}
		}

		else {
			super.setInSlot(slot, content);
		}
	}
}
