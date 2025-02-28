package frc.robot.subsystems;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.commands.GenericMotorCommand;

public class ArmSubsystem extends SubsystemBase{
      /** Creates a new ExampleSubsystem. */
  public ArmSubsystem() {
    SmartDashboard.putData("Arm Subsystem", this); // Put data on dashboard.
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command extend() {
    Command cmd = new GenericMotorCommand(true, RobotContainer.ARM_MOTOR); // Create a generic motor command to move a motor clockwise
    cmd.addRequirements(this);
    return cmd;
  }
  
  public Command retract() {
    Command cmd = new GenericMotorCommand(false, RobotContainer.ARM_MOTOR); // Create a generic motor command to move a motor clockwise
    cmd.addRequirements(this);
    return cmd;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  
  @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addDoubleProperty("Arm Speed", RobotContainer.ARM_MOTOR::get, null);
    }
}
