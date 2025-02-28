// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int OPERATER_CONTROLLER_PORT = 0;
  }

  public static class DriverConstants {
    public static final int DRIVER_CONTROLLER_PORT = 1;
  }

  public static class IntakeConstants {
    public static final double MAX_INTAKE_TIME = 10;
    public static final double SPEED_DIVIDER = 5;
    public static final int    INTAKE_ID = 9999;
  }
  
  public static class ClimberConstants {
    public static final double SPEED_DIVIDER = 5;
    public static final int    CLIMBER_ID = 9998;
  }

  public static class ArmConstants {
    public static final double SPEED_DIVIDER = 5;
    public static final int    ARM_ID = 9997;
  }
}
