// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.commands.IntakeCommand;

public class Intake extends SubsystemBase {
    IntakeCommand currentCommand; // Current running command

    public Intake() {}

    public void init() {
        SmartDashboard.putData("Intake Subsystem", this); // Put data on dashboard.
    }

    // Gets running command / run a new command
    public Command intakeCommand(boolean forward) {
       if (currentCommand == null || currentCommand.isFinished()) { // If command is stopped
           currentCommand = new IntakeCommand(forward); // Create new command
       } else if (currentCommand.FORWARD != forward) { // If command is wrong direction
           currentCommand.cancel(); // Stop current command
           currentCommand = new IntakeCommand(forward); // Create new command
       }
       return currentCommand; // Return command
    }
   
    // Move the motors forward. Overriding currents motors state
    public Command forward() {
        return this.run(() -> setDefaultCommand(intakeCommand(true)));
    }
    
    // Move the motors backward. Overriding currents motors state
    public Command backward() {
        return this.run(() -> setDefaultCommand(intakeCommand(true)));
    }
    
    // Stop the motors
    public Command stop() {
        return this.run(() -> currentCommand.cancel());
    }

    @Override
    public void periodic() {}

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addDoubleProperty("Intake Motor Speed", RobotContainer.INTAKE_MOTOR::get, null);
    }
}