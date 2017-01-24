package org.usfirst.frc.team2141.robot.subsystems;

import org.usfirst.frc.team2141.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
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

	public void setLeftMotors(double speed) {//sets leftMotorA to speed for autonomous
		this.leftMotorA.set(speed); 

	}

	public void setRightMotors(double speed) {//set the rightMotorA to speed for autonomous
		this.rightMotorA.set(speed);
	}
	public void turn(double speed){ //turns for autonomous 
		this.leftMotorA.set(speed);
		this.rightMotorA.set(-speed);
	}
	public void arcadeDrive(Joystick stick){//uses a Joystick called arcadeDrive
		this.drive.arcadeDrive(stick);
		
	}
}
