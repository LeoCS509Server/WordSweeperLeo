package admin.admincontroller;

import admin.adminmodel.AdminModel;
import admin.adminview.AdminApplication;
import xml.Message;
/**
 * admin client send a request of showgamestate to server
 */

public class ShowGameStateController {

	AdminApplication app;
	AdminModel model;

	public ShowGameStateController(AdminApplication app, AdminModel model) {
		this.app = app;
		this.model = model;
	}

	/** Make the list games request on the server and wait for response. */
	public void process(String id) {
		//get a gameid by selecting from GUI
		String gameId = id;
		// send the request to list games.
		String xmlString = Message.requestHeader() +
				"<showGameStateRequest gameId='"+
				gameId +
				"'/> </request>";
		Message m = new Message (xmlString);

		app.getServerAccess().sendRequest(m);
	}
		
}
