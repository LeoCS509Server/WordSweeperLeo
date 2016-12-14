package admin.admincontroller;


import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import admin.adminmodel.AdministratorModel;
import admin.adminmodel.Game;
import admin.adminview.DrawSee;
import xml.Message;

/**
 *An showgamestate response information should be received once admin is trying to refresh the admin interface.
 * This handles the showgame response information
 * 
 * The {@link #process(Message)}} updates the game information in entity classes, and returns the boundary to the admin GUI.
 * 
 * 
 * @author Weihao Li,Zetian Wang (Authors contribute equally)
 */
public class ShowGameStateResponseController extends ControllerChain {

	public DrawSee app;
	public AdministratorModel model;
	
	/**
	 * Constructor function
	 * @param app
	 * @param m
	 */
	public ShowGameStateResponseController(DrawSee app, AdministratorModel m) {
		super();
		this.app = app;
		this.model = m;
	}
	
	public boolean process(Message response) {
		String type = response.contents.getFirstChild().getLocalName();
		if (!type.equals ("boardResponse")) {
			return next.process(response);
		}	
		Node showGameStateResponse = response.contents.getFirstChild();
		NamedNodeMap showGame = showGameStateResponse.getAttributes();
		String id = showGame.getNamedItem("gameId").getNodeValue();
		int size = Integer.parseInt(showGame.getNamedItem("size").getNodeValue());
		if(model.isExist(id)){
			model.removeGame(id);
		}
		Game g = new Game(id,size);
		model.addGame(g);	
		String content = showGame.getNamedItem("contents").getNodeValue();
		g.setBoard(content, size);
		Node player = showGameStateResponse.getFirstChild();
		while ( player != null){
			NamedNodeMap playerMap = player.getAttributes();
			String[] position = playerMap.getNamedItem("position").getNodeValue().split(",");
			int positionIntStyle = Integer.parseInt(position[0])*10+Integer.parseInt(position[1]);
			g.setPlayerlocation(positionIntStyle);
			String name = playerMap.getNamedItem("name").getNodeValue()+"            "+playerMap.getNamedItem("score").getNodeValue();;
			g.setPlayerid(name);
			int score = Integer.parseInt(playerMap.getNamedItem("score").getNodeValue());
			g.setScore(score);
			player = player.getNextSibling();
		}
		
		//update GUI
		for(String i:g.getPlayerid()){
			app.getPlayerarea().append(i+"\n");
		}
		app.paint(app.getGraphics());
		app.paintComponents(app.getGraphics(),g); //draw board	
		return true;
	}

}
