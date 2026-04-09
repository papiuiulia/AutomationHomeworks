package Tema8;

public class Encapsulation2 {
    public static void main(String[] args) {
        Product p = new Product();

        p.setName("Laptop");
        p.setPrice(3000);
        System.out.println(p.getName() + " - " + p.getPrice());

        p.setPrice(-100);
        System.out.println("Pret curent: " + p.getPrice());
    }
}