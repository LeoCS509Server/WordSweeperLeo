package server.controller;

import server.ClientState;
import xml.Message;

public interface IServerController {
	/**
	 * Return TRUE if accept the request; false otherwise.
	 * 
	 * If unable to process a valid request, then must thrown a RuntimeException 
	 */
	public Message process(ClientState client, Message request);

}
