package Tema8;

public class OnlineCourse extends Course {
    private String platform;

    public OnlineCourse(String courseName, int duration, String platform) {
        super(courseName, duration);
        this.platform = platform;
    }

    public String getPlatform() {
        return platform;
    }
}
