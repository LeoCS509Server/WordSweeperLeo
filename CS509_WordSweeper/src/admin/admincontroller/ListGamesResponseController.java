package admin.admincontroller;


import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import admin.adminmodel.AdministratorModel;
import admin.adminview.DrawSee;
import xml.Message;

/**
 *An listgame response information should be received once admin is trying to select a gameid to know its information.
 * This handles the list game response information
 * 
 * The {@link #process(Message)}} updates the gameid information in entity classes, and returns the boundary  to the admin GUI.
 * 
 * 
 * @author Weihao Li,Zetian Wang (Authors contribute equally)
 */

/**
 * deal with listGame response
 * Tell admin client the active games, show game list in GUI
 */
public class ListGamesResponseController extends ControllerChain {

	public DrawSee app;
	public AdministratorModel model;
	
	public ListGamesResponseController(DrawSee app,AdministratorModel m) {
		super();
		this.app = app;
		this.model = m;
	}
	
	public boolean process(Message response) {
		
		String type = response.contents.getFirstChild().getLocalName();
		if (!type.equals ("listGamesResponse")) {
			return next.process(response);
		}
		System.out.println(response);
		// this refers to the outer node of the Message DOM (in this case, updateResponse).
		Node listGamesResponse = response.contents.getFirstChild();
		Node gameBrief = listGamesResponse.getFirstChild();
	
		
		while (gameBrief != null){
			NamedNodeMap map = gameBrief.getAttributes();
			String ID = map.getNamedItem("gameId").getNodeValue();			
			model.setIdlist(ID);//update gamelist
			gameBrief = gameBrief.getNextSibling();
		}
		/**
		 * call some GUI update functions here	
		 */
		app.gameComponents(app.getchoice());	
		return true;
	}

	

}
