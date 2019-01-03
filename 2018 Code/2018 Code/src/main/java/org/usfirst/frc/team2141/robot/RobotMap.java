package org.usfirst.frc.team2141.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	// CAN Bus Values
	public static final int LEFT_MASTER_MOTOR = 8;
	public static final int LEFT_SLAVE_MOTOR_ALPHA = 9;
	public static final int LEFT_SLAVE_MOTOR_BETA = 10;
	public static final int RIGHT_MASTER_MOTOR = 3;
	public static final int RIGHT_SLAVE_MOTOR_ALPHA = 7;
	public static final int RIGHT_SLAVE_MOTOR_BETA = 5;
	public static final int ELEVATOR_CLIMB_MOTOR_ALPHA = 1;
	public static final int ELEVATOR_CLIMB_MOTOR_BETA = 2;
	public static final int ELEVATOR_CLIMB_MOTOR_CHARLIE = 6;

	// Chassis Primitives
	public static final Double LEFT_CHASSIS_P = 5.0;
	public static final Double LEFT_CHASSIS_I = 0.0;
	public static final Double LEFT_CHASSIS_D = 50.0;
	public static final Double LEFT_CHASSIS_F = 1.0/1080.0*1023.0;

	public static final Double RIGHT_CHASSIS_P = 5.0;
	public static final Double RIGHT_CHASSIS_I = 0.0;
	public static final Double RIGHT_CHASSIS_D = 50.0;
	public static final Double RIGHT_CHASSIS_F = 1.0/1080.0*1023.0;
	public static final Double CHASSIS_RAMP_RATE = 0.25;

	// PCM Values
	public static final int INTAKE_SOLENOID_CHANNEL_A = 4;
	public static final int INTAKE_SOLENOID_CHANNEL_B = 5;
	public static final int GEARBOX_SOLENOID_CHANNEL_A = 2;
	public static final int GEARBOX_SOLENOID_CHANNEL_B = 3;
	public static final int EXTRA_SOLENOID_CHANNEL_A = 6;
	public static final int EXTRA_SOLENOID_CHANNEL_B = 7;

	// Analog Inputs
	public static final int STORED_PRESSURE_SENSOR = 1;

	// Digital Inputs
	public static final int ELEVATOR_BOTTOM_LIMIT_SWITCH = 0;
	public static final int ELEVATOR_UPPER_LIMIT_SWITCH = 1;

	// PWN Outputs
	public static final int COMPRESSOR_SPIKE = 0;

	// Control Values
	public static final int DRIVE_STICK_NUMBER = 0;
	public static final int AUXILLIARY_STICK_NUMBER = 1;

	// public static final int SHOOTER_CONTROL_BUTTON = 4;

}
