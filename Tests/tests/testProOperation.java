package tests;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.FileManager;
import controller.crud.CRUDOperation;
import model.Member;
import model.Professional;

public class testProOperation extends testGlobal {
	private String email = "pro1@gym.com";
	LinkedList<Professional> proList;
	
	@Before
	public void beforeFindPro() throws Exception {
		System.out.println("Before find pro");
		FileManager.deleteData(true);
		FileManager.initData(true);
		FileManager.readObjects(true);
	}

	/**
	 * Desc: Find a professional's position in the LinkedList with his email.
	 * Class: controller/ProOperation
	 * In: String
	 * Out: int
	 * Test: pro (email) -> findPro() == 0
	 */
	@Test
	public void testFindPro() {
		Professional proToFind = new Professional(email); 
		assertEquals(0, CRUDOperation.find(proToFind));
	}
	
	@After
	public void afterFindPro() {
		System.out.println("After find pro");
		proList = Professional.getProList();
		proList.clear();
	}	
}
