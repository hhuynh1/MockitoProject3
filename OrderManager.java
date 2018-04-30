public class OrderManager {

    private int orderCount;
    private ProcessOrders processor;


    //magic numbers
    private static final double PROFIT_MARGIN = 1.11;
    private static final double LOSS_MARGIN = 1.33;

    public OrderManager(ProcessOrders processor) {
        this.processor = processor;
    }

    public double calculateProfit() {
        int validOrders = orderCount - processor.getFraudulentOrders().size();
        return (validOrders * PROFIT_MARGIN) - (processor.getFraudulentOrders().size() * LOSS_MARGIN);
    }

    public boolean processOrder(int orderNumber) {
        orderCount++;
        try {
            processor.shipOrder(orderNumber);
        } catch (InvalidOrderException e) {
            return false;
        }
        return true;
    }



}
