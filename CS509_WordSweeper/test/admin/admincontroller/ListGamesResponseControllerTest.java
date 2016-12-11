package admin.admincontroller;

import admin.adminmodel1.AdminModel;
import junit.framework.TestCase;
import xml.Message;

public class ListGamesResponseControllerTest extends TestCase {

	AdminModel model;
	protected void setUp() throws Exception {
		super.setUp();
		if (!Message.configure("wordsweeper.xsd")) {
			fail ("unable to configure protocol");
		}
		model = new AdminModel();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testProcess() {
		String xmlString =Message.responseHeader("1101")
				+"<listGamesResponse>"
				+ "<gameBrief gameId='181949' players='3'/>"
				+ "<gameBrief gameId='181000' players='2'/>"
				+ "</listGamesResponse></response>";
		Message response = new Message(xmlString);
		new ListGamesResponseController(model).process(response);
		String idlist = model.obtainIdList();
		assertEquals(idlist,"181949,3,181000,2,");
		
	}

}
