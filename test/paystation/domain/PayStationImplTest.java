/**
 * Testcases for the Pay Station system.
 *
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 *
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
package paystation.domain;

import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

public class PayStationImplTest {

    PayStation ps;
    
    //adding test for LinearRateStrategy
    RateStrategy rateStrategy1;
    
    //adding test for ProgressiveRateStrategy
    RateStrategy rateStrategy2;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setup() {
        ps = new PayStationImpl();
        
        //setting up LinearRateStrategy
        rateStrategy1 = new LinearRateStrategy();
        
        //setting up ProgressiveRateStrategy
        rateStrategy2 = new ProgressiveRateStrategy();
    }
    

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Entering 5 cents should make the display report 2 minutes parking time.
     */
    @Test
    public void shouldDisplay2MinFor5Cents()
            throws IllegalCoinException {
        ps.addPayment(5);
        assertEquals("Should display 2 min for 5 cents",
                2, ps.readDisplay());
    }

    /**
     * Entering 25 cents should make the display report 10 minutes parking time.
     */
    @Test
    public void shouldDisplay10MinFor25Cents() throws IllegalCoinException {
        ps.addPayment(25);
        assertEquals("Should display 10 min for 25 cents",
                10, ps.readDisplay());
    }

    /**
     * Verify that illegal coin values are rejected.
     */
    @Test(expected = IllegalCoinException.class)
    public void shouldRejectIllegalCoin() throws IllegalCoinException {
        ps.addPayment(17);
    }

    /**
     * Entering 10 and 25 cents should be valid and return 14 minutes parking
     */
    @Test
    public void shouldDisplay14MinFor10And25Cents()
            throws IllegalCoinException {
        ps.addPayment(10);
        ps.addPayment(25);
        assertEquals("Should display 14 min for 10+25 cents",
                14, ps.readDisplay());
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
     * Buy for 100 cents and verify the receipt
     */
    @Test
    public void shouldReturnReceiptWhenBuy100c()
            throws IllegalCoinException {
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
     * Verify that the pay station is cleared after a buy scenario
     */
    @Test
    public void shouldClearAfterBuy()
            throws IllegalCoinException {
        ps.addPayment(25);
        ps.buy(); // I do not care about the result
        // verify that the display reads 0
        assertEquals("Display should have been cleared",
                0, ps.readDisplay());
        // verify that a following buy scenario behaves properly
        ps.addPayment(10);
        ps.addPayment(25);
        assertEquals("Next add payment should display correct time",
                14, ps.readDisplay());
        Receipt r = ps.buy();
        assertEquals("Next buy should return valid receipt",
                14, r.value());
        assertEquals("Again, display should be cleared",
                0, ps.readDisplay());
    }

    /**
     * Verify that cancel clears the pay station
     */
    @Test
    public void shouldClearAfterCancel()
            throws IllegalCoinException {
        ps.addPayment(10);
        ps.cancel();
        assertEquals("Cancel should clear display",
                0, ps.readDisplay());
        ps.addPayment(25);
        assertEquals("Insert after cancel should work",
                10, ps.readDisplay());
    }


    /**
     * Verify that the canceled entry does not add to the amount returned by
     * empty.
     * @throws Exception 
     */
    @Test
    public void cancelShouldNotAddToEmpty() throws Exception
    {
        PayStationImpl instance = new PayStationImpl();
        int amountAdded = 25;
        instance.addPayment(amountAdded);
        instance.cancel();
        instance.addPayment(amountAdded);
        int result = instance.empty();
        assertEquals(amountAdded, result);
        
    }
    
    /**
     * Very that the empty method resets the total to zero.
     * @throws Exception 
     */
    @Test
    public void testEmptyZero() throws Exception
    {
        PayStationImpl instance = new PayStationImpl();
        int amountAdded = 10;
        instance.addPayment(amountAdded);
        instance.empty();
        int result = instance.empty();
        assertEquals(0, result);
    }
    
    /**
     * Verify that cancel returns a map with the correct amount of coins for one
     * coin type.
     * @throws Exception 
     */
    @Test
    public void test1CoinMapReturn() throws Exception
    {
        PayStationImpl instance = new PayStationImpl();
        int amountAdded = 25;
        instance.addPayment(amountAdded);
        int result = instance.cancel().get(3);
        assertEquals(1, result);
    }
    
    /**
     * Verify that cancel returns a map with the correct amount of coins for
     * all coin types.
     * @throws Exception 
     */
    @Test
    public void testNoCoinMapReturn() throws Exception
    {
        PayStationImpl instance = new PayStationImpl();
        int quarter = 25;
        int dime = 10;
        int nickle = 5;
        //instance.addPayment(quarter);
        instance.addPayment(dime);
        instance.addPayment(dime);
        instance.addPayment(nickle);
        Map answer = new HashMap();
        //answer.put(1, 1);
        answer.put(2, 2);
        answer.put(1, 1);
        Map result = instance.cancel();
        assertEquals(answer, result);
    }
    
    /**
     * Verify that cancel returns a map without keys when no coins are entered.
     * @throws Exception 
     */
    @Test
    public void testMultipleCoinMapReturn() throws Exception
    {
        PayStationImpl instance = new PayStationImpl();
        int quarter = 25;
        int dime = 10;
        int nickle = 5;
        instance.addPayment(dime);
        instance.addPayment(dime);
        instance.addPayment(quarter);
        instance.addPayment(nickle);
        Map answer = new HashMap();
        answer.put(1, 1);
        answer.put(2, 2);
        answer.put(3, 1);
        Map result = instance.cancel();
        assertEquals(answer, result);
    }
 
    /**
     * Verify that cancel clears the map.
     * @throws Exception 
     */
    @Test
    public void testCancelClearMap() throws Exception
    {
        PayStationImpl instance = new PayStationImpl();
        int quarter = 25;
        int dime = 10;
        int nickle = 5;
        instance.addPayment(quarter);
        instance.addPayment(nickle);
        instance.addPayment(dime);
        instance.addPayment(quarter);
        instance.cancel();
        Map result = instance.cancel();
        Map emptyMap = new HashMap();
        assertEquals(emptyMap, result);
    }
    
    /**
     * Verify that buy clears the map.
     * @throws Exception 
     */
    @Test
    public void testBuyClearMap() throws Exception
    {
        PayStationImpl instance = new PayStationImpl();
        int quarter = 25;
        int dime = 10;
        int nickle = 5;
        instance.addPayment(quarter);
        instance.addPayment(nickle);
        instance.addPayment(dime);
        instance.addPayment(quarter);
        instance.buy();
        Map result = instance.cancel();
        Map emptyMap = new HashMap();
        assertEquals(emptyMap, result);
    }
    
    // Testing the LinearRateStrategy
    // Verifying that the linear rate is correct
    @Test
    public void linearRateStrategy0(){
        
        /**
         * 0 cents entered in coins should equal 0
         * Since (0*2)/5=0
         */
        assertEquals("0 cents should be 0", 0, 
                rateStrategy1.calculateTime(0));
        
    }
    @Test
    public void linearRateStrategy50(){
        
        /**
         * 100 cents entered in coins should equal 40
         */
        assertEquals("50 cents should be 20", 40, 
                rateStrategy1.calculateTime(50));
        
    }
    
    // The following tests are used for testing the ProgressiveRateStrategy
    // Verifying that the progressive rate is correct
    @Test
    public void progressiveRateStrategy0(){
        /**
         * 0 cents entered should equal 0 minutes
         */
        assertEquals("0 cents should be 0", 0, 
                rateStrategy2.calculateTime(0));
               
    }
    @Test
    public void progressiveRateStrategy50(){
        /**
         * 50 cents entered should equal 20 minutes
         */
        assertEquals("50 cents should be 20 minutes", 20, 
                rateStrategy2.calculateTime(50));
               
    }
    @Test
    public void progressiveRateStrategy145(){
        /**
         * 50 cents entered should equal 58 minutes
         */
        assertEquals("145 cents should be 58 minutes", 58, 
                rateStrategy2.calculateTime(145));
               
    }
    @Test
    public void progressiveRateStrategy150(){
        /**
         * 150 cents entered should equal 60 minutes
         * Since (150-150)*(3/10)+60 = 0 + 60 = 60
         */
        assertEquals("150 cents should be 60 minutes", 60, 
                rateStrategy2.calculateTime(150));
               
    }
    @Test
    public void progressiveRateStrategy250(){
        /**
         * 250 cents entered should equal 90 minutes
         * Since (250-150)*(3/10)+60 = 100*(3/10) + 60 = 90
         */
        assertEquals("250 cents should be 90 minutes", 90, 
                rateStrategy2.calculateTime(250));
               
    }
    @Test
    public void progressiveRateStrategy345(){
        /**
         * 345 cents entered should equal 90 minutes
         * Since (345-150)*(3/10)+60 = 195*(3/10) + 60 = 118.5
         */
        assertEquals("345 cents should be 118.5 minutes", 118.5, 
                rateStrategy2.calculateTime(345));
               
    }
        @Test
    public void progressiveRateStrategy350(){
        /**
         * 350 cents entered should equal 90 minutes
         * Since (350-350)/5+120 = 0/5 + 120 = 120
         */
        assertEquals("350 cents should be 120 minutes", 120, 
                rateStrategy2.calculateTime(350));
               
    }
    @Test
    public void progressiveRateStrategy(){
        
        /**
         * 100 cents entered should equal 40
         */
        assertEquals("100 cents should be 40", 40, 
                rateStrategy2.calculateTime(100));
        
        /**
         * 200 cents entered should equal 75
         */
        assertEquals("200 cents should be 75", 75,
                rateStrategy2.calculateTime(200));
        
        /**
         * 350 cents entered should equal 120
         */
        assertEquals("350 cents should be 120", 120,
                rateStrategy2.calculateTime(350));
    }
    // Testing the AlternatingRateStrategy
    // Verifying that the Alternate rate is correct
    @Test
    public void AlternatingRateStrategy0(){
        
        /**
         * 0 cents entered in coins should equal 0
         */
        assertEquals("0 cents should be 0", 0, 
                rateStrategy1.calculateTime(0));
        
    }

        
}
