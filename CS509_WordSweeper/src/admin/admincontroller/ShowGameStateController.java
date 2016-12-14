package admin.admincontroller;

import admin.adminmodel.AdministratorModel;
import admin.adminview.DrawSee;
import xml.Message;

/**
 * This class is used when admin need to send a 'listGameRequest' to server.
 * Get called when related events happen in GUI.
 * @author Zetian
 *
 */
public class ShowGameStateController {

	DrawSee app;
	AdministratorModel model;

	public ShowGameStateController(DrawSee app, AdministratorModel model) {
		this.app = app;
		this.model = model;
	}

	/**
	 * Make a 'showGameStateRequest' message based on xml protocol and send it to server. 
	 * @param id
	 */
	public void process(String id) {
		String gameId = id;
		//create message 
		String xmlString = Message.requestHeader() +
				"<showGameStateRequest gameId='"+
				gameId +
				"'/> </request>";
		Message m = new Message (xmlString);
		//send out message
		app.getServerAccess().sendRequest(m);
	}
		
}
