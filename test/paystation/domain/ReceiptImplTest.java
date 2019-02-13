/**
 * Testcases for the Receipt system.
 *
 */
package paystation.domain;

import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

public class ReceiptImplTest {

    PayStation ps;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setup() {
        ps = new PayStationImpl();

    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Buy should return a valid receipt of the proper amount of parking time
     */
    @Test
    public void shouldReturnCorrectReceiptWhenBuy()
            throws IllegalCoinException {
        ps.addPayment(5);
        ps.addPayment(10);
        ps.addPayment(25);
        Receipt receipt;
        receipt = ps.buy();
        assertNotNull("Receipt reference cannot be null",
                receipt);
        assertEquals("Receipt value must be 16 min.",
                16, receipt.value());
    }

    /**
     * LinearRateStrategy Buy for 100 cents and verify the receipt
     */
    @Test
    public void shouldReturnReceiptWhenBuy100cLinear()
            throws IllegalCoinException {

        ps.setRateStrategy(1);

        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(25);
        ps.addPayment(25);

        Receipt receipt;
        receipt = ps.buy();
        assertEquals(40, receipt.value());
    }

    /**
     * ProgressiveRateStrategy Buy for 100 cents and verify the receipt
     */
    @Test
    public void shouldReturnReceiptWhenBuy100cProgressive()
            throws IllegalCoinException {

        ps.setRateStrategy(2);

        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(25);
        ps.addPayment(25);

        Receipt receipt;
        receipt = ps.buy();
        assertEquals(40, receipt.value());
    }

    /**
     * ProgressiveRateStrategy Buy for 200 cents and verify the receipt
     */
    @Test
    public void shouldReturnReceiptWhenBuy200cProgressive()
            throws IllegalCoinException {

        ps.setRateStrategy(2);

        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);

        Receipt receipt;
        receipt = ps.buy();
        assertEquals(75, receipt.value());
    }

    /**
     * ProgressiveRateStrategy Buy for 400 cents and verify the receipt
     */
    @Test
    public void shouldReturnReceiptWhenBuy400cProgressive()
            throws IllegalCoinException {

        ps.setRateStrategy(2);

        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);
        ps.addPayment(25);

        Receipt receipt;
        receipt = ps.buy();
        assertEquals(130, receipt.value());
    }

    /**
     * AlternatingRateStrategy Buy for 100 cents and verify the receipt on a
     * weekday
     */
    @Test
    public void shouldReturnReceiptWhenBuy100cAlternatingWeekday()
            throws IllegalCoinException {

        ps.setRateStrategy(3);

        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(25);
        ps.addPayment(25);

        Receipt receipt;
        receipt = ps.buy();
        try {
            assertEquals(45, receipt.value());
            System.out.println(receipt.value() + " - pass, it is a weekday");
        } catch(AssertionError e) {
            System.out.println(receipt.value() + " - fail, it is not a weekday");
            throw e;
        }
    }
}
