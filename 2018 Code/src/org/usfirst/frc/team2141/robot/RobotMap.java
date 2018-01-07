package org.usfirst.frc.team2141.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	//Motor channel numbers
		public static final int LEFT_MASTER_MOTOR = 0;
		public static final int LEFT_SLAVE_MOTOR_A = 2;
		public static final int LEFT_SLAVE_MOTOR_B = 3;
		public static final int RIGHT_MASTER_MOTOR = 4;
		public static final int RIGHT_SLAVE_MOTOR_A = 5; 
		public static final int RIGHT_SLAVE_MOTOR_B = 6;
	
	//Solenoid channel numbers
		public static final int LEFT_SHIFTER_SOLENOID_CHANNEL_B = 0;
		public static final int LEFT_SHIFTER_SOLENOID_CHANNEL_A = 1;
		public static final int RIGHT_SHIFTER_SOLENOID_CHANNEL_A = 2;
		public static final int RIGHT_SHIFTER_SOLENOID_CHANNEL_B = 3;
		
	//Set armMechanism PWN posistions
		public static final int ARM_MOTOR = 10;
		
		//Drive Controller
		public static final int DRIVE_STICK_NUMBER = 0;
		public static final int AUXILLIARY_STICK_NUMBER = 1;
//		public static final int SHOOTER_CONTROL_BUTTON = 4;
}

