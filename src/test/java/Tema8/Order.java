package Tema8;

class Order {
    protected int orderId;

    public void printOrder() {
        System.out.println("Order ID: " + orderId);
    }
}

class OnlineOrder extends Order {

    public void trackOrder() {
        // acces permis (protected)
        System.out.println("Tracking order: " + orderId);
    }
}
