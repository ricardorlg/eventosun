package co.edu.unal.software.arquitectura.evnetos.client.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface Resources extends ClientBundle {
	@Source("images/wrench.png")
	ImageResource iconWrench();

	@Source("images/login.png")
	ImageResource login();

	@Source("images/accept.png")
	ImageResource accept();

	@Source("images/delete.png")
	ImageResource delete();

	@Source("images/user_suit.png")
	ImageResource userIcon();
}
