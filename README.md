# Personal Health Tracker CLI

A console-based Java application to track your daily health habits — water intake, exercise, sleep, and meals — all saved locally without any internet or subscription required.

---

##  Problem Statement

Most health tracking solutions are either expensive apps, require internet connectivity, or collect your personal data. Students and everyday users need a **simple, offline, privacy-respecting** tool to build consistent health habits. This project solves that with a lightweight Java CLI app.

---
##  Screenshots

###  Main Menu
<img src="https://raw.githubusercontent.com/DeepJaiswal07/Health-Tracker-CLI/main/assets/Main_menu.png" width="700"/>

###  Logging Water Intake
<img src="https://raw.githubusercontent.com/DeepJaiswal07/Health-Tracker-CLI/main/assets/log_water.png" width="700"/>

###  Logging Exercise
<img src="https://raw.githubusercontent.com/DeepJaiswal07/Health-Tracker-CLI/main/assets/log_exercise.png" width="700"/>

###  Logging Sleep
<img src="https://raw.githubusercontent.com/DeepJaiswal07/Health-Tracker-CLI/main/assets/log_sleep.png" width="700"/>

###  Logging a Meal
<img src="https://raw.githubusercontent.com/DeepJaiswal07/Health-Tracker-CLI/main/assets/log_meal.png" width="700"/>

###  Daily Health Summary
<img src="https://raw.githubusercontent.com/DeepJaiswal07/Health-Tracker-CLI/main/assets/daily_summary.png" width="700"/>

###  History View
<img src="https://raw.githubusercontent.com/DeepJaiswal07/Health-Tracker-CLI/main/assets/history.png" width="700"/>

### Stats View
<img src="https://raw.githubusercontent.com/DeepJaiswal07/Health-Tracker-CLI/main/assets/stats.png" width="700"/>

### Profile View
<img src="https://raw.githubusercontent.com/DeepJaiswal07/Health-Tracker-CLI/main/assets/profile.png" width="700"/>

##  Features

| Feature | Description |
|---|---|
|  User Profiles | Create and load personal profiles with health goals |
|  Water Intake | Log water consumption; get goal progress feedback |
|  Exercise | Log workout type, duration, and calories burned |
|  Sleep | Log sleep hours with bed/wake times and quality rating |
|  Meals | Log meals by type (Breakfast/Lunch/Dinner/Snack) with calorie estimates |
|  Daily Summary | Full health snapshot for any date |
|  History | View all past log entries grouped by date |
|  Stats | Aggregated averages and goal achievement counts |
|  Persistent Storage | All data saved to local `.txt` files — no database needed |
|  Multi-User | Multiple user profiles supported on the same machine |

---

##  Java Concepts Used

- **OOP** — Abstract `Log` base class; `WaterLog`, `ExerciseLog`, `SleepLog`, `MealLog` as subclasses
- **Interfaces** — `Trackable` interface enforces `logEntry()`, `getSummary()`, and `toFileString()` across all log types
- **Encapsulation** — All model fields are `private` with proper getters/setters
- **Collections** — `ArrayList`, `HashMap`, `LinkedHashMap` for managing and grouping log data
- **File I/O** — `BufferedReader` / `BufferedWriter` for reading and writing CSV-style text files
- **Switch Expressions** — Java 14+ switch expressions used in the CLI menu

---

##  Project Structure

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
- Java JDK 11 or higher installed → Download from [https://www.oracle.com/java/technologies/downloads/](https://www.oracle.com/java/technologies/downloads/)
- A terminal / Command Prompt

### Step 1 — Clone the Repository
```bash
git clone https://github.com/DeepJaiswal07/Health-Tracker-CLI.git
cd Health-Tracker-CLI
```

### Step 2 — Compile

**On Windows (Command Prompt):**
```cmd
mkdir out
javac -d out src\Main.java src\models\Log.java src\models\User.java src\models\WaterLog.java src\models\ExerciseLog.java src\models\SleepLog.java src\models\MealLog.java src\interfaces\Trackable.java src\services\FileService.java src\services\LogService.java src\utils\DateUtils.java
```

**On Mac / Linux (Terminal):**
```bash
mkdir out
find src -name "*.java" | xargs javac -d out
```

### Step 3 — Run

**On Windows:**
```cmd
cd out
java Main
```

**On Mac / Linux:**
```bash
cd out
java Main
```

> **Note:** Always run `java Main` from inside the `out/` folder. The `data/` folder will be created automatically there to store your health logs.

---

##  Usage Walkthrough

```
  First Launch — New User Setup
  ─────────────────────────────
  Enter your name: Deep
  → New user detected. Setting up profile...
  Age: 21
  Current weight (kg): 72
  Target weight (kg): 68
  Daily water goal (L): 3.0
  Daily sleep goal (hrs): 8.0
  ✅ Profile created!

  Main Menu
  ─────────
  [1]  Log Water Intake
  [2]  Log Exercise
  [3]  Log Sleep
  [4]  Log Meal
  [5]  View Daily Summary
  [6]  View Full History
  [7]  View Overall Stats
  [8]   Update Profile
  [9]  Switch User
  [0]  Exit
```

---

##  Data Storage Format

All data is stored in plain text CSV files in the `data/` folder.

**User file** (`data/user_deep.txt`):
```
Deep,21,72.0,68.0,3.0,8.0
```

**Log file** (`data/logs_deep.txt`):
```
WATER,2025-06-10,2.5,3.0
EXERCISE,2025-06-10,Running,30,250
SLEEP,2025-06-10,7.5,8.0,11:00 PM,06:30 AM
MEAL,2025-06-10,Breakfast,Oats and banana,350
```

---

##  Possible Future Enhancements

(i) BMI calculator based on current weight and height\
(ii) Weekly PDF/HTML report export\
(iii) Calorie goal tracking\
(iv) JavaFX GUI version\
(v) Reminders using scheduled threads

---

##  Author

Deep Jaiswal
Roll No: 24BAI10750
Course: Programming In Java
Institution: VIT Bhopal University

---
