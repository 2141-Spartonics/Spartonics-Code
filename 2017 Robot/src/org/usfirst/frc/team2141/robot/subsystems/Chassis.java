package org.usfirst.frc.team2141.robot.subsystems;

import org.usfirst.frc.team2141.robot.RobotMap;
import org.usfirst.frc.team2141.robot.commands.DriveWithJoystick;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
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
	RobotDrive driveTrain;
	DoubleSolenoid leftShifter;
	DoubleSolenoid rightShifter;
	Encoder leftEncoder;
	Encoder rightEncoder;
	

	public Chassis() {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    	leftMotorA = new CANTalon(RobotMap.LEFT_MOTOR_A);
     	leftMotorB = new CANTalon(RobotMap.LEFT_MOTOR_B);
     	leftMotorC = new CANTalon(RobotMap.LEFT_MOTOR_C);
     	rightMotorA = new CANTalon(RobotMap.RIGHT_MOTOR_A);
     	rightMotorB = new CANTalon(RobotMap.RIGHT_MOTOR_B);
     	rightMotorC = new CANTalon(RobotMap.RIGHT_MOTOR_C);
     	
     	leftEncoder = new Encoder(RobotMap.LEFT_ENCODER_CHANNEL_A, RobotMap.LEFT_ENCODER_CHANNEL_B, false);
     	rightEncoder = new Encoder(RobotMap.RIGHT_ENCODER_CHANNEL_A, RobotMap.RIGHT_ENCODER_CHANNEL_B, true);

     	leftShifter = new DoubleSolenoid(RobotMap.LEFT_SHIFTER_CHANNEL_A, RobotMap.LEFT_SHIFTER_CHANNEL_B);
     	rightShifter = new DoubleSolenoid(RobotMap.RIGHT_SHIFTER_CHANNEL_A, RobotMap.RIGHT_SHIFTER_CHANNEL_B);
     	
     	this.leftMotorB.changeControlMode(CANTalon.TalonControlMode.Follower);
     	this.leftMotorB.set(RobotMap.LEFT_MOTOR_A);
     	this.leftMotorC.changeControlMode(CANTalon.TalonControlMode.Follower);
     	this.leftMotorC.set(RobotMap.LEFT_MOTOR_A);
     	this.rightMotorB.changeControlMode(CANTalon.TalonControlMode.Follower);
     	this.rightMotorB.set(RobotMap.RIGHT_MOTOR_A);
     	this.rightMotorC.changeControlMode(CANTalon.TalonControlMode.Follower);
     	this.rightMotorC.set(RobotMap.RIGHT_MOTOR_A);
     	
     	driveTrain = new RobotDrive(leftMotorA, rightMotorA);
     	this.driveTrain.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
     	this.driveTrain.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
     	
     	
     }

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new DriveWithJoystick());

	}
	
	public void setLeftToHigh(){
     	this.leftShifter.set(DoubleSolenoid.Value.kForward);
	}
	
	public void setLeftToLow(){
     	this.leftShifter.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void setRightToHigh(){
     	this.rightShifter.set(DoubleSolenoid.Value.kForward);
	}
	
	public void setRightToLow(){
     	this.rightShifter.set(DoubleSolenoid.Value.kReverse);
	}

	
	public void setBothToHigh(){
		this.setLeftToHigh();
		this.setRightToHigh();
	}
	
	public void setBothToLow(){
		this.setLeftToLow();
		this.setRightToLow();
	}
	
	public void setLeftMotors(double speed) {
		this.leftMotorA.set(speed);
	}

	public void setRightMotors(double speed) {
		this.rightMotorA.set(speed);
	}
	
	public void setAllMotors(double speed){
		this.setRightMotors(speed);
		this.setLeftMotors(speed);
		}

	public void arcadeDrive(double moveValue, double rotateValue) {
		this.driveTrain.arcadeDrive(moveValue, rotateValue, true);
	}
	
	public double getLeftDistance(){
		return this.leftEncoder.getDistance();
	}
	
	public double getRightDistance(){
		return this.rightEncoder.getDistance();
	}
	
	public void resetLeftEncoder(){
		this.leftEncoder.reset();
	}

	public void resetRightEncoder(){
		this.rightEncoder.reset();
	}
	
	public void resetBothEncoders(){
		this.resetRightEncoder();
		this.resetLeftEncoder();
	}
	
	public double getLeftRate(){
		return this.leftEncoder.getRate();
	}
	
	public double getRightRate(){
		return this.rightEncoder.getRate(); 
	}
	
	
}
