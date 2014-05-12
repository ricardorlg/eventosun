package co.edu.unal.software.arquitectura.evnetos.client.application.menu;

import co.edu.unal.software.arquitectura.evnetos.client.resources.Resources;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.CurrentUserDto;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class MenuView extends ViewWithUiHandlers<MenuUiHandlers> implements
		MenuPresenter.MyView {

	private ToolBar menu;
	private Resources recursos;
	private CurrentUserDto currentLoginInfo;
	private TextButton btnLogout;
	private TextButton btnUser;
	private TextButton btnAdminLocations;
	private TextButton btnEventos;
	private TextButton btnLogin;
	private TextButton btnRegistrar;
	private TextButton btnAdminEvents;

	@Inject
	public MenuView(final Resources recursos,
			final CurrentUserDto currentLoginInfo) {
		this.recursos = recursos;
		this.currentLoginInfo = currentLoginInfo;
		menu = new ToolBar();

	}

	@Override
	public Widget asWidget() {
		return menu;
	}

	@Override
	public void renderUserMenus() {
		menu.clear();
		menu.add(new FillToolItem());
		btnRegistrar = new TextButton("Registrarse", recursos.iconUserSuit());
		btnLogin = new TextButton("Ingresar", recursos.login());
		menu.add(btnRegistrar);
		SeparatorToolItem separatorToolItem2 = new SeparatorToolItem();
		menu.add(separatorToolItem2);
		menu.add(btnLogin);
		bindCustomUserUiHandlers();
		menu.forceLayout();
	}

	@Override
	public void renderGeneralMenu() {
		menu.clear();
		btnLogout = new TextButton("Logout", recursos.iconKeyGo());

		btnUser = new TextButton(currentLoginInfo.getUserName() == null ? ""
				: currentLoginInfo.getUserName(), recursos.iconUserSuit());

		btnEventos = new TextButton("Eventos", recursos.products());
		menu.add(btnEventos);

		btnEventos.setEnabled(true);
		btnEventos.setVisible(true);
		menu.add(new FillToolItem());
		menu.add(btnUser);
		SeparatorToolItem separatorToolItem2 = new SeparatorToolItem();
		menu.add(separatorToolItem2);
		menu.add(btnLogout);
		btnLogout.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				if (getUiHandlers() != null) {
					getUiHandlers().logOut();
				}
			}
		});

		btnEventos.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				if (getUiHandlers() != null) {
					getUiHandlers().eventsClicked();
				}
			}
		});
		menu.forceLayout();

	}

	private void bindCustomUserUiHandlers() {
		btnRegistrar.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				if (getUiHandlers() != null) {
					getUiHandlers().openRegister();
				}

			}
		});
		btnLogin.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {

				if (getUiHandlers() != null) {
					getUiHandlers().openLogin();
				}

			}
		});

	}

	@Override
	public void enabledActionsAdminLocations() {
		menu.clear();
		btnLogout = new TextButton("Logout", recursos.iconKeyGo());

		btnUser = new TextButton(currentLoginInfo.getUserName() == null ? ""
				: currentLoginInfo.getUserName(), recursos.iconUserSuit());

		btnAdminLocations = new TextButton("Espacios", recursos.IconEdificio());
		SeparatorToolItem separatorToolItem = new SeparatorToolItem();

		btnEventos = new TextButton("Eventos", recursos.products());
		menu.add(btnEventos);
		menu.add(separatorToolItem);
		menu.add(btnAdminLocations);

		btnAdminLocations.setEnabled(true);
		btnEventos.setEnabled(true);
		btnAdminLocations.setVisible(true);
		btnEventos.setVisible(true);
		menu.add(new FillToolItem());
		menu.add(btnUser);
		SeparatorToolItem separatorToolItem2 = new SeparatorToolItem();
		menu.add(separatorToolItem2);
		menu.add(btnLogout);
		btnLogout.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				if (getUiHandlers() != null) {
					getUiHandlers().logOut();
				}
			}
		});
		btnAdminLocations.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				if (getUiHandlers() != null) {
					getUiHandlers().adminLocationsClicked();
				}
			}
		});

		btnEventos.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				if (getUiHandlers() != null) {
					getUiHandlers().eventsClicked();
				}
			}
		});
		menu.forceLayout();
	}

	@Override
	public void enabledActionsAdminEvents() {
		menu.clear();
		btnLogout = new TextButton("Logout", recursos.iconKeyGo());

		btnUser = new TextButton(currentLoginInfo.getUserName() == null ? ""
				: currentLoginInfo.getUserName(), recursos.iconUserSuit());

		btnAdminEvents = new TextButton("Administrar Eventos",
				recursos.iconUserSuit());
		SeparatorToolItem separatorToolItem = new SeparatorToolItem();

		btnEventos = new TextButton("Eventos", recursos.products());
		menu.add(btnEventos);
		menu.add(separatorToolItem);
		menu.add(btnAdminEvents);

		btnAdminEvents.setEnabled(true);
		btnEventos.setEnabled(true);
		btnAdminEvents.setVisible(true);
		btnEventos.setVisible(true);
		menu.add(new FillToolItem());
		menu.add(btnUser);
		SeparatorToolItem separatorToolItem2 = new SeparatorToolItem();
		menu.add(separatorToolItem2);
		menu.add(btnLogout);
		btnLogout.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				if (getUiHandlers() != null) {
					getUiHandlers().logOut();
				}
			}
		});
		btnAdminEvents.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				if (getUiHandlers() != null) {
					getUiHandlers().adminEventsClicked();
				}
			}
		});

		btnEventos.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				if (getUiHandlers() != null) {
					getUiHandlers().eventsClicked();
				}
			}
		});
		menu.forceLayout();
	}
}
