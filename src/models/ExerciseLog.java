package models;

public class ExerciseLog extends Log {

    private String type; 
    private int minutes;
    private int calories;

    public ExerciseLog(String date, String type, int minutes, int calories) {
        super(date, "EXERCISE");
        this.type = type;
        this.minutes = minutes;
        this.calories = calories;
    }

    public String getExerciseType() {
        return type;
    }

    public int getDurationMinutes() {
        return minutes;
    }

    public int getCaloriesBurned() {
        return calories;
    }

    @Override
    public void logEntry() {
        System.out.printf("🏃 %s for %d min (%d kcal) on %s%n",
                type, minutes, calories, date);
    }

    @Override
    public String getSummary() {
        return String.format("🏃 %s | %d min | %d kcal → %s",
                type, minutes, calories, getIntensity());
    }

    private String getIntensity() {
        if (minutes >= 45) return "High";
        if (minutes >= 20) return "Moderate";
        return "Light";
    }

    @Override
    public String toFileString() {
        return String.join(",",
                "EXERCISE",
                date,
                type.replace(" ", "_"),
                String.valueOf(minutes),
                String.valueOf(calories)
        );
    }

    public static ExerciseLog fromFileString(String line) {
        String[] parts = line.split(",");

        String date = parts[1];
        String type = parts[2].replace("_", " ");
        int minutes = Integer.parseInt(parts[3]);
        int calories = Integer.parseInt(parts[4]);

        return new ExerciseLog(date, type, minutes, calories);
    }
}
