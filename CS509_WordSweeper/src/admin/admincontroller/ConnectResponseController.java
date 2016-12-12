package admin.admincontroller;

import admin.admincontroller.ControllerChain;
import admin.adminmodel.AdminModel;
import admin.adminview.DrawSee;
import xml.Message;
public class ConnectResponseController extends ControllerChain {
	public DrawSee app;
	public AdminModel model;

	public ConnectResponseController(DrawSee a, AdminModel m) {
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

		// app.getResponseArea().append(response.toString() + "\n");
		return true;
	}

}
