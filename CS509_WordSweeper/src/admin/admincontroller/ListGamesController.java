package admin.admincontroller;

import xml.Message;
import server.model.Model;
import server.view.LeoAdminGUI;

/**
 * send a listGame Request to server 
 */
public class ListGamesController {

	LeoAdminGUI app;
	Model model;

	public ListGamesController(LeoAdminGUI app, Model model) {
		this.app = app;
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

