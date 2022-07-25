package tests;

import static org.junit.Assert.assertEquals;
import model.Member;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import controller.adv.MemberOperation;
import utils.FileManager;
import java.util.LinkedList;

public class testMemberOperation extends testGlobal {
	private String email = "member1@gym.com";
	private MemberOperation memberOperation = new MemberOperation();
	LinkedList<Member> memberList;
	
	@Before
	public void beforeMemberState() throws Exception {
		System.out.println("Before Member State");
		FileManager.deleteData(true);
		FileManager.initData(true);
		FileManager.readObjects(true);
	}

	/**
	 * Desc: See if a member that is valid returns a valid response.
	 * Class: controller/MemberOperation
	 * In: String
	 * Out: int
	 * Test: member (email) -> memberState == 1
	 */
	@Test
	public void testMemberState() {
		assertEquals(1, memberOperation.memberState(email));
	}
	
	@After
	public void afterMemberState() {
		System.out.println("After Member State");
		memberList = Member.getMemberList();
		memberList.clear();
	}
}
