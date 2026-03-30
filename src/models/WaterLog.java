package models;

public class WaterLog extends Log {

    private double amount;
    private double goal;

    public WaterLog(String date, double amount, double goal) {
        super(date, "WATER"); 
        this.amount = amount;
        this.goal = goal;
    }

    public double getLitersConsumed() {
        return amount;
    }

    public double getDailyGoal() {
        return goal;
    }

    @Override
    public void logEntry() {
        System.out.println("💧 " + amount + " L on " + date);
    }

    @Override
    public String getSummary() {
        return String.format("💧 %.2f / %.1f L → %s",
                amount, goal, getStatus());
    }

    private String getStatus() {
        if (amount >= goal) {
            return "Goal reached";
        }

        double percent = (amount / goal) * 100;
        double remaining = goal - amount;

        return String.format("%.0f%% done (%.1f L left)", percent, remaining);
    }

    @Override
    public String toFileString() {
        return String.join(",",
                "WATER",
                date,
                String.valueOf(amount),
                String.valueOf(goal)
        );
    }

    public static WaterLog fromFileString(String line) {
        String[] parts = line.split(",");

        String date = parts[1];
        double amount = Double.parseDouble(parts[2]);
        double goal = Double.parseDouble(parts[3]);

        return new WaterLog(date, amount, goal);
    }
}
