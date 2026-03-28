package models;

public class User {

    private String name;
    private int age;
    private double weight;
    private double targetWeight;
    private double waterGoal;
    private double sleepGoal;

    public User(String name, int age, double weight, double targetWeight,
                double waterGoal, double sleepGoal) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.targetWeight = targetWeight;
        this.waterGoal = waterGoal;
        this.sleepGoal = sleepGoal;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getWeightKg() {
        return weight;
    }

    public double getTargetWeightKg() {
        return targetWeight;
    }

    public double getDailyWaterGoalLiters() {
        return waterGoal;
    }

    public double getDailySleepGoalHours() {
        return sleepGoal;
    }

    public void setWeightKg(double value) {
        this.weight = value;
    }

    public void setTargetWeightKg(double value) {
        this.targetWeight = value;
    }

    public void setDailyWaterGoalLiters(double value) {
        this.waterGoal = value;
    }

    public void setDailySleepGoalHours(double value) {
        this.sleepGoal = value;
    }

    public String toFileString() {
        return String.join(",",
                name,
                String.valueOf(age),
                String.valueOf(weight),
                String.valueOf(targetWeight),
                String.valueOf(waterGoal),
                String.valueOf(sleepGoal)
        );
    }

    public static User fromFileString(String line) {
        String[] parts = line.split(",");

        String name = parts[0];
        int age = Integer.parseInt(parts[1]);
        double weight = Double.parseDouble(parts[2]);
        double target = Double.parseDouble(parts[3]);
        double water = Double.parseDouble(parts[4]);
        double sleep = Double.parseDouble(parts[5]);

        return new User(name, age, weight, target, water, sleep);
    }

    @Override
    public String toString() {
        return String.format(
                "%s | %d yrs | %.1f kg → %.1f kg | Water: %.1fL | Sleep: %.1f hrs",
                name, age, weight, targetWeight, waterGoal, sleepGoal
        );
    }
}