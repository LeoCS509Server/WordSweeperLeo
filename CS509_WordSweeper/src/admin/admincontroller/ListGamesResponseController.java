package admin.admincontroller;



import java.util.ArrayList;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import admin.adminmodel.AdminModel;
import admin.adminview.AdminApplication;
import xml.Message;


/**
 * deal with listGame response
 * Tell admin client the active games, show game list in GUI
 */
public class ListGamesResponseController extends ControllerChain {

	public AdminApplication app;
	public AdminModel model;
	
	public ListGamesResponseController(AdminApplication a, AdminModel m) {
		super();
		this.app = a;
		this.model = m;
	}
	
	public boolean process(Message response) {
		String type = response.contents.getFirstChild().getLocalName();
		if (!type.equals ("listGamesResponse")) {
			return next.process(response);
		}
		
		ArrayList<String> gamesList = new ArrayList<String>() ;
		ArrayList<String> playersList = new ArrayList<String>();
		
		// this refers to the outer node of the Message DOM (in this case, updateResponse).
		Node listGamesResponse = response.contents.getFirstChild();
		Node gameBrief = listGamesResponse.getFirstChild();
		while (gameBrief != null){
			NamedNodeMap map = gameBrief.getAttributes();
			String ID = map.getNamedItem("gameId").getNodeValue();
			gamesList.add(ID);
			String Num = map.getNamedItem("players").getNodeValue();
			playersList.add(Num);
			gameBrief = gameBrief.getNextSibling();
		}
		
	/**
	 * call some GUI update functions here	
	 */
	
		return true;
	}

}