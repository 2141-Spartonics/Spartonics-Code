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
	public static final int FEEDER_CONTROL_BUTTON = 4;
	public static final int INTAKE_CONTROL_BUTTON = 1;	

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
	public static final int FEEDER_MOTOR = 10;

	//Solenoid channel numbers
	public static final int SHIFTER_SOLENOID_CHANNEL_B = 0;
	public static final int SHIFTER_SOLENOID_CHANNEL_A = 1;
	public static final int WINCH_STOPPER_CHANNEL_A = 2;
	public static final int WINCH_STOPPER_CHANNEL_B = 3;

	//Miscellaneous
	public static final int WINCH_PDP_CHANNEL = 0;

}
