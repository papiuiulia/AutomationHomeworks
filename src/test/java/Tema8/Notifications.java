package Tema8;

public class Notifications {
    public static void main(String[] args) {
        EmailNotification email = new EmailNotification();
        email.setEmailAddress("test@mail.com");
        email.send();
    }
}

class Notification {
    public void send() {
        System.out.println("Sending generic notification...");
    }
}

class EmailNotification extends Notification {
    private String emailAddress;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public void send() {
        System.out.println("Sending email to: " + emailAddress);
    }
}