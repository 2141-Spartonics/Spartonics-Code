package org.usfirst.frc.team2141.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	//Motor channel numbers
		public static final int LEFT_MASTER_MOTOR = 3;
		public static final int LEFT_SLAVE_MOTOR = 1;
		public static final int RIGHT_MASTER_MOTOR = 8;
		public static final int RIGHT_SLAVE_MOTOR = 5; 
		public static final int ELEVATOR_CLIMB_MOTOR = 0;
		public static final int ELEVATOR_INTAKE_MOTOR = 0;
		
		//Drive Controller
		public static final int DRIVE_STICK_NUMBER = 0;
		public static final int AUXILLIARY_STICK_NUMBER = 1;
//		public static final int SHOOTER_CONTROL_BUTTON = 4;
		
		//Pnuematics Values
		public static final int INTAKE_SOLENOID_CHANNEL_A = 6;
		public static final int INTAKE_SOLENOID_CHANNEL_B = 7;
}

