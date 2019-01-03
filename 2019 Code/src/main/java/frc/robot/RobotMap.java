/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

  // PORTS
  public static final Integer PRIMARY_STICK_PORT = 0;
  public static final Integer AUXILIARY_STICK_PORT = 1;

  // CAN BUS
  public static final Integer LEFT_MASTER_SPARK = 0;
  public static final Integer LEFT_SLAVE_SPARK = 1;
  public static final Integer RIGHT_MASTER_SPARK = 2;
  public static final Integer RIGHT_SLAVE_SPARK = 3;

  // Chassis PID Constants
  public static final Integer CHASSIS_KP_INTEGER = 0;
  public static final Integer CHASSIS_KD_INTEGER = 0;
  public static final Integer CHASSIS_KF_INTEGER = 1;


  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}
