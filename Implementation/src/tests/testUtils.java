package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.DateTimeManipulation;
import utils.FileManager;
import utils.UID;
import static org.junit.Assert.assertEquals;

/**
 * test_Action_StateUnderTest
 */
public class testUtils {

    @Before
    public void beforeShowSynthesisForTest() throws Exception {
        System.out.println("Before for utils/UID Test");
        FileManager.deleteData(true);
        FileManager.initData(false);
        FileManager.readObjects(false);
    }

    /**
     * Desc: Validate the length of UID generated from @param n.
     * Class: utils/UID
     * In: int
     * Out: String
     * Test: Out.length == Int
     */
    @Test
    public void generateUID_CorrectSizeLength() {
        int uidSize1 = 1;
        int uidSize2 = 9;
        String uid1 = UID.generateUID(uidSize1);
        String uid2 = UID.generateUID(uidSize2);

        assertEquals(uidSize1, uid1.length());
        assertEquals(uidSize2, uid2.length());
    }

    /**
     * Desc: Validate the length of the date generated (ext. or not).
     * Class: utils/DateTimeManipulation
     * In: boolean
     * Out: String
     * Test: false (not ext.) == 10, true (ext.) == 19
     */
    @Test
    public void getCurrentDate_CorrectFormat() {
        String value1 = DateTimeManipulation.generateCurrentTime(false);
        String value2 = DateTimeManipulation.generateCurrentTime(true);

        System.out.println(value1);
        System.out.println(value2);
        assertEquals(10, value1.length());
        assertEquals(19, value2.length());
    }

    @After
    public void afterShowSynthesisForTest() {
        System.out.println("After for utils/UID Test");
    }
}