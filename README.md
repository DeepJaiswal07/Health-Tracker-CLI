# 🏥 Personal Health Tracker CLI

A console-based Java application to track your daily health habits — water intake, exercise, sleep, and meals — all saved locally without any internet or subscription required.

---

## 📌 Problem Statement

Most health tracking solutions are either expensive apps, require internet connectivity, or collect your personal data. Students and everyday users need a **simple, offline, privacy-respecting** tool to build consistent health habits. This project solves that with a lightweight Java CLI app.

---

## ✨ Features

| Feature | Description |
|---|---|
| 👤 User Profiles | Create and load personal profiles with health goals |
| 💧 Water Intake | Log water consumption; get goal progress feedback |
| 🏃 Exercise | Log workout type, duration, and calories burned |
| 😴 Sleep | Log sleep hours with bed/wake times and quality rating |
| 🍎 Meals | Log meals by type (Breakfast/Lunch/Dinner/Snack) with calorie estimates |
| 📊 Daily Summary | Full health snapshot for any date |
| 📅 History | View all past log entries grouped by date |
| 📈 Stats | Aggregated averages and goal achievement counts |
| 💾 Persistent Storage | All data saved to local `.txt` files — no database needed |
| 👥 Multi-User | Multiple user profiles supported on the same machine |

---

## 🧠 Java Concepts Used

- **OOP** — Abstract `Log` base class; `WaterLog`, `ExerciseLog`, `SleepLog`, `MealLog` as subclasses
- **Interfaces** — `Trackable` interface enforces `logEntry()`, `getSummary()`, and `toFileString()` across all log types
- **Encapsulation** — All model fields are `private` with proper getters/setters
- **Collections** — `ArrayList`, `HashMap`, `LinkedHashMap` for managing and grouping log data
- **File I/O** — `BufferedReader` / `BufferedWriter` for reading and writing CSV-style text files
- **Switch Expressions** — Java 14+ switch expressions used in the CLI menu

---

## 📁 Project Structure

```
HealthTracker/
├── src/
│   ├── Main.java                    ← Entry point & CLI menu
│   ├── models/
│   │   ├── Log.java                 ← Abstract base class
│   │   ├── User.java                ← User profile model
│   │   ├── WaterLog.java            ← Water intake log
│   │   ├── ExerciseLog.java         ← Exercise session log
│   │   ├── SleepLog.java            ← Sleep entry log
│   │   └── MealLog.java             ← Meal entry log
│   ├── interfaces/
│   │   └── Trackable.java           ← Interface for all log types
│   ├── services/
│   │   ├── LogService.java          ← Business logic, summaries, stats
│   │   └── FileService.java         ← All file read/write operations
│   └── utils/
│       └── DateUtils.java           ← Date formatting helpers
└── data/                            ← Auto-created; stores user files
    ├── user_<name>.txt
    └── logs_<name>.txt
```

---

## ⚙️ Setup & How to Run

### Prerequisites
- Java JDK 11 or higher installed
- A terminal / command prompt

### Step 1 — Clone the Repository
```bash
git clone https://github.com/YOUR_USERNAME/health-tracker-cli.git
cd health-tracker-cli
```

### Step 2 — Compile
```bash
mkdir out
javac -d out $(find src -name "*.java")
```

### Step 3 — Run
```bash
cd out
java Main
```

> **Note:** The `data/` folder will be created automatically in whichever directory you run the program from.

---

## 🖥️ Usage Walkthrough

```
  First Launch — New User Setup
  ─────────────────────────────
  Enter your name: Arjun
  → New user detected. Setting up profile...
  Age: 21
  Current weight (kg): 72
  Target weight (kg): 68
  Daily water goal (L): 3.0
  Daily sleep goal (hrs): 8.0
  ✅ Profile created!

  Main Menu
  ─────────
  [1] 💧 Log Water Intake
  [2] 🏃 Log Exercise
  [3] 😴 Log Sleep
  [4] 🍎 Log Meal
  [5] 📊 View Daily Summary
  [6] 📅 View Full History
  [7] 📈 View Overall Stats
  [8] ✏️  Update Profile
  [9] 🔄 Switch User
  [0] 🚪 Exit
```

---

## 💾 Data Storage Format

All data is stored in plain text CSV files in the `data/` folder.

**User file** (`data/user_deep.txt`):
```
deep,21,72.0,68.0,3.0,8.0
```

**Log file** (`data/logs_deep.txt`):
```
WATER,2025-06-10,2.5,3.0
EXERCISE,2025-06-10,Running,30,250
SLEEP,2025-06-10,7.5,8.0,11:00 PM,06:30 AM
MEAL,2025-06-10,Breakfast,Oats and banana,350
```

---

## 🔮 Possible Future Enhancements

- BMI calculator based on current weight and height
- Weekly PDF/HTML report export
- Calorie goal tracking
- JavaFX GUI version
- Reminders using scheduled threads

---

## 👨‍💻 Author

Deep Jaiswal
Reg. No: 24BAI10750
Course: Programming In Java
Institution: VIT Bhopal University

---

## 📄 License

This project is for academic purposes under the BYOP capstone submission for programming in java course
