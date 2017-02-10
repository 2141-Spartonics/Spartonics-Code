package org.usfirst.frc.team2141.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	//OI numbers
	
	//Drive Controller
	public static final int DRIVE_STICK_NUMBER = 0;
	public static final int INTAKE_CONTROL_BUTTON = 2;
	public static final int WINCH_CONTROL_BUTTON = 9;
	public static final int SHOOTER_CONTROL_BUTTON = 4;
	public static final int REVERSE_DRIVE_BUTTON = 10;
	public static final int SHIFT_DOWN_BUTTON = 1;
	public static final int LEFT_HAND_BRAKE = 5;
	public static final int RIGHT_HAND_BRAKE = 6;
	public static final int FORCED_AUTONMOUS =3;
	public static final int AUTO_STICK = 2;
	
	public static final int AUTO_TURN_LEFT = 4;
	public static final int AUTO_TURN_RIGHT = 5;
	
	
	//Manual Board
	public static final int MANUAL_BOARD_STICK_NUMBER = 1;
	public static final int FORCE_SHOOTER_OFF = 3;
	public static final int FORCE_INTAKE_OFF = 2;

	//PID Values
	public static final double SHOOTER_SPEED_P = 0.0;
	public static final double SHOOTER_SPEED_I = 0.0;
	public static final double SHOOTER_SPEED_D = 0.0;
	public static final double SHOOTER_SPEED_F = 0.0;
	public static final double LEFT_MOTOR_VELOCITY_P = 0.0;
	public static final double LEFT_MOTOR_VELOCITY_I = 0.0;
	public static final double LEFT_MOTOR_VELOCITY_D = 0.0;
	public static final double LEFT_MOTOR_VELOCITY_F = 0.0;
	public static final double RIGHT_MOTOR_VELOCITY_P = 0.0;
	public static final double RIGHT_MOTOR_VELOCITY_I = 0.0;
	public static final double RIGHT_MOTOR_VELOCITY_D = 0.0;
	public static final double RIGHT_MOTOR_VELOCITY_F = 0.0;
	

	//Motor channel numbers
	public static final int LEFT_MASTER_MOTOR = 3;
	public static final int LEFT_SLAVE_MOTOR_A = 2;
	public static final int LEFT_SLAVE_MOTOR_B = 1;
	public static final int RIGHT_MASTER_MOTOR = 8;
	public static final int RIGHT_SLAVE_MOTOR_A = 5; 
	public static final int RIGHT_SLAVE_MOTOR_B = 6;
	public static final int WINCH_MOTOR = 7;
	public static final int SHOOTER_MOTOR = 4;
	public static final int INTAKE_MOTOR = 9;
	public static final int FEEDER_MOTOR = 10;

	//Solenoid channel numbers
	public static final int LEFT_SHIFTER_SOLENOID_CHANNEL_B = 0;
	public static final int LEFT_SHIFTER_SOLENOID_CHANNEL_A = 1;
	public static final int WINCH_STOPPER_CHANNEL_A = 2;
	public static final int WINCH_STOPPER_CHANNEL_B = 3;
	public static final int RIGHT_SHIFTER_SOLENOID_CHANNEL_A = 4;
	public static final int RIGHT_SHIFTER_SOLENOID_CHANNEL_B = 5;

	//Miscellaneous
	public static final int WINCH_PDP_CHANNEL = 0;
	

}