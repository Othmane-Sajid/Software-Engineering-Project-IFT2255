package tests;

import static org.junit.Assert.*;
import controller.adv.SubSessionOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.FileManager;

public class testSubSessionOperation extends testGlobal {
	private String email = "member1@gym.com";
	SubSessionOperation subSessionOperation = new SubSessionOperation();

	@Before
	public void beforeSubSessionForTest() throws Exception {
		System.out.println("Before subscription session");
		FileManager.initData(true);
		FileManager.readObjects(true);
	}

	/**
	 * Desc: Check the total cost amount
	 * Class: controller/SubSessionOperation
	 * In: String, String, String, String, String 
	 * Out: double
	 * Test: 2 (nb. weeks) * 10 (cost)
	 * 		 (nb. weeks) -> count from dateStart to dateFinal
	 */
	@Test
	public void test_getSubSessionsCostFromMember_fromSeeding() {
		double cost = subSessionOperation.subSession(
				email,
				"000",
				"0001111",
				"unit testing",
				"12-12-2021"
		).getTotalCostForSession();

		assertEquals(20, cost, DELTA_LOSS);
	}
	
	@After
	public void afterSubSessionForTest() {
		System.out.println("After subscription session"); 
	}
}
