package server.controller;
import org.w3c.dom.Node;

import server.ClientState;
import server.IShutdownHandler;
import server.model.Model;
import server.model.Game;
import xml.*;

/**
 * Sample implementation of a protocol handler to respond to messages received from clients.
 * You should follow this template when designing YOUR protocol handler.
 * <p>
 * To avoid issues with multiple clients submitting requests concurrently,
 * notice that the {@link #process(ClientState, Message)} method is synchronized.
 * This will ensure that no more than one server thread executes this method
 * at a time.
 * <p>
 * Also extended to support detection of client disconnects so these can release the lock
 * if indeed the client was the one locking the model.
 */
public class ProtocolHandler implements IShutdownHandler {
	
	Model model;
	Game game;
	
	public ProtocolHandler (Model model) {
		this.model = model;
	}
	
	@Override
	public synchronized Message process (ClientState st, Message request) {
		Node child = request.contents.getFirstChild();
		String type = child.getLocalName();
		
		
		System.out.println (request);
		if (type.equals ("createGameRequest")) {
			return new CreateGameRequestController(model).process(st, request); 
		} else if (type.equals ("joinGameRequest")) {
			return new JoinGameRequestController(model).process(st, request);
		} else if (type.equals("exitGameRequest")) {
			return new ExitGameRequestController(model).process(st, request);
		} else if (type.equals("lockGameRequest")) {
			return new LockGameRequestController(model).process(st, request);
		} else if (type.equals("findWordRequest")) {
			return new FindWordRequestController(model).process(st, request);
		} else if (type.equals("resetGameRequest")) {
			return new ResetGameRequestController(model).process(st, request);
		} else if (type.equals("listGameRequest")) {
			return new ListGamesRequestController(model).process(st, request);
		} else if (type.equals("showGameStateRequest")) {
			return new ShowGameStateRequestController(model).process(st, request);
		} else if (type.equals("repositionBoardRequest")) {
			return new RespositionBoardRequestController(model).process(st, request);
		} 
		
		// unknown? no idea what to do
		System.err.println("Unable to handle message:" + request);
		return null;
	}

	@Override
	public void logout(ClientState st) {
		new ClientDisconnectController().process(st);		
	} 
}
