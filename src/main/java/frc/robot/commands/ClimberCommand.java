// package frc.robot.commands;

// import frc.robot.RobotContainer;

// public class ClimberCommand extends GenericMotorCommand {
//     public ClimberCommand(boolean forward) {
//         super(forward, RobotContainer.CLIMBER_MOTOR);
//         addRequirements(RobotContainer.CLIMBER_SUBSYSTEM); // Require moter subsystem
//     }
// }

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ClimberSubsystem;

public class ClimberCommand extends Command {
    /** Creates a new Crushing. */
    public ClimberCommand(ClimberSubsystem parent) {
        addRequirements(parent);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {}

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        RobotContainer.CLIMBER_MOTOR.set(RobotContainer.OPERATER_CONTROLLER.getLeftY());
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        RobotContainer.CLIMBER_MOTOR.set(0); // Stop the motor on command end
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}