package admin.admincontroller;


import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import admin.adminmodel.AdministratorModel;
import admin.adminview.DrawSee;
import xml.Message;


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
			//int Num = Integer.parseInt(map.getNamedItem("players").getNodeValue());
			//Info = Info+ ID ;
			
			model.setIdlist(ID);
			gameBrief = gameBrief.getNextSibling();
		}
		
		app.gameComponents(app.getchoice());
	//System.out.println(model.obtainIdList().get(0));
//		app.getGUI();
		
	/**
	 * call some GUI update functions here	
	 */
	
		return true;
	}

	

}
