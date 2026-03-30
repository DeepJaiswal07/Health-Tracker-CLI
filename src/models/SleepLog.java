package models;

public class SleepLog extends Log {

    private double hours;
    private double goal;
    private String bed; 
    private String wake;

    public SleepLog(String date, double hours, double goal, String bed, String wake) {
        super(date, "SLEEP");
        this.hours = hours;
        this.goal = goal;
        this.bed = bed;
        this.wake = wake;
    }

    public double getHoursSlept() {
        return hours;
    }

    public double getSleepGoal() {
        return goal;
    }

    public String getBedTime() {
        return bed;
    }

    public String getWakeTime() {
        return wake;
    }

    @Override
    public void logEntry() {
        System.out.printf("😴 %.1f hrs (%s → %s) on %s%n",
                hours, bed, wake, date);
    }

    @Override
    public String getSummary() {
        return String.format("😴 %.1f / %.1f hrs (%s → %s) → %s",
                hours, goal, bed, wake, getQuality());
    }

    private String getQuality() {
        if (hours >= goal) return "Well-rested";
        if (hours >= goal - 1) return "Slightly short";
        return "Sleep deficit";
    }

    @Override
    public String toFileString() {
        return String.join(",",
                "SLEEP",
                date,
                String.valueOf(hours),
                String.valueOf(goal),
                bed,
                wake
        );
    }

    public static SleepLog fromFileString(String line) {
        String[] parts = line.split(",");

        String date = parts[1];
        double hours = Double.parseDouble(parts[2]);
        double goal = Double.parseDouble(parts[3]);
        String bed = parts[4];
        String wake = parts[5];

        return new SleepLog(date, hours, goal, bed, wake);
    }
}
