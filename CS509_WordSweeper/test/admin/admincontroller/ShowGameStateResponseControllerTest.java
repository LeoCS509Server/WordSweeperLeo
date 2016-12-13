package admin.admincontroller;

import admin.MockServerAccess;
import admin.adminmodel.AdministratorModel;
import admin.adminview.DrawSee;
import junit.framework.TestCase;
import xml.Message;

public class ShowGameStateResponseControllerTest extends TestCase {
	// Mock server object that extends (and overrides) ServerAccess for its purposes
	MockServerAccess mockServer;
	// admin to connect
	//DrawSee admin;
	
	// model being maintained by client.
	AdministratorModel model;
	DrawSee app;

	protected void setUp() throws Exception {
		super.setUp();
		// FIRST thing to do is register the protocol being used.
		if (!Message.configure("wordsweeper.xsd")) {
			fail ("unable to configure protocol");
		}
		
		// prepare client and connect to server.
		model = new AdministratorModel();
		app = new DrawSee(model);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testProcess() {
		String xmlString = Message.responseHeader("1101") +"<boardResponse gameId='10534' size='7' managingUser='manager' "
				+ "contents='O,S,O,Y,O,N,L,E,E,E,R,I,A,O,E,O,S,U,T,I,S,A,C,O,G,G,O,P,D,C,C,Y,O,O,L,E,N,U,E,R,N,A,Y,E,C,F,U,H,A,' "
				+ "bonus='7,4'>"
				+ "<player name='manager' position='1,1' board='O,S,O,Y,E,E,E,R,E,O,S,U,A,C,O,G,' score='0'/>"
				+ "<player name='ali' position='3,1' board='O,Y,O,N,E,R,I,A,S,U,T,I,O,G,G,O,' score='0'/>"
				+ "</boardResponse></response>";
		Message response = new Message(xmlString);
		new ShowGameStateResponseController(app,model).process(response);
		admin.adminmodel.Game gg = model.getGame("10534");
		System.out.println(gg.getGlobalboard());
		String b = "";
		for(String[] s:gg.getGlobalboard()){
			for(String ss: s){
				b = b+ss;
			}
		}
		assertEquals(b,"OSOYONLEEERIAOEOSUTISACOGGOPDCCYOOLENUERNAYECFUHA");
		int l1 = gg.getPlayerlocation().get(0);
		assertEquals(11,l1);
		int l2 = gg.getPlayerlocation().get(1);
		assertEquals(31,l2);

		
	}

}
