package Tema8;

public class LoginService {

    public void login(User user) {
        System.out.println("Logging in user: " + user.getUsername());

        // simulare login
        if (user.getUsername().equals("admin") &&
                user.getPassword().equals("1234")) {

            System.out.println("Login SUCCESS");
        } else {
            System.out.println("Login FAILED");
        }
    }
}
