package org.usfirst.frc.team2141.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	//OI numbers
	public static final int DRIVE_STICK = 0;
	public static final int SHIFT_UP_BUTTON = 3;
	public static final int SHIFT_DOWN_BUTTON = 2;
	
	//Motor channel numbers
	public static final int LEFT_MASTER_MOTOR = 1;
	public static final int LEFT_SLAVE_MOTOR_A = 2;
	public static final int LEFT_SLAVE_MOTOR_B = 3;
	public static final int RIGHT_MASTER_MOTOR = 4;
	public static final int RIGHT_SLAVE_MOTOR_A = 5; 
	public static final int RIGHT_SLAVE_MOTOR_B = 6;
	public static final int WINCH_MOTOR = 7;
	public static final int SHOOTER_MOTOR = 8;
	public static final int INTAKE_MOTOR = 9;
<<<<<<< Upstream, based on master

	//Solenoid channel numbers
	public static final int SHIFTER_SOLENOID_CHANNEL_B = 0;
	public static final int SHIFTER_SOLENOID_CHANNEL_A = 1;
	public static final int WINCH_STOPPER_CHANNEL_A = 2;
	public static final int WINCH_STOPPER_CHANNEL_B = 3;

	//Miscellaneous
=======
	public static final int HOPPER_MOTOR = 10;
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
	public static final int WINCH_STOPPER_CHANNEL_A = 0;
	public static final int WINCH_STOPPER_CHANNEL_B = 0;
	
>>>>>>> 3d7535d Added hopper code to Shooter subsystem and updated Robotmap
	public static final int WINCH_PDP_CHANNEL = 0;
<<<<<<< Upstream, based on master
		
=======
	
	
	
	
	
	
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
>>>>>>> 3d7535d Added hopper code to Shooter subsystem and updated Robotmap
}
