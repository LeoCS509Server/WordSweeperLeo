package admin.admincontroller;

import xml.Message;
import admin.adminmodel.AdminModel;


import admin.adminview.DrawSee;


/**
 * send a listGame Request to server 
 */
public class ListGamesController {

	DrawSee app;
	AdminModel model;

	public ListGamesController(DrawSee drawSee, AdminModel model) {
		this.app = drawSee;
		this.model = model;
	}

	/** Make the list games request on the server and wait for response. */
	public void process() {
		// send the request to list games.
		String xmlString = Message.requestHeader() + "<listGamesRequest/></request>";
		Message m = new Message (xmlString);

		app.getServerAccess().sendRequest(m);
		/**
		// Request the lock (this might not succeed).
		app.getRequestArea().append(m.toString());
		app.getRequestArea().append("\n");
		app.getServerAccess().sendRequest(m);
		*/
	}
}

