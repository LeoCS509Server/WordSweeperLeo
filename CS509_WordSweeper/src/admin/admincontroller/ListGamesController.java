package admin.admincontroller;

import xml.Message;
import admin.adminmodel.AdministratorModel;


import admin.adminview.DrawSee;

/**
 * This class is used when admin need to send a 'listGameRequest' to server.
 * Get called when related events happen in GUI or run adminlauncher
 *  @author Zetian
 *
 */
public class ListGamesController {

	DrawSee app;
	AdministratorModel model;

	public ListGamesController(DrawSee drawSee, AdministratorModel model) {
		this.app = drawSee;
		this.model = model;
	}

	/**
	 * Make a 'listGamesRequest' message based on xml protocol and send it to server. 
	 */
	public void process() {
		// send the request to list games.
		String xmlString = Message.requestHeader() + "<listGamesRequest/></request>";
		Message m = new Message (xmlString);
		app.getServerAccess().sendRequest(m);
	}
}

