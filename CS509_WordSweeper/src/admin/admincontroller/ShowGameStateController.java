package admin.admincontroller;

import server.model.Model;
import server.view.LeoAdminGUI;
import xml.Message;
/**
 * admin client send a request of showgamestate to server
 */

public class ShowGameStateController {

	LeoAdminGUI app;
	Model model;

	public ShowGameStateController(LeoAdminGUI app, Model model) {
		this.app = app;
		this.model = model;
	}

	/** Make the list games request on the server and wait for response. */
	public void process() {
		//get a gameid by selecting from GUI
		String gameId = "anyGame";
		// send the request to list games.
		String xmlString = Message.requestHeader() +
				"<showGameStateRequest/>"+
				"name='"+
				gameId +
				"'</request>";
		Message m = new Message (xmlString);

		app.getServerAccess().sendRequest(m);
	}
		
}
