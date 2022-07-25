package tests;

import static org.junit.Assert.assertEquals;
import controller.adv.SessionOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import model.Service;
import utils.FileManager;

public class testSessionOperation extends testGlobal {
	private String sessionId = "0002222";
	private SessionOperation sessionOperation = new SessionOperation();
		
	@Before
	public void beforeFindSesson() throws Exception {
		System.out.println("Before find session");
		FileManager.deleteData(true);
		FileManager.initData(true);
		FileManager.readObjects(true);
	}

	/**
	 * Desc: Find a session from a list providing from a service.
	 * Class: controller/SessionOperation
	 * In: String, LinkedList<Session>
	 * Out: int
	 * Test: findSession(sessionId, sessionList) >= 0
	 */
	@Test
	public void testFindSession() {
		Service service = Service.getServiceList().get(0);
		int index = sessionOperation.findSession(sessionId, service.getListSessionsAffiliated());
		int result = index >= 0 ? 1 : 0;
		assertEquals(1, result);
	}
	
	@After
	public void afterFindSession() {
		System.out.println("After find session");
	}	
}
