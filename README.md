# 2025-RATCODE 🚀🐀

**9152 Rat Fight — Code for the 2025 Robot (“Rat Fight”)**

This repo has all the code we’re using for the 2025 Rat Fight robot (FRC project).  

It covers everything from driving and intake to climbing, autonomous routines, and driver controls.

---

## Table of Contents

1. Features
2. Architecture & Modules
3. Dependencies & Setup
4. Building & Deployment
5. Running the Code
6. Testing
7. Folder Structure  
8. Contributing
9. License

---

## Features

- Core subsystems: drivetrain, intake, arm, climber (planned)  
- Autonomous routines (starter versions, will grow over time)  
- Driver controls and operator interface  
- Built with WPILib (FRC robotics framework)  
- Modular setup so new features can be added without breaking everything  

---

## Architecture & Modules

Here’s a quick breakdown of what’s in here:

| Subsystem | What it does |
|-----------|--------------|
| **Drivetrain** | Handles robot motion (low + high level control) |
| **Intake** | Picks things up and manages intake logic |
| **Arm** | Controls positioning, extension, and manipulation |
| **Climber** | Endgame climbing logic |
| **Autonomous** | Preprogrammed auto routines and path planning |
| **Driver Interface** | Controller mappings, dashboard links, operator controls |
| **Helpers** | Shared constants, math utilities, sensor wrappers |


---

## Dependencies & Setup

You’ll need:

- Java JDK (whatever WPILib requires, usually Java 11)  
- WPILib + toolchain  
- Gradle (already included with the wrapper here)  
- VS Code (with WPILib plugin)  
- A robot (RoboRIO, motor controllers, sensors, etc.) for deployment  

**Setup steps:**

1. Clone the repo:
   ```bash
   git clone https://github.com/Rat-Fight/2025-RATCODE.git
   cd 2025-RATCODE
