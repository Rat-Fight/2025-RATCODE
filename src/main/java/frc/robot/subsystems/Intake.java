// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.Constants.IntakeConstants;
import frc.robot.commands.GenericMotorCommand;
import frc.robot.commands.IntakeCommand;

public class Intake extends SubsystemBase {
    public Intake() {
        SmartDashboard.putData("Intake Subsystem", this); // Put data on dashboard.
    }
   
    // Move the motors forward. Overriding currents motors state
    public Command forward() {
        return new IntakeCommand(true);
    }
    
    // Move the motors backward. Overriding currents motors state
    public Command backward() {
        return new IntakeCommand(false);
    }
    
    // Stop the motors
    public Command stop() {
        return this.runOnce(() -> {CommandScheduler.getInstance().requiring(this).cancel();});
    }

    @Override
    public void periodic() {}

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addDoubleProperty("Intake Speed", RobotContainer.INTAKE_MOTOR::get, null);
        builder.addDoubleProperty("Time Left", () -> {return Math.max(0, IntakeConstants.MAX_INTAKE_TIME - IntakeCommand.TIMER.get());}, null);
    }
}