package admin.admincontroller;




import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import admin.adminmodel1.AdminModel;
import admin.adminview.DrawSee;
import xml.Message;


/**
 * deal with listGame response
 * Tell admin client the active games, show game list in GUI
 */
public class ListGamesResponseController extends ControllerChain {

	//public DrawSee app;
	public AdminModel model;
	
	public ListGamesResponseController(AdminModel m) {
		super();
		//this.app = a;
		this.model = m;
	}
	
	public boolean process(Message response) {
		String type = response.contents.getFirstChild().getLocalName();
		if (!type.equals ("listGamesResponse")) {
			return next.process(response);
		}

		// this refers to the outer node of the Message DOM (in this case, updateResponse).
		Node listGamesResponse = response.contents.getFirstChild();
		Node gameBrief = listGamesResponse.getFirstChild();
		String Info = "";
		while (gameBrief != null){
			NamedNodeMap map = gameBrief.getAttributes();
			String ID = map.getNamedItem("gameId").getNodeValue();			
			int Num = Integer.parseInt(map.getNamedItem("players").getNodeValue());
			Info = Info+ ID + ","+Num+",";
			gameBrief = gameBrief.getNextSibling();
		}
		model.getIdlist(Info);
		
	/**
	 * call some GUI update functions here	
	 */
	
		return true;
	}

}
