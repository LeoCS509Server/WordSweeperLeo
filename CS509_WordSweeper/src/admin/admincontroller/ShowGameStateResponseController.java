package admin.admincontroller;


import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import admin.adminmodel.AdminModel;
import admin.adminmodel.Game;
import admin.adminview.DrawSee;
import xml.Message;


public class ShowGameStateResponseController extends ControllerChain {

	public DrawSee app;
	public AdminModel model;
	

	/**
	 *deal with the showGameStateResponse from server and upgrade the GUI 
	 */
	public ShowGameStateResponseController(DrawSee app,AdminModel m) {
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
		
		for(String i:g.getPlayerid()){
			app.getPlayerarea().append(i+"\n");
		}
//		for(Integer j:g.getScore()){
//			app.getPlayerarea().append(j+"\n");
//		}
//		app.getPlayerarea().append(g.getPlayerid().get(0)+"\n");
//		app.getPlayerarea().append(g.getPlayerid().get(1)+"\n");
		app.paint(app.getGraphics());
		app.paintComponents(app.getGraphics(),g); //draw board
		

	/**
	 * call some GUI update functions here	
	 */
	
		return true;
	}

}
