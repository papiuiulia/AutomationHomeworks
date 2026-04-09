package Tema8;

public class Mostenire2 {
    public static void main(String[] args) {
        AdminAccount admin = new AdminAccount();
        admin.setUsername("admin123");

        admin.login();
        admin.deleteUser();
    }
}
