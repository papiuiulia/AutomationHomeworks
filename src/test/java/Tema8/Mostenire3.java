package Tema8;

public class Mostenire3 {
    public static void main(String[] args) {
        OnlineCourse oc = new OnlineCourse("Java OOP", 10, "Udemy");

        System.out.println(oc.getCourseName());
        System.out.println(oc.getDuration());
        System.out.println(oc.getPlatform());
    }
}
