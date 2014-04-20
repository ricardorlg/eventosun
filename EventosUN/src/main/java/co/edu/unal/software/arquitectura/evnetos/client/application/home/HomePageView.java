package co.edu.unal.software.arquitectura.evnetos.client.application.home;

import co.edu.unal.software.arquitectura.evnetos.client.resources.Resources;
import co.edu.unal.software.arquitectura.evnetos.shared.dto.CurrentUserDto;

import com.bramosystems.oss.player.core.client.AbstractMediaPlayer;
import com.bramosystems.oss.player.core.client.PlayerInfo;
import com.bramosystems.oss.player.core.client.PlayerUtil;
import com.bramosystems.oss.player.core.client.PluginNotFoundException;
import com.bramosystems.oss.player.core.client.PluginVersionException;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
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
	public AbstractMediaPlayer player;
	private CurrentUserDto currentUserDto;

	@Inject
	public HomePageView(Binder uiBinder, CurrentUserDto currentUserDto,
			Resources recursos) {
		widget = uiBinder.createAndBindUi(this);
		this.currentUserDto = currentUserDto;
		Image im = new Image(recursos.getLogoUnal());
		im.setSize("100%", "100%");

		mainPanel.add(im);

	}

	@Override
	public Widget asWidget() {

		return widget;
	}

	@Override
	public void renderVideo() {
		try {
			// retrieve the basic information about the YouTubeIPlayer as
			// registered with the API
			// using the provider name and the player name
			PlayerInfo pi = PlayerUtil.getPlayerInfo("bst.youtube", "YouTube");

			// create the player, using the PlayerInfo object and specifing URL
			// of media
			player = PlayerUtil.getPlayer(pi, "MW43lPmeSk8", true, "100%",
					"100%");
			mainPanel.setWidget(player); // add player to panel.

		} catch (PluginVersionException e) {
			// required plugin version is not available, alert user
			mainPanel.setWidget(PlayerUtil.getMissingPluginNotice(
					e.getPlugin(), e.getRequiredVersion()));
		} catch (PluginNotFoundException e) {
			// catch PluginNotFoundException and display a friendly notice.
			mainPanel
					.setWidget(PlayerUtil.getMissingPluginNotice(e.getPlugin()));
		}
	}

}
