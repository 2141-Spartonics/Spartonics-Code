package org.usfirst.frc.team2141.robot.subsystems;

import org.usfirst.frc.team2141.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Chassis extends Subsystem {

	CANTalon leftMotorA;
	CANTalon leftMotorB;
	CANTalon leftMotorC;
	CANTalon rightMotorA;
	CANTalon rightMotorB;
	CANTalon rightMotorC;
	RobotDrive drive;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public Chassis() {
		leftMotorA = new CANTalon(RobotMap.LEFT_MOTOR_A);
		leftMotorB = new CANTalon(RobotMap.LEFT_MOTOR_B);
		leftMotorC = new CANTalon(RobotMap.LEFT_MOTOR_C);
		rightMotorA = new CANTalon(RobotMap.RIGHT_MOTOR_A);
		rightMotorB = new CANTalon(RobotMap.RIGHT_MOTOR_B);
		rightMotorC = new CANTalon(RobotMap.RIGHT_MOTOR_C);
		
		drive = new RobotDrive(leftMotorA, rightMotorA);
		this.leftMotorB.changeControlMode(CANTalon.TalonControlMode.Follower);
		this.leftMotorB.set(RobotMap.LEFT_MOTOR_A);
		this.leftMotorC.changeControlMode(CANTalon.TalonControlMode.Follower);
		this.leftMotorC.set(RobotMap.LEFT_MOTOR_A);
		this.rightMotorB.changeControlMode(CANTalon.TalonControlMode.Follower);
		this.rightMotorB.set(RobotMap.RIGHT_MOTOR_A);
		this.rightMotorC.changeControlMode(CANTalon.TalonControlMode.Follower);
		this.rightMotorC.set(RobotMap.RIGHT_MOTOR_A);

		this.drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
		this.drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
		this.drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
		this.drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);

	}
	/**
	 * Sets the leftMotorA to speed
	 * @param speed ~speed is speed motor is set~
	 */
	public void setLeftMotors(double speed) {
		this.leftMotorA.set(speed); 

	}
	/**
	 * Sets the rightMotorA to Speed
	 * @param speed ~speed is speed motor is set~
	 */
	public void setRightMotors(double speed) {
		this.rightMotorA.set(speed);
	}
	/**
	 * Turns the Robot for autonomous
	 * @param speed ~speed is speed motor is set~
	 */
	public void turn(double speed){
		this.leftMotorA.set(speed);
		this.rightMotorA.set(-speed);
	}
	/**
	 * Method for driving with a Joystick
	 * @param stick ~stick is joystick for driving~
	 */
	public void arcadeDrive(Joystick stick){
		this.drive.arcadeDrive(stick);
		
	}
}
