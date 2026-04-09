package Tema8;

public class Encapsulation1 {
    public static void main(String[] args) {
        Book book = new Book();
        book.setTitle("Ion");
        book.setAuthor("Liviu Rebreanu");

        System.out.println(book.getTitle());
        System.out.println(book.getAuthor());
    }
}