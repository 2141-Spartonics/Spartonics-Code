package org.usfirst.frc.team2141.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	//Drive Controller
	public static final int DRIVE_STICK_NUMBER = 0;
	public static final int INTAKE_CONTROL_BUTTON = 2;
	public static final int WINCH_CONTROL_BUTTON = 9;
	public static final int SHOOTER_CONTROL_BUTTON = 4;
	public static final int REVERSE_DRIVE_BUTTON = 10;
	public static final int SHIFT_DOWN_BUTTON = 1;
	public static final int WINCH_DOWN = 6;
	
	
	//Manual Board
	public static final int MANUAL_BOARD_STICK_NUMBER = 1;
	public static final int FORCE_SHOOTER_OFF = 3;
	public static final int FORCE_INTAKE_OFF = 2;

	//Motor channel numbers
	public static final int LEFT_MASTER_MOTOR = 8;
	public static final int LEFT_SLAVE_MOTOR_A = 7;
	public static final int LEFT_SLAVE_MOTOR_B = 10;
	public static final int RIGHT_MASTER_MOTOR = 3;
	public static final int RIGHT_SLAVE_MOTOR_A = 9; 
	public static final int RIGHT_SLAVE_MOTOR_B = 4;
	public static final int WINCH_MOTOR = 2;
	public static final int SHOOTER_MOTOR = 6;
	public static final int INTAKE_MOTOR = 1;
	public static final int FEEDER_MOTOR = 5;

	//Solenoid channel numbers
	public static final int LEFT_SHIFTER_SOLENOID_CHANNEL_B = 6;
	public static final int LEFT_SHIFTER_SOLENOID_CHANNEL_A = 7;
	public static final int WINCH_STOPPER_CHANNEL_A = 2;
	public static final int WINCH_STOPPER_CHANNEL_B = 3;
	public static final int RIGHT_SHIFTER_SOLENOID_CHANNEL_A = 4;
	public static final int RIGHT_SHIFTER_SOLENOID_CHANNEL_B = 5;

	//Miscellaneous
	public static final int WINCH_PDP_CHANNEL = 1;

	//PID Values
	public static final double SHOOTER_SPEED_P = 0.0;
	public static final double SHOOTER_SPEED_I = 0.0;//Done
	public static final double SHOOTER_SPEED_D = 0.0;
	public static final double SHOOTER_SPEED_F = 0.0;
	
    // 54.0/30.0*3.0*256.0 = 1382.4 counts per revolution
	public static final double DRIVE_LOW_VELOCITY_P = 0;
	public static final double DRIVE_LOW_VELOCITY_I = 0.0;//Done
	public static final double DRIVE_LOW_VELOCITY_D = 0;
	public static final double DRIVE_LOW_VELOCITY_F = 0.5;//Calculated
	public static final double DRIVE_HIGH_VELOCITY_P = 0;
	public static final double DRIVE_HIGH_VELOCITY_I = 0.0;//Done
	public static final double DRIVE_HIGH_VELOCITY_D = 0;
	public static final double DRIVE_HIGH_VELOCITY_F = 0.23;//Calculated
	
	public static final double DRIVE_RAMP_RATE = 0.0;//Done
	public static final int DRIVE_IZONE = 0;//Done
	
	//Velocity Constants
	//RPS of encoder shaft is at .9 on low = 5330*.9*12/50*34/50*3/60 = 39.14352
	//Native units per 100ms = 5330*.9*12/50*34/50*3/60*256*4/10 = 4008.296448 * .8
	public static final double SHIFTING_SPEED_THRESHOLD = 3200;//Calculated but needs to be tested to make sure
	
	//Motion profiling constants
	public static final double PROFILE_LOW_P = 0.0;
	public static final double PROFILE_LOW_I = 0.0;//Done
	public static final double PROFILE_LOW_D = 0.0;
	public static final double PROFILE_LOW_V = 0.0;
	public static final double PROFILE_LOW_A = 0.0;//Done	
	
}