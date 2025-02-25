// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static IntakeSubsystem INTAKE_SUBSYSTEM;
  public static SparkMax INTAKE_MOTOR = new SparkMax(Constants.IntakeConstants.INTAKE_ID, MotorType.kBrushless); // Create Brushless Spark Max for intake.

  public static ClimberSubsystem CLIMBER_SUBSYSTEM;
  public static SparkMax CLIMBER_MOTOR = new SparkMax(Constants.ClimberConstants.CLIMBER_ID, MotorType.kBrushless); // Create Brushless Spark Max for intake.

  // Replace with CommandPS4Controller or CommandJoystick if needed
  public static CommandXboxController OPERATER_CONTROLLER =
      new CommandXboxController(OperatorConstants.OPERATER_CONTROLLER_PORT);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    CLIMBER_SUBSYSTEM = new ClimberSubsystem();
    INTAKE_SUBSYSTEM = new IntakeSubsystem();
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    //DRIVER_CONTROLLER.rightTrigger().onTrue(INTAKE_SUBSYSTEM.forward());
    //DRIVER_CONTROLLER.leftTrigger().onTrue(INTAKE_SUBSYSTEM.backward());
    //DRIVER_CONTROLLER.rightBumper().onTrue(INTAKE_SUBSYSTEM.stop());
    OPERATER_CONTROLLER.axisGreaterThan(5, 0.9).onTrue(INTAKE_SUBSYSTEM.forward());
    OPERATER_CONTROLLER.axisGreaterThan(2, 0.9).onTrue(INTAKE_SUBSYSTEM.backward());
    OPERATER_CONTROLLER.button(6).onTrue(INTAKE_SUBSYSTEM.stop());
    
    OPERATER_CONTROLLER.getLeftY();
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null;
  }
}
