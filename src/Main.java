import models.*;
import services.*;
import utils.DateUtils;

import java.util.Scanner; 

public class Main {

    private static final Scanner input = new Scanner(System.in);

    private static FileService storage;
    private static LogService logs;
    private static User user;

    public static void main(String[] args) {
        showBanner();

        storage = new FileService();
        login();

        boolean active = true;

        while (active) {
            showMenu();
            String choice = input.nextLine().trim();

            switch (choice) {
                case "1" -> addWater();
                case "2" -> addExercise();
                case "3" -> addSleep();
                case "4" -> addMeal();
                case "5" -> showSummary();
                case "6" -> logs.printHistory();
                case "7" -> logs.printWeeklyStats();
                case "8" -> editProfile();
                case "9" -> login();
                case "0" -> {
                    System.out.println("\n👋 Goodbye, " + user.getName());
                    active = false;
                }
                default -> System.out.println("❌ Invalid choice");
            }
        }

        input.close();
    }

    private static void login() {
        System.out.print("\nEnter your name: ");
        String name = input.nextLine().trim();

        if (storage.userExists(name)) {
            user = storage.loadUser(name);
            System.out.println("Welcome back, " + user.getName());
        } else {
            user = createUser(name);
            storage.saveUser(user);
            System.out.println("Profile created");
        }

        logs = new LogService(storage, user);
    }

    private static User createUser(String name) {
        System.out.print("Age: ");
        int age = readInt();

        System.out.print("Weight (kg): ");
        double weight = readDouble();

        System.out.print("Target weight (kg): ");
        double target = readDouble();

        System.out.print("Water goal (default 2.5): ");
        String w = input.nextLine().trim();
        double water = w.isEmpty() ? 2.5 : Double.parseDouble(w);

        System.out.print("Sleep goal (default 8): ");
        String s = input.nextLine().trim();
        double sleep = s.isEmpty() ? 8.0 : Double.parseDouble(s);

        return new User(name, age, weight, target, water, sleep);
    }

    private static void showMenu() {
        System.out.println("\n──────── MENU ────────");
        System.out.println(user.getName() + " | " + DateUtils.today());
        System.out.println("[1] Water  [2] Exercise  [3] Sleep  [4] Meal");
        System.out.println("[5] Summary [6] History  [7] Stats");
        System.out.println("[8] Profile [9] Switch   [0] Exit");
        System.out.print("Choice: ");
    }

    private static void addWater() {
        System.out.print("Liters: ");
        double val = readDouble();

        if (val <= 0) {
            System.out.println("Invalid amount");
            return;
        }

        logs.logWater(val);
    }

    private static void addExercise() {
        System.out.print("Type: ");
        String type = input.nextLine().trim();

        if (type.isEmpty()) {
            System.out.println("Invalid type");
            return;
        }

        System.out.print("Minutes: ");
        int minutes = readInt();

        System.out.print("Calories: ");
        int calories = readInt();

        logs.logExercise(type, minutes, calories);
    }

    private static void addSleep() {
        System.out.print("Hours: ");
        double hours = readDouble();

        System.out.print("Bed time: ");
        String bed = input.nextLine().trim();

        System.out.print("Wake time: ");
        String wake = input.nextLine().trim();

        logs.logSleep(hours, bed, wake);
    }

    private static void addMeal() {
        System.out.print("Meal type (Breakfast/Lunch/Dinner/Snack): ");
        String meal = input.nextLine().trim();

        System.out.print("Food: ");
        String food = input.nextLine().trim();
        if (food.isEmpty()) food = "Unknown";

        System.out.print("Calories: ");
        int cal = readInt();

        logs.logMeal(meal, food, cal);
    }

    private static void showSummary() {
        System.out.print("1: Today | 2: Custom date → ");
        String c = input.nextLine().trim();

        if (c.equals("2")) {
            System.out.print("Date (yyyy-MM-dd): ");
            String date = input.nextLine().trim();

            if (!DateUtils.isValidDate(date)) {
                System.out.println("Invalid format");
                return;
            }

            logs.printDailySummary(date);
        } else {
            logs.printDailySummary(DateUtils.today());
        }
    }

    private static void editProfile() {
        System.out.println("\n1 Weight  2 Target  3 Water  4 Sleep  0 Back");
        String c = input.nextLine().trim();

        switch (c) {
            case "1" -> {
                System.out.print("New weight: ");
                user.setWeightKg(readDouble());
            }
            case "2" -> {
                System.out.print("New target: ");
                user.setTargetWeightKg(readDouble());
            }
            case "3" -> {
                System.out.print("Water goal: ");
                user.setDailyWaterGoalLiters(readDouble());
            }
            case "4" -> {
                System.out.print("Sleep goal: ");
                user.setDailySleepGoalHours(readDouble());
            }
            default -> { return; }
        }

        storage.saveUser(user);
        System.out.println("Updated");
    }

    private static int readInt() {
        try {
            return Integer.parseInt(input.nextLine().trim());
        } catch (Exception e) {
            return 0;
        }
    }

    private static double readDouble() {
        try {
            return Double.parseDouble(input.nextLine().trim());
        } catch (Exception e) {
            return 0.0;
        }
    }

    private static void showBanner() {
        System.out.println("\n=== HEALTH TRACKER ===\n");
    }
}
