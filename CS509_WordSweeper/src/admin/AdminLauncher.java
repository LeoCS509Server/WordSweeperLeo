package admin;

import admin.admincontroller.AdminMessageHandler;
import admin.admincontroller.ConnectResponseController;
import admin.admincontroller.ListGamesResponseController;
import admin.admincontroller.ShowGameStateResponseController;
import admin.adminmodel.AdministratorModel;
import admin.adminview.DrawSee;
import client.ServerAccess;
import xml.Message;

/**
 *  Adminlauncher and send connectrequest and listgamerequest 
 * 
 * @author Weihao Li, Zetian Wang (Authors contribute equally)
 *
 */
public class AdminLauncher {
	// If requested by AdminLauncher (pass in '-server' as argument).
	public static final String serverHost = "72.249.186.243";
	
	/**
	 * Note that to simplify the coding of this command-client, it declares that it can throw an Exception,
	 * which is typically the failed connection to a server.
	 */
	public static void main(String[] args) throws Exception {
		// FIRST thing to do is register the protocol being used. There will be a single class protocol
		// that will be defined and which everyone will use. For now, demonstrate with skeleton protocol.
		if (!Message.configure("wordsweeper.xsd")) {
			System.exit(0);
		}
		
		// select dedicated server with '-server' options
		String host = "localhost";
		if (args.length > 0 && args[0].equals("-server")) {
			host = serverHost;
		}
		
		// Initialize the client application and its corresponding model
		AdministratorModel admodel = new AdministratorModel();
		DrawSee app = new DrawSee(admodel);
				
		// set up the chain of responsibility
		AdminMessageHandler handler = new AdminMessageHandler(app);
		handler.registerHandler(new ListGamesResponseController(app, admodel));
		handler.registerHandler(new ShowGameStateResponseController(app, admodel));
		handler.registerHandler(new ConnectResponseController(app, admodel));
		
		// try to connect to the server. Once connected, messages are going to be processed by 
		// SampleClientMessageHandler. For now we just continue on with the initialization because
		// no message is actually sent by the connect method.
		ServerAccess sa = new ServerAccess(host, 11425);
		if (!sa.connect(handler)) {
			System.out.println("Unable to connect to server (" + host + "). Exiting.");
			System.exit(0);
		}
		System.out.println("Connected to " + host);
		
		
		// Should we on the admin ever need to communicate with the server, we need this ServerAccess
		// object.
		app.setServerAccess(sa);
		
		// send an introductory connect request now that we have created (but not made visible!)
		// the GUI
		String xmlString = Message.requestHeader() + "<connectRequest/></request>";
		Message m = new Message (xmlString);
		sa.sendRequest(m);
		
		String xmlString2 = Message.requestHeader() + "<listGamesRequest/></request>";
		Message m2 = new Message (xmlString2);
		sa.sendRequest(m2);
		
		// at this point, we need to make app visible, otherwise we would terminate application
		app.setVisible(true);
	}

}
