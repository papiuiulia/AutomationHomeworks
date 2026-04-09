package Tema8;

public class Ex9 {
    public static void main(String[] args) {

        User user = new User("admin", "1234");
        LoginService loginService = new LoginService();

        loginService.login(user);
    }
}
