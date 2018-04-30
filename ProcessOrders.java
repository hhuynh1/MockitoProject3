import java.util.ArrayList;
import java.util.List;

/*
 * This class is our dependency.
 * It has code to process orders or maybe the code is not yet implemented.
 * Either way, we do not want to actually execute this code because
 * we don't want to actually "process" orders (or ship orders) for testing purposes.
 * So we will use Mockito to mock this class during testing.
 *
 * This illustrates why we do mock testing.
 * Assume that all the code for this class (ProcessOrders) is written.
 * And assume it has been tested already.
 *
 * Now you want to write OrderManager class that uses this ProcessOrders.
 * OrderManager makes calls to ProcessOrders.
 * But during testing, we decide that making REAL calls to ProcessOrders
 * is a waste of resource because ProcessOrders has expensive methods that
 * make calls to Database or actually "ships" orders.
 *
 * When we test OrderManager class, our goal is to make sure our OrderManager
 * class's code is correct. So we MOCK ProcessOrders to ENABLE the testing
 * of OrderManager class.
 */

public class ProcessOrders {

    public void shipOrder(int id) throws InvalidOrderException {
        //
        //
    }

    public List<Order> getFraudulentOrders() {
        //
        //
        return new ArrayList<>();
    }

}
