package co.edu.unal.software.arquitectura.evnetos.server.services.handlers;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import co.edu.unal.software.arquitectura.evnetos.shared.actions.LoadServerInfoAction;
import co.edu.unal.software.arquitectura.evnetos.shared.actions.LoadServerInfoResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class LoadServerInfoActionHandler implements
		ActionHandler<LoadServerInfoAction, LoadServerInfoResult> {
	private Provider<HttpServletRequest> requestProvider;
	private ServletContext servletContext;

	@Inject
	LoadServerInfoActionHandler(ServletContext servletContext,
			Provider<HttpServletRequest> requestProvider) {
		this.servletContext = servletContext;
		this.requestProvider = requestProvider;
	}

	@Override
	public LoadServerInfoResult execute(LoadServerInfoAction action,
			ExecutionContext context) throws ActionException {
		InetAddress s = null;
		String serverInfo = servletContext.getServerInfo();
		try {
			s = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String userAgent = requestProvider.get().getHeader("User-Agent");
		userAgent = escapeHtml(userAgent);
		return new LoadServerInfoResult("Hello, " + "!<br><br>I am running "
				+ serverInfo + "<br><br>Server name: " + s.getHostName()
				+ "<br><br>Server Ip: " + s.getHostAddress()
				+ ".<br><br>It looks like you are using:<br>" + userAgent);

	}

	@Override
	public Class<LoadServerInfoAction> getActionType() {
		// TODO Auto-generated method stub
		return LoadServerInfoAction.class;
	}

	@Override
	public void undo(LoadServerInfoAction action, LoadServerInfoResult result,
			ExecutionContext context) throws ActionException {
		// TODO Auto-generated method stub

	}

	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
}
