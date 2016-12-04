package server.controller;

import server.ClientState;
import xml.Message;

public final class EmptyHandler extends ControllerChain {

	@Override
	public Message process(ClientState client, Message request) {
		Message m = new Message("");
		System.err.println("Not Handled:" + request);
		return m;
	}

}
