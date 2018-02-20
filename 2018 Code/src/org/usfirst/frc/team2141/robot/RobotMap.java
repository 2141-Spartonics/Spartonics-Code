package org.usfirst.frc.team2141.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	//Motor channel numbers
		public static final int LEFT_MASTER_MOTOR = 8;
		public static final int LEFT_SLAVE_MOTOR_ALPHA = 9;
		public static final int LEFT_SLAVE_MOTOR_BETA = 10;
		public static final int RIGHT_MASTER_MOTOR = 3;
		public static final int RIGHT_SLAVE_MOTOR_ALPHA = 7; 
		public static final int RIGHT_SLAVE_MOTOR_BETA = 5; 
		
		public static final int ELEVATOR_CLIMB_MOTOR_ALPHA = 1;
		public static final int ELEVATOR_CLIMB_MOTOR_BETA = 2;
		public static final int ELEVATOR_CLIMB_MOTOR_CHARLIE = 6;
		public static final int ELEVATOR_INTAKE_MOTOR = 0;
		
		public static final int COMPRESSOR_RELAY = 0;
		
		//PID Variables
		public static final Double LEFT_CHASSIS_P = 2.22391;
		public static final Double LEFT_CHASSIS_I = 0.0;
		public static final Double LEFT_CHASSIS_D = 22.2391;
		public static final Double LEFT_CHASSIS_F = 0.10518;
		
		public static final Double RIGHT_CHASSIS_P = 1.67704;
		public static final Double RIGHT_CHASSIS_I = 0.0;
		public static final Double RIGHT_CHASSIS_D = 16.7704;
		public static final Double RIGHT_CHASSIS_F = 0.106256;

		
		//Drive Controller
		public static final int DRIVE_STICK_NUMBER = 0;
		public static final int AUXILLIARY_STICK_NUMBER = 1;
//		public static final int SHOOTER_CONTROL_BUTTON = 4;
		
		//Pnuematics Values
		public static final int INTAKE_SOLENOID_CHANNEL_A = 2;
		public static final int INTAKE_SOLENOID_CHANNEL_B = 3;
		public static final int GEARBOX_SHIFTER_A = 4;
		public static final int GEARBOX_SHIFTER_B = 5;
}

