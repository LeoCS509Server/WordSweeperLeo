package admin;


import admin.ControllerChain;
import xml.Message;

public abstract class ControllerChain implements IAdminClientController {

	/** Next one in the chain. Once null is reached, done. */
	IAdminClientController next = null;
	
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
	public abstract boolean process(Message response);

}