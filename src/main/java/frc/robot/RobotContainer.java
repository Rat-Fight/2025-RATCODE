// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.*;

import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.swerve.SwerveRequest;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Direction;
import frc.robot.Constants.DriverConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.IntakeSubsystem;

public class RobotContainer {
    public static IntakeSubsystem INTAKE_SUBSYSTEM;
    public static SparkMax INTAKE_MOTOR = new SparkMax(Constants.IntakeConstants.INTAKE_ID, MotorType.kBrushless); // Create Brushless Spark Max for intake.

    public static ClimberSubsystem CLIMBER_SUBSYSTEM;
    public static SparkMax CLIMBER_MOTOR = new SparkMax(Constants.ClimberConstants.CLIMBER_ID, MotorType.kBrushless); // Create Brushless Spark Max for intake.

    // Replace with CommandPS4Controller or CommandJoystick if needed
    public static CommandXboxController OPERATER_CONTROLLER =
        new CommandXboxController(OperatorConstants.OPERATER_CONTROLLER_PORT);

    public static CommandXboxController DRIVER_CONTROLLER =
        new CommandXboxController(DriverConstants.DRIVER_CONTROLLER_PORT);
    
    private double MaxSpeed = TunerConstants.kSpeedAt12Volts.in(MetersPerSecond); // kSpeedAt12Volts desired top speed
    private double MaxAngularRate = RotationsPerSecond.of(0.75).in(RadiansPerSecond); // 3/4 of a rotation per second max angular velocity

    /* Setting up bindings for necessary control of the swerve drive platform */
    public final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
            .withDeadband(MaxSpeed * 0.1).withRotationalDeadband(MaxAngularRate * 0.1) // Add a 10% deadband
            .withDriveRequestType(DriveRequestType.OpenLoopVoltage); // Use open-loop control for drive motors
    private final SwerveRequest.SwerveDriveBrake brake = new SwerveRequest.SwerveDriveBrake();
    private final SwerveRequest.PointWheelsAt point = new SwerveRequest.PointWheelsAt();

    private final Telemetry logger = new Telemetry(MaxSpeed);


    public final CommandSwerveDrivetrain drivetrain = TunerConstants.createDrivetrain();

    public RobotContainer() {
        CLIMBER_SUBSYSTEM = new ClimberSubsystem();
        INTAKE_SUBSYSTEM = new IntakeSubsystem();

        configureBindings();
    }

    private void configureBindings() {
        // Note that X is defined as forward according to WPILib convention,
        // and Y is defined as to the left according to WPILib convention.
        drivetrain.setDefaultCommand(
            // Drivetrain will execute this command periodically
            drivetrain.applyRequest(() -> 
                drive.withVelocityX(-DRIVER_CONTROLLER.getLeftY() * MaxSpeed) // Drive forward with negative Y (forward)
                    .withVelocityY(-DRIVER_CONTROLLER.getLeftX() * MaxSpeed) // Drive left with negative X (left)
                    .withRotationalRate(-DRIVER_CONTROLLER.getRightX() * MaxAngularRate) // Drive counterclockwise with negative X (left)
            )
        );

        DRIVER_CONTROLLER.a().whileTrue(drivetrain.applyRequest(() -> brake));
        DRIVER_CONTROLLER.b().whileTrue(drivetrain.applyRequest(() ->
            point.withModuleDirection(new Rotation2d(-DRIVER_CONTROLLER.getLeftY(), -DRIVER_CONTROLLER.getLeftX()))
        ));

        // Run SysId routines when holding back/start and X/Y.
        // Note that each routine should be run exactly once in a single log.
        DRIVER_CONTROLLER.back().and(DRIVER_CONTROLLER.y()).whileTrue(drivetrain.sysIdDynamic(Direction.kForward));
        DRIVER_CONTROLLER.back().and(DRIVER_CONTROLLER.x()).whileTrue(drivetrain.sysIdDynamic(Direction.kReverse));
        DRIVER_CONTROLLER.start().and(DRIVER_CONTROLLER.y()).whileTrue(drivetrain.sysIdQuasistatic(Direction.kForward));
        DRIVER_CONTROLLER.start().and(DRIVER_CONTROLLER.x()).whileTrue(drivetrain.sysIdQuasistatic(Direction.kReverse));

        // reset the field-centric heading on left bumper press
        DRIVER_CONTROLLER.leftBumper().onTrue(drivetrain.runOnce(() -> drivetrain.seedFieldCentric()));

        drivetrain.registerTelemetry(logger::telemeterize);

        //OPERATER_CONTROLLER.axisGreaterThan(5, 0.9).onTrue(INTAKE_SUBSYSTEM.forward());
        //OPERATER_CONTROLLER.axisGreaterThan(2, 0.9).onTrue(INTAKE_SUBSYSTEM.backward());
        //OPERATER_CONTROLLER.button(6).onTrue(INTAKE_SUBSYSTEM.stop());
        OPERATER_CONTROLLER.rightTrigger().onTrue(INTAKE_SUBSYSTEM.forward());
        OPERATER_CONTROLLER.leftTrigger().onTrue(INTAKE_SUBSYSTEM.backward());
        OPERATER_CONTROLLER.rightBumper().onTrue(INTAKE_SUBSYSTEM.stop());
    }

    public Command getAutonomousCommand() {
        //return new AutoCommand();
        return null;
    }
}
