package tests;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

import controller.adv.ConfirmPresenceOperation;
import model.ConfirmPresence;
import model.Member;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.FileManager;

public class testConfirmPresenceOperation extends testGlobal {
	private String email = "member1@gym.com";
	private String sessionId = "0001111";
	private String proId = "987654321";
	
	private ConfirmPresenceOperation confirmPresenceOperation = new ConfirmPresenceOperation();
	LinkedList<ConfirmPresence> confirmPresenceList;
	
	@Before
	public void beforeConfirmPresence() throws Exception {
		System.out.println("Before Confirm Presence");
		FileManager.deleteData(true);
		FileManager.initData(true);
		FileManager.readObjects(true);
	}

	/**
	 * Desc: Confirms a presence at a pro's session by the client who subscribed beforehand.
	 * Class: controller/ConfirmPresenceOperation
	 * In: String, String, String, String
	 * Out: int
	 * Test: member (email, sessionId, proId, comment) -> confirmPresence() == 0
	 */
	@Test
	public void test_confirmMemberPresence_worksIfSubscribedBeforehand() {
		assertEquals(0, confirmPresenceOperation.confirmPresence(sessionId, email, proId, "test"));
	}
	
	@After
	public void afterConfirmPresence() {
		System.out.println("After Confirm Presence");
		confirmPresenceList = ConfirmPresence.getConfirmPresenceList();
		confirmPresenceList.clear();
	}	
}
