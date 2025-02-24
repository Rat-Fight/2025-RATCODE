package frc.robot.commands;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class IntakeCommand extends GenericMotorCommand {
    public final static Timer TIMER = new Timer();
    
    public IntakeCommand(boolean forward) {
        // Initalize motor command
        super(forward, RobotContainer.INTAKE_MOTOR);
        // setup timer
        TIMER.reset();
        // Put data on dashboard
        SmartDashboard.putData((FORWARD ? "Forward": "Backward") + " Intake Command", this);
    }
    
    public void initialize() {
        // Let generic motor initalize
        super.initialize();
        // Start timer
        TIMER.reset();
        TIMER.start();
    }
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return (TIMER.get() > Constants.IntakeConstants.MAX_INTAKE_TIME); // Stop after CRUSHING_TIME
    }
}
