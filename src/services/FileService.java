package services;

import models.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    private static final String BASE_FOLDER = "data/";

    public FileService() {
        File folder = new File(BASE_FOLDER);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    public void saveUser(User user) {
        String filePath = BASE_FOLDER + "user_" + clean(user.getName()) + ".txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(user.toFileString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Failed to save user: " + e.getMessage());
        }
    }

    public User loadUser(String name) {
        String filePath = BASE_FOLDER + "user_" + clean(name) + ".txt";
        File file = new File(filePath);

        if (!file.exists()) {
            return null;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String data = br.readLine();
            if (data != null && !data.isBlank()) {
                return User.fromFileString(data);
            }
        } catch (IOException e) {
            System.out.println("Failed to load user: " + e.getMessage());
        }

        return null;
    }

    public boolean userExists(String name) {
        String filePath = BASE_FOLDER + "user_" + clean(name) + ".txt";
        return new File(filePath).exists();
    }

    public void appendLog(String userName, Log log) {
        String filePath = BASE_FOLDER + "logs_" + clean(userName) + ".txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(log.toFileString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Failed to save log: " + e.getMessage());
        }
    }

    public List<Log> loadLogsForDate(String userName, String date) {
        List<Log> result = new ArrayList<>();
        String filePath = BASE_FOLDER + "logs_" + clean(userName) + ".txt";

        File file = new File(filePath);
        if (!file.exists()) {
            return result;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;

                Log log = convert(line);

                if (log != null && log.getDate().equals(date)) {
                    result.add(log);
                }
            }

        } catch (IOException e) {
            System.out.println("Failed to read logs: " + e.getMessage());
        }

        return result;
    }

    public List<Log> loadAllLogs(String userName) {
        List<Log> result = new ArrayList<>();
        String filePath = BASE_FOLDER + "logs_" + clean(userName) + ".txt";

        File file = new File(filePath);
        if (!file.exists()) {
            return result;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;

                Log log = convert(line);
                if (log != null) {
                    result.add(log);
                }
            }

        } catch (IOException e) {
            System.out.println("Failed to read logs: " + e.getMessage());
        }

        return result;
    }

    private Log convert(String line) {
        try {
            if (line.startsWith("WATER")) return WaterLog.fromFileString(line);
            if (line.startsWith("EXERCISE")) return ExerciseLog.fromFileString(line);
            if (line.startsWith("SLEEP")) return SleepLog.fromFileString(line);
            if (line.startsWith("MEAL")) return MealLog.fromFileString(line);
        } catch (Exception e) {
            System.out.println("Skipping invalid entry: " + line);
        }

        return null;
    }

    private String clean(String name) {
        return name.trim().toLowerCase().replaceAll("\\s+", "_");
    }
}
