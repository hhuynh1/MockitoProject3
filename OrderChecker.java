public class OrderChecker {

    public boolean isValidOrder(Order order) {
        if (order.id <= 0) {
            return false;
        }
        if (order.customerName.isEmpty()) {
            return false;
        }
        if (order.address.isEmpty()) {
            return false;
        }
        if (order.totalPrice < 0.01) {
            return false;
        }
        if (isProfitableSale(order) && !isFromBannedCustomer(order)) {
            return true;
        }
        return false;
    }

    public boolean isProfitableSale(Order order) {
        //some implementation not yet available
        return true;
    }

    public boolean isFromBannedCustomer(Order order) {
        //some implementation not yet available
        return false;
    }
}
