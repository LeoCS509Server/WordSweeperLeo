package admin;

import java.util.ArrayList;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import server.model.Model;
import server.view.LeoAdminGUI;
import xml.Message;


public class ShowGameStateResponseController extends ControllerChain {

	public LeoAdminGUI app;
	public Model model;
	String content;
	//ArrayList<String> playerContent;
	/**
	 *deal with the showGameStateResponse from server and upgrade the GUI 
	 */
	public ShowGameStateResponseController(LeoAdminGUI a, Model m) {
		super();
		this.app = a;
		this.model = m;
	}
	
	public boolean process(Message response) {
		String type = response.contents.getFirstChild().getLocalName();
		if (!type.equals ("boardResponse")) {
			return next.process(response);
		}
		
		//String content = "";
		ArrayList<String> playerContent = new ArrayList<String>();
	
		Node showGameStateResponse = response.contents.getFirstChild();
		NamedNodeMap showGame = showGameStateResponse.getAttributes();
		content = showGame.getNamedItem("contents").getNodeValue();
		Node player = showGameStateResponse.getFirstChild();
		while ( player != null){
			NamedNodeMap playerMap = player.getAttributes();
			String position = playerMap.getNamedItem("position").getNodeValue();
			playerContent.add(position);
			player = player.getNextSibling();
		}
		
	/**
	 * call some GUI update functions here	
	 */
	
		return true;
	}

}