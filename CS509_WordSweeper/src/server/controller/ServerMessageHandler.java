package server.controller;
import server.ClientState;
import server.controller.ControllerChain;
import server.controller.EmptyHandler;
import client.IMessageHandler;
import xml.Message;

public class ServerMessageHandler implements IMessageHandler {

	
	// by default is the empty handler...
	ControllerChain chain = new EmptyHandler();
	
	/**
	 * Register new controller chain as occuring before existing chain.
	 */
	public void registerHandler(ControllerChain handler) {
		handler.next = chain;
		chain = handler;
	}
	
	
	

	public void process(ClientState client,Message request) {
		chain.process(client, request);
	}




}
