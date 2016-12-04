package admin.admincontroller;

import admin.adminview.AdminApplication;
import client.IMessageHandler;
import xml.Message;

public class AdminMessageHandler implements IMessageHandler{

	AdminApplication app;
	
	// by default is the empty handler...
	ControllerChain chain = new EmptyHandler();
	
	/**
	 * Register new controller chain as occuring before existing chain.
	 */
	public void registerHandler(ControllerChain handler) {
		handler.next = chain;
		chain = handler;
	}
	
	public AdminMessageHandler(AdminApplication app) {
		this.app = app;
	}
	
	
	@Override
	public void process(Message response) {
		chain.process(response);
	}

}
