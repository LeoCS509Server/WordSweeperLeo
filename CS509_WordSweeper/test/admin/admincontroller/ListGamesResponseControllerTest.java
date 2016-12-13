package admin.admincontroller;

import java.util.ArrayList;

import admin.adminmodel.AdministratorModel;
import admin.adminview.DrawSee;
import junit.framework.TestCase;
import xml.Message;

public class ListGamesResponseControllerTest extends TestCase {

	AdministratorModel model;
	DrawSee app;
	protected void setUp() throws Exception {
		super.setUp();
		if (!Message.configure("wordsweeper.xsd")) {
			fail ("unable to configure protocol");
		}
		model = new AdministratorModel();
		app = new DrawSee(model);
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
		new ListGamesResponseController(app,model).process(response);
		ArrayList<String> idlist = model.obtainIdList();
		assertEquals(idlist.get(0),"181949");
		
	}

}
