package admin;

import junit.framework.TestCase;
import server.model.Model;
import server.view.LeoAdminGUI;
import xml.Message;

public class ShowGameStateResponseControllerTest extends TestCase {

	MockServerAccess mockServer;
	LeoAdminGUI gui;
	Model model;
	String responseHeader = null;
	
	protected void setUp() throws Exception {
		super.setUp();
		if (!Message.configure("wordsweeper.xsd")) {
			fail ("unable to configure protocol");
		}
		model = new Model();
		gui = new LeoAdminGUI (model);
		gui.setVisible(true);
		mockServer = new MockServerAccess("localhost");
		responseHeader = Message.responseHeader("someMessageID");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testProcess() {
		Message m = new Message (
				responseHeader +
				  "<boardResponse gameId='dsfhu' size='7' managingUser='leo1' contents='dancislcohgsiqkfdancislcohgsiqkfdancislcohgsiqkfd' bonus='7,1'>" +
					  "<player name='leo1' position='1,1' board='dancislcohgsiqkf' score='1000'/>"+
					  "<player name='leo2' position='3,3' board='dancislcohgsiqkf' score='1000'/>"+
					  "<player name='leo3' position='5,5' board='dancislcohgsiqkf' score='1000'/>"+
				  "</boardResponse>" +
				"</response>");
		System.out.println(m.toString());
		//assertEquals ("boardResponse", m.contents.getFirstChild().getLocalName());
		//new ShowGameStateResponseController(gui, model).process(m);
		ShowGameStateResponseController sample = new ShowGameStateResponseController(gui, model);
		sample.process(m);
		assertEquals ("dancislcohgsiqkfdancislcohgsiqkfdancislcohgsiqkfd",sample.content);
		/**for (String i : sample.playerContent){
			System.out.println(i);
		}*/
		
		
	}

}
