// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class IntakeCommand extends Command {
    // Create variables
    final SparkMax MOTOR;
    final Timer TIMER;
    public final boolean FORWARD;

    public IntakeCommand() {
        this(true);
    }

    /** Creates a new Crushing. */
    public IntakeCommand(boolean forward) {
        addRequirements(RobotContainer.INTAKE_SUBSYSTEM); // Require moter subsystem
        // Initalize variables
        this.MOTOR = RobotContainer.INTAKE_MOTOR;
        this.FORWARD = forward;
        // Initalize Timer
        TIMER = new Timer();
        TIMER.reset();
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        // Start timer
        TIMER.reset();
        TIMER.start();
        System.out.println("Moving the motor " + (FORWARD ? "forward": "backward"));
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

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return (TIMER.get() > Constants.IntakeConstants.MAX_INTAKE_TIME); // Stop after CRUSHING_TIME
    }
}
