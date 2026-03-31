public class oopExercises {

    // 1. Clasa Dog
    static class Dog {
        String name;
        int age;

        Dog(String name, int age) {
            this.name = name;
            this.age = age;
        }

        void bark() {
            System.out.println("Cainele " + name + " latra");
        }

        int getAgeInHumanYears() {
            return age * 7;
        }
    }

    // 2. Clasa BankAccount
    static class BankAccount {
        String ownerName;
        double balance;

        BankAccount(String ownerName, double balance) {
            this.ownerName = ownerName;
            this.balance = balance;
        }

        void deposit(double amount) {
            balance += amount;
        }

        void withdraw(double amount) {
            if (balance >= amount) {
                balance -= amount;
            } else {
                System.out.println("Fonduri insuficiente!");
            }
        }

        void displayBalance() {
            System.out.println("Soldul lui " + ownerName + " este: " + balance);
        }
    }

    // 3. Clasa Temperature
    static class Temperature {
        double celsius;

        Temperature(double celsius) {
            this.celsius = celsius;
        }

        double toFahrenheit() {
            return celsius * 9 / 5 + 32;
        }

        double toKelvin() {
            return celsius + 273.15;
        }
    }

    // 4. Clasa Employee
    static class Employee {
        String name;
        double salary;

        Employee(String name, double salary) {
            this.name = name;
            this.salary = salary;
        }

        void increaseSalary(double percent) {
            salary += salary * percent / 100;
        }

        double getSalary() {
            return salary;
        }
    }

    // 5. Clasa ShoppingCart
    static class ShoppingCart {
        double totalPrice;

        void addProduct(double price) {
            totalPrice += price;
        }

        void removeProduct(double price) {
            if (totalPrice - price >= 0) {
                totalPrice -= price;
            } else {
                System.out.println("Totalul nu poate deveni negativ!");
            }
        }

        double getTotal() {
            return totalPrice;
        }
    }

    public static void main(String[] args) {

        // 1. Dog
        Dog dog1 = new Dog("Rex", 3);
        Dog dog2 = new Dog("Max", 5);

        dog1.bark();
        dog2.bark();

        System.out.println(dog1.name + " are " + dog1.getAgeInHumanYears() + " ani umani");
        System.out.println(dog2.name + " are " + dog2.getAgeInHumanYears() + " ani umani");

        System.out.println("-----");

        // 2. BankAccount
        BankAccount account = new BankAccount("Ion", 100);

        account.deposit(50);
        account.deposit(30);
        account.withdraw(70);
        account.displayBalance();

        System.out.println("-----");

        // 3. Temperature
        Temperature temp = new Temperature(25);

        System.out.println("Fahrenheit: " + temp.toFahrenheit());
        System.out.println("Kelvin: " + temp.toKelvin());

        System.out.println("-----");

        // 4. Employee
        Employee emp1 = new Employee("Ana", 3000);
        Employee emp2 = new Employee("Mihai", 4000);

        emp1.increaseSalary(10);

        System.out.println(emp1.name + " salariu nou: " + emp1.getSalary());
        System.out.println(emp2.name + " salariu: " + emp2.getSalary());

        System.out.println("-----");

        // 5. ShoppingCart
        ShoppingCart cart = new ShoppingCart();

        cart.addProduct(20);
        cart.addProduct(35);
        cart.addProduct(15);

        cart.removeProduct(35);

        System.out.println("Total cos: " + cart.getTotal());
    }
}