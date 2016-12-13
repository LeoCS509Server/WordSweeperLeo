package admin.admincontroller;

import admin.admincontroller.ControllerChain;
import admin.adminmodel.AdministratorModel;
import admin.adminview.DrawSee;
import xml.Message;

/**
 * When the launch admin interface, a connectRequest will be sent to the
 * server.
 * <p>
 * The {@link #process()} makes a connectRequest in XML format, and send it to
 * the server.
 * 
 * @author Weihao Li,Zetian Wang (Authors contribute equally)
 *
 */
public class ConnectResponseController extends ControllerChain {
	public DrawSee app;
	public AdministratorModel model;

	public ConnectResponseController(DrawSee a, AdministratorModel m) {
		super();
		this.app = a;
		this.model = m;
	}

	@Override
	public boolean process(Message response) {
		String type = response.contents.getFirstChild().getLocalName();
		if (!type.equals("connectResponse")) {
			return next.process(response);
		}
		return true;
	}

}
