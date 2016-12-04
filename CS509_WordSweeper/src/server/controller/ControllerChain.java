package server.controller;

import server.ClientState;
import xml.Message;

public abstract  class ControllerChain implements IServerController {
	/** Next one in the chain. Once null is reached, done. */
	IServerController next = null;
	
	/** Terminal entry in the chain. */
	protected ControllerChain() {
		
	}
	
	/** Chain together. */
	protected ControllerChain(ControllerChain next) {
		this.next = next;
	}
	
	/** 
	 * Carry out the processing. 
	 */
	public abstract Message process(ClientState client, Message request);

}
