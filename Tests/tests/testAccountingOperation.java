package tests;

import static org.junit.Assert.assertEquals;
import controller.adv.AccountingOperation;
import model.SubSession;
import model.dto.SynthesisDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.FileManager;
import java.util.LinkedList;

public class testAccountingOperation extends testGlobal {
	private final AccountingOperation accountingOperation = new AccountingOperation();
	private LinkedList<SubSession> listSubSessions = SubSession.getListSubSession();

	@Before
	public void before() throws Exception {
		System.out.println("Before Show Synthesis For Test");
		FileManager.deleteData(true);
		FileManager.initData(true);
		FileManager.readObjects(true);
	}

	/**
	 * Desc: Validate the Synthesis total cost and number of services.
	 * Class: action/AccountingOperation
	 * In: LinkedList<Report>
	 * Out: Synthesis
	 * Test: reportListSize  	== 1
	 * 		 totalNbReports  	== 1
	 * 		 totalNbServices 	== 1
	 * 		 totalNbSubServices == 2
	 * 		 totalCost 		 	== 10 + 10 -> 20
	 */
	@Test
	public void test_synthesisGeneration_fromSeeding() {
		SynthesisDTO synthesisDTO = accountingOperation.generateSynthesis();
		assertEquals(1, synthesisDTO.getListReport().size());
		assertEquals(1, synthesisDTO.getTotalNbReports());
		assertEquals(1, synthesisDTO.getTotalNbServices());
		assertEquals(2, synthesisDTO.getTotalNbSubServices());
		assertEquals(20, synthesisDTO.getTotalCost(), DELTA_LOSS);
	}

	/**
	 * Desc: Validate the Synthesis total cost and number of services after a subService has been added.
	 * Class: action/AccountingOperation
	 * In: LinkedList<Report>
	 * Out: Synthesis
	 * Test: reportListSize  	== 1
	 * 		 totalNbReports  	== 1
	 * 		 totalNbServices 	== 1
 	 *	 	 totalNbSubServices == 3
	 * 		 totalCost 		 	== (10 + 10) + 50 -> 70
	 */
	@Test
	public void test_synthesisGeneration_withAddedSubService() {
		SubSession subSession = new SubSession(
				"01-05-2021 13:00:00",
				"01-05-2021",
				"01-01-2022",
				50.0,
				"commentSS",
				"000",
				"0007777",
				"123456789",
				"987654321"
		);
		listSubSessions.add(subSession);
		SynthesisDTO synthesisDTO = accountingOperation.generateSynthesis();
		
		assertEquals(1, synthesisDTO.getListReport().size());
		assertEquals(1, synthesisDTO.getTotalNbReports());
		assertEquals(1, synthesisDTO.getTotalNbServices());
		assertEquals(3, synthesisDTO.getTotalNbSubServices());
		assertEquals(70, synthesisDTO.getTotalCost(), DELTA_LOSS);
	}
	
	@After
	public void after() {
		System.out.println("After Show Synthesis For Test");
		listSubSessions = SubSession.getListSubSession();
		listSubSessions.clear();
	}	
}
