/**
 * Created by: Henry Huynh
 * ITEC 4260 Software Testing & QA
 * Project 3
 * Spring 2018
 */

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.mockito.Mockito.*;

public class OrderManagerTest {
    // Creating objects for the OrderManager, ProcessOrders, and Order class
    private OrderManager orderManager;
    private ProcessOrders processorMock;
    private Order order;

    /**
     *  The setup() method will create objects of the private instance variables.
     *  Order object will be populated with the required parameters in the constructor
     *  A new mock object of ProcessOrder will be created
     *  OrderManager will be passed with an instance of the mock object ; processorMock
     */
    @Before
    public void setup() {
        //GIVEN - setup mock and other necessary objects
        // Create Order object and passing the required parameters for the Order constructor
        order = new Order(404, "Henry", "1234 Sesame Street, Sugar Hill, GA 30518", 550.75, true );

        // create ProcessOrders mock object
        processorMock = mock(ProcessOrders.class);

        // create OrderManager instance with mock object
        orderManager = new OrderManager(processorMock);
    }

    /**
     *  The testProcessValidOrder() will test if a order is successfully processed
     *  The method will first retrieve the order ID
     *  Then, the processOrder() will be retrieved from the OrderManager class by the orderManager object
     *  After, the method will verify the expected result which should be true
     *  Lastly, the method will verify that the ship order method is called with the orderID of 251
     */
    @Test
    public void testProcessValidOrder() {

        //WHEN - have the mock object do something (that you want to test)
        doNothing().when(processorMock).shipOrder(order.getId());

        // call processOrder() method of orderManager with any integer and store in
        // Randomly passing the int value of 251 as the order number
        boolean validOrder = orderManager.processOrder(251);

        //THEN - verify expected result
        Assert.assertEquals(true, validOrder);

        // A mock will remember all interactions.
        // You can selectively verify that specific methods were actually called.
        verify(processorMock).shipOrder(251);

        // You can also verify that no other interactions on the mock object
        // happened other than the ones expected above
        verifyNoMoreInteractions(processorMock);
    }

    /**
     *  The testProcessInvalidOrder() will test if a order is successfully processed
     *  A new object of Order will be created and initialize it's constructor with the orderID of 123
     *  Then, the method will throw a InvalidOrderException() when the order number is 123
     *  The boolean will retrieve the orderID from the processOrder() in the OrderManager class
     *  Last, the method will verify the expected result which should be false and that the correct method was called
     */
    @Test
    public void testProcessInvalidOrder() {

        //***
        //GIVEN - setup mock and define expected behavior
        Order notValidOrder = new Order(123);

        // define return value when ProcessOrders methods are called
        // throw InvalidOrderException() when order num is 123
        doThrow(new InvalidOrderException()).when(processorMock).shipOrder(notValidOrder.getId());

        //WHEN - have the mock object do something (that you want to test)
        boolean invalidOrder = orderManager.processOrder(notValidOrder.getId());

        //we expect this to be false
        //THEN - verify expected result
        Assert.assertEquals(false, invalidOrder);

        //A mock will remember all interactions.
        //You can selectively verify that specific methods were actually called.
        verify(processorMock).shipOrder(notValidOrder.getId());

        //you can also verify that no other interactions on the mock object
        //happened other than the ones expected above
        verifyNoMoreInteractions(processorMock);
    }

    /**
     * The testProfitHappyCase() will test if the profits are valid
     * A List is created of the Order class
     * The method will then retrieve any fraudulent orders and return the order ID
     * If there are no fraudulent orders, the method will return true
     * Last, the method will verify the expected result and make sure
     * the right method was called
     */
    @Test
    public void testProfitHappyCase() {
        //GIVEN - setup mock and define expected behavior

        // define return value when getFraudulentOrders is called --
        // return empty list, meaning 0 fraudulent orders
        List<Order> fraudulentOrder = new ArrayList<>();

        //WHEN - have the mock object do something (that you want to test)
        when(processorMock.getFraudulentOrders()).thenReturn(fraudulentOrder);
        boolean noFraudulentOrders = processorMock.getFraudulentOrders().isEmpty();

        //THEN - verify expected result
        Assert.assertEquals(true, noFraudulentOrders);
        verify(processorMock).getFraudulentOrders();
        verifyNoMoreInteractions(processorMock);
    }

    /**
     * The testProfitWithFrauds() will test for profits from fraudulent orders
     * Five fraudulent order objects are created and added to the ArrayList from the Order Class
     * The method will then retrieve the fraudulent orders and return the list of fraudulent orderID from the list
     * Verifying all the expected results which should be true and ensure the correct method was called
     */
    @Test
    public void testProfitWithFrauds() {
        Random random = new Random();

        // Generating random order numbers from 1 to 400
        int randomOrderID = random.nextInt(400) + 1;
        
        //GIVEN - setup mock and define expected behavior
        Order fraudulentOrder1 = new Order(randomOrderID, null, null, 0.00, true);
        Order fraudulentOrder2 = new Order(randomOrderID, null, "1786 Chocolate Milk Street, Sugar Hill, GA 30518", 0.00, true);
        Order fraudulentOrder3 = new Order(randomOrderID, "Bill Gates", null, 0.00, true);
        Order fraudulentOrder4 = new Order(randomOrderID, null, null, 0.00, false);
        Order fraudulentOrder5 = new Order(randomOrderID, "Donald Trump", "1287 Candy Land, Lawrenceville , GA 30043", 0.00, true);

        // Adding Fraudulent Orders to the ArrayList
        ArrayList<Order> fraudulentOrderList = new ArrayList<>();
        fraudulentOrderList.add(fraudulentOrder1);
        fraudulentOrderList.add(fraudulentOrder2);
        fraudulentOrderList.add(fraudulentOrder3);
        fraudulentOrderList.add(fraudulentOrder4);
        fraudulentOrderList.add(fraudulentOrder5);

        //WHEN - have the mock object do something (that you want to test)
        when(processorMock.getFraudulentOrders()).thenReturn(fraudulentOrderList);
        boolean containFraudOrders = !processorMock.getFraudulentOrders().isEmpty();

        //THEN - verify expected result
        Assert.assertEquals(true, containFraudOrders);

        // Verifying that the method was called
        verify(processorMock, times(1)).getFraudulentOrders();
        verifyNoMoreInteractions(processorMock);
    }

}
