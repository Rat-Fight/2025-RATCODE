// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.commands.ClimberCommand;

public class ClimberSubsystem extends SubsystemBase {
    public ClimberSubsystem() {
        SmartDashboard.putData("Climber Subsystem", this); // Put data on dashboard.
        setDefaultCommand(new ClimberCommand(this));
    }

    @Override
    public void periodic() {}

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addDoubleProperty("Climber Speed", RobotContainer.CLIMBER_MOTOR::get, null);
    }
}