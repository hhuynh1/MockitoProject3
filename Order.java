public class Order {

    int id;
    String customerName;
    String address;
    double totalPrice;
    boolean hasShipped;


    public Order(int id) {
        this.id = id;
    }

    public Order(int id, String customerName, String address, double totalPrice, boolean hasShipped) {
        this.id = id;
        this.customerName = customerName;
        this.address = address;
        this.totalPrice = totalPrice;
        this.hasShipped = hasShipped;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isHasShipped() {
        return hasShipped;
    }

    public void setHasShipped(boolean hasShipped) {
        this.hasShipped = hasShipped;
    }

}
