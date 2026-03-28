package services;

import models.*;
import utils.DateUtils;

import java.util.*;

public class LogService {

    private final FileService storage;
    private User user;

    public LogService(FileService storage, User user) {
        this.storage = storage;
        this.user = user;
    }

    public void setCurrentUser(User user) {
        this.user = user;
    }

    public void logWater(double liters) {
        WaterLog entry = new WaterLog(
                DateUtils.today(),
                liters,
                user.getDailyWaterGoalLiters()
        );

        storage.appendLog(user.getName(), entry);
        entry.logEntry();
    }

    public void logExercise(String type, int minutes, int calories) {
        ExerciseLog entry = new ExerciseLog(
                DateUtils.today(),
                type,
                minutes,
                calories
        );

        storage.appendLog(user.getName(), entry);
        entry.logEntry();
    }

    public void logSleep(double hours, String bed, String wake) {
        SleepLog entry = new SleepLog(
                DateUtils.today(),
                hours,
                user.getDailySleepGoalHours(),
                bed,
                wake
        );

        storage.appendLog(user.getName(), entry);
        entry.logEntry();
    }

    public void logMeal(String meal, String items, int calories) {
        MealLog entry = new MealLog(
                DateUtils.today(),
                meal,
                items,
                calories
        );

        storage.appendLog(user.getName(), entry);
        entry.logEntry();
    }

    public void printDailySummary(String date) {
        List<Log> logs = storage.loadLogsForDate(user.getName(), date);

        System.out.println();
        System.out.println("══════════════════════════════════════════════════════════");
        System.out.println("DAILY SUMMARY — " + DateUtils.formatDisplay(date));
        System.out.println("══════════════════════════════════════════════════════════");

        if (logs.isEmpty()) {
            System.out.println("No data for this day.");
            return;
        }

        double waterTotal = 0;
        int caloriesIn = 0;
        int caloriesOut = 0;

        List<WaterLog> water = new ArrayList<>();
        List<ExerciseLog> exercise = new ArrayList<>();
        List<SleepLog> sleep = new ArrayList<>();
        List<MealLog> meals = new ArrayList<>();

        for (Log log : logs) {
            if (log instanceof WaterLog) water.add((WaterLog) log);
            if (log instanceof ExerciseLog) exercise.add((ExerciseLog) log);
            if (log instanceof SleepLog) sleep.add((SleepLog) log);
            if (log instanceof MealLog) meals.add((MealLog) log);
        }

        if (!water.isEmpty()) {
            for (WaterLog w : water) {
                waterTotal += w.getLitersConsumed();
            }
            System.out.println("\n💧 Water: " +
                    new WaterLog(date, waterTotal, user.getDailyWaterGoalLiters()).getSummary());
        }

        if (!meals.isEmpty()) {
            System.out.println("\n🍎 Meals:");
            for (MealLog m : meals) {
                System.out.println(m.getSummary());
                caloriesIn += m.getEstimatedCalories();
            }
            System.out.println("Total Intake: " + caloriesIn + " kcal");
        }

        if (!exercise.isEmpty()) {
            System.out.println("\n🏃 Exercise:");
            for (ExerciseLog e : exercise) {
                System.out.println(e.getSummary());
                caloriesOut += e.getCaloriesBurned();
            }
            System.out.println("Total Burned: " + caloriesOut + " kcal");
        }

        if (!sleep.isEmpty()) {
            System.out.println("\n😴 Sleep:");
            for (SleepLog s : sleep) {
                System.out.println(s.getSummary());
            }
        }

        if (caloriesIn > 0 || caloriesOut > 0) {
            int net = caloriesIn - caloriesOut;
            String status = net > 500 ? "High surplus"
                    : net < 0 ? "Deficit"
                    : "Balanced";

            System.out.println("\n⚖️ Net Calories: " + net + " → " + status);
        }

        System.out.println("──────────────────────────────────────────────────────────");
    }

    public void printHistory() {
        List<Log> all = storage.loadAllLogs(user.getName());

        if (all.isEmpty()) {
            System.out.println("No history found.");
            return;
        }

        Map<String, List<Log>> grouped = new LinkedHashMap<>();

        for (Log log : all) {
            grouped
                .computeIfAbsent(log.getDate(), k -> new ArrayList<>())
                .add(log);
        }

        System.out.println("\n📅 HISTORY — " + user.getName());

        for (Map.Entry<String, List<Log>> entry : grouped.entrySet()) {
            System.out.println("\n" + DateUtils.formatDisplay(entry.getKey())
                    + " (" + entry.getValue().size() + ")");

            for (Log log : entry.getValue()) {
                System.out.println("  " + log.getSummary());
            }
        }
    }

    public void printWeeklyStats() {
        List<Log> all = storage.loadAllLogs(user.getName());

        if (all.isEmpty()) {
            System.out.println("No stats available.");
            return;
        }

        Map<String, Double> water = new HashMap<>();
        Map<String, Double> sleep = new HashMap<>();
        Map<String, Integer> exercise = new HashMap<>();

        for (Log log : all) {
            String d = log.getDate();

            if (log instanceof WaterLog) {
                water.merge(d, ((WaterLog) log).getLitersConsumed(), Double::sum);
            }

            if (log instanceof SleepLog) {
                sleep.merge(d, ((SleepLog) log).getHoursSlept(), Double::sum);
            }

            if (log instanceof ExerciseLog) {
                exercise.merge(d, ((ExerciseLog) log).getDurationMinutes(), Integer::sum);
            }
        }

        int exerciseDays = exercise.size();

        int waterGoalDays = 0;
        for (double w : water.values()) {
            if (w >= user.getDailyWaterGoalLiters()) {
                waterGoalDays++;
            }
        }

        int goodSleepDays = 0;
        for (double s : sleep.values()) {
            if (s >= user.getDailySleepGoalHours()) {
                goodSleepDays++;
            }
        }

        double avgWater = water.values().stream()
                .mapToDouble(Double::doubleValue).average().orElse(0);

        double avgSleep = sleep.values().stream()
                .mapToDouble(Double::doubleValue).average().orElse(0);

        System.out.println("\n📊 STATS — " + user.getName());
        System.out.println("💧 Avg Water: " + avgWater + " L");
        System.out.println("😴 Avg Sleep: " + avgSleep + " hrs");
        System.out.println("🏃 Exercise Days: " + exerciseDays);
        System.out.println("✅ Water Goals Met: " + waterGoalDays);
        System.out.println("😊 Good Sleep Days: " + goodSleepDays);
    }
}