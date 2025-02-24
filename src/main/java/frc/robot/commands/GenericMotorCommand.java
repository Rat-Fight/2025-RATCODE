// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class GenericMotorCommand extends Command {
    // Create variables
    public final boolean FORWARD;
    public final SparkMax MOTOR;

    public GenericMotorCommand(SparkMax motor) {
        this(true, motor);
    }

    /** Creates a new Crushing. */
    public GenericMotorCommand(boolean forward, SparkMax motor) {
        addRequirements(RobotContainer.INTAKE_SUBSYSTEM); // Require moter subsystem
        // Initalize variables
        this.FORWARD = forward;
        this.MOTOR = motor;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        // Logging
        System.out.println("Moving motor " + MOTOR.getDeviceId() + (FORWARD ? " forward": " backward"));
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        MOTOR.set((FORWARD ? 1: -1)/Constants.IntakeConstants.SPEED_DIVIDER); // Set the motor to full speed forward if forward is true or backward if forward is false
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        MOTOR.set(0); // Stop the motor on command end
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
