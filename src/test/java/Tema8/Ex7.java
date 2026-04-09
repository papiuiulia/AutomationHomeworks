package Tema8;

public class Ex7 {
    public static void main(String[] args) {
        OnlineOrder order = new OnlineOrder();
        order.orderId = 123; // acces permis (protected în același pachet)
        order.printOrder();
        order.trackOrder();
    }
}