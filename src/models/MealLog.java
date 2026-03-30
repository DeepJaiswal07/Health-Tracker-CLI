package models;

public class MealLog extends Log {

    private String type;
    private String items; 
    private int calories;

    public MealLog(String date, String type, String items, int calories) {
        super(date, "MEAL");
        this.type = type;
        this.items = items;
        this.calories = calories;
    }

    public String getMealType() {
        return type;
    }

    public String getFoodItems() {
        return items;
    }

    public int getEstimatedCalories() {
        return calories;
    }

    @Override
    public void logEntry() {
        System.out.printf("🍎 %s: %s (~%d kcal) on %s%n",
                type, items, calories, date);
    }

    @Override
    public String getSummary() {
        return String.format("🍎 %s | %s | %d kcal → %s",
                type, items, calories, getLabel());
    }

    private String getLabel() {
        if (calories > 700) return "Heavy";
        if (calories > 400) return "Moderate";
        return "Light";
    }

    @Override
    public String toFileString() {
        return String.join(",",
                "MEAL",
                date,
                type,
                items.replace(",", ";"),
                String.valueOf(calories)
        );
    }

    public static MealLog fromFileString(String line) {
        String[] parts = line.split(",");

        String date = parts[1];
        String type = parts[2];
        String items = parts[3].replace(";", ",");
        int calories = Integer.parseInt(parts[4]);

        return new MealLog(date, type, items, calories);
    }
}
