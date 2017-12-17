package org.usfirst.frc.team2141.robot.subsystems;

import org.usfirst.frc.team2141.robot.RobotMap;
import org.usfirst.frc.team2141.robot.commands.DriveWithJoystick;
import org.usfirst.frc.team2141.robot.commands.chassis.JoyStickDriving;
import org.usfirst.frc.team2141.robot.subsystems.Chassis.ShifterValue;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Chassis extends Subsystem {
	
	// Motor Objects
	private CANTalon leftMasterMotor;
	private CANTalon leftSlaveMotorA;
	private CANTalon leftSlaveMotorB;
	private CANTalon rightMasterMotor;
	private CANTalon rightSlaveMotorA;
	private CANTalon rightSlaveMotorB;
	
	// Shifter Objects
	private DoubleSolenoid leftShifterSolenoid;
	private DoubleSolenoid rightShifterSolenoid;
	
	public enum ShifterValue {
		HIGH, LOW
	}
	
	private ShifterValue leftGearing = ShifterValue.LOW;
	private ShifterValue rightGearing = ShifterValue.LOW;
	
	//DistanceFollower leftDriveFollower;
	//DistanceFollower rightDriveFollower;
	
	private boolean flipped;
		
	public Chassis() {
		
		leftShifterSolenoid = new DoubleSolenoid(RobotMap.LEFT_SHIFTER_SOLENOID_CHANNEL_A,
				RobotMap.LEFT_SHIFTER_SOLENOID_CHANNEL_B);
		rightShifterSolenoid = new DoubleSolenoid(RobotMap.RIGHT_SHIFTER_SOLENOID_CHANNEL_A,
				RobotMap.RIGHT_SHIFTER_SOLENOID_CHANNEL_B);
		
		leftMasterMotor = new CANTalon(RobotMap.LEFT_MASTER_MOTOR);
		leftSlaveMotorA = new CANTalon(RobotMap.LEFT_SLAVE_MOTOR_A);
		leftSlaveMotorB = new CANTalon(RobotMap.LEFT_SLAVE_MOTOR_B);
		rightMasterMotor = new CANTalon(RobotMap.RIGHT_MASTER_MOTOR);
		rightSlaveMotorA = new CANTalon(RobotMap.RIGHT_SLAVE_MOTOR_A);
		rightSlaveMotorB = new CANTalon(RobotMap.RIGHT_SLAVE_MOTOR_B);
		
		//Master motor setup
		this.rightMasterMotor.enableBrakeMode(true);
		this.rightMasterMotor.setInverted(false);
		//this.rightMasterMotor.reverseSensor(false);
		this.rightMasterMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		//this.rightMasterMotor.setCurrentLimit(RobotMap.CURRENT_LIMIT);
		//this.rightMasterMotor.EnableCurrentLimit(currentEnable);
		
		this.leftMasterMotor.enableBrakeMode(true);
		this.leftMasterMotor.setInverted(true);
		//this.leftMasterMotor.reverseSensor(false);
		this.leftMasterMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		//this.leftMasterMotor.setCurrentLimit(RobotMap.CURRENT_LIMIT);
		//this.leftMasterMotor.EnableCurrentLimit(currentEnable);
		
		
		//Slave motor setup
		this.leftSlaveMotorA.enableBrakeMode(true);
		this.leftSlaveMotorA.changeControlMode(CANTalon.TalonControlMode.Follower);
		//this.leftSlaveMotorA.setVoltageRampRate(RobotMap.DRIVE_RAMP_RATE);
		this.leftSlaveMotorA.set(RobotMap.LEFT_MASTER_MOTOR);
		
		this.leftSlaveMotorB.enableBrakeMode(true);
		this.leftSlaveMotorB.changeControlMode(CANTalon.TalonControlMode.Follower);
		//this.leftSlaveMotorB.setVoltageRampRate(RobotMap.DRIVE_RAMP_RATE);
		this.leftSlaveMotorB.set(RobotMap.LEFT_MASTER_MOTOR);
		
		this.rightSlaveMotorA.enableBrakeMode(true);
		this.rightSlaveMotorA.changeControlMode(CANTalon.TalonControlMode.Follower);
		//this.rightSlaveMotorA.setVoltageRampRate(RobotMap.DRIVE_RAMP_RATE);
		this.rightSlaveMotorA.set(RobotMap.RIGHT_MASTER_MOTOR);
		
		this.rightSlaveMotorB.enableBrakeMode(true);
		this.rightSlaveMotorB.changeControlMode(CANTalon.TalonControlMode.Follower);
		//this.rightSlaveMotorB.setVoltageRampRate(RobotMap.DRIVE_RAMP_RATE);
		this.rightSlaveMotorB.set(RobotMap.RIGHT_MASTER_MOTOR);
	}
	
	public void publishToSmartDashboard() {
		//SmartDashboard.putNumber("Left Encoder Speed", this.getLeftEncoderVelocity());
		//SmartDashboard.putNumber("Right Encoder Speed", this.getRightEncoderVelocity());
		//SmartDashboard.putNumber("Right Encoder Distance", this.getRightEncoderCount());
		//SmartDashboard.putNumber("Left Encoder Distance", this.getLeftEncoderCount());
		SmartDashboard.putBoolean("Flipped", this.flipped);
		SmartDashboard.putNumber("Left Setpoint", this.leftMasterMotor.getSetpoint());
		SmartDashboard.putNumber("Right Setpoint", this.rightMasterMotor.getSetpoint());
		SmartDashboard.putNumber("Left Velocity Error", this.leftMasterMotor.getClosedLoopError());
		SmartDashboard.putNumber("Right Velocity Error", this.rightMasterMotor.getClosedLoopError());
		//SmartDashboard.putBoolean("Left in Low", this.leftInLow());
		//SmartDashboard.putBoolean("Right in Low", this.rightInLow());
		SmartDashboard.putNumber("Right throttle", this.rightMasterMotor.getOutputVoltage());
		SmartDashboard.putNumber("Left throttle", this.leftMasterMotor.getOutputVoltage());
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
	}
	
	
	
	public CANTalon getLeftMotor(){
		return this.leftMasterMotor;
	}
	
	public CANTalon getRightMotor(){
		return this.rightMasterMotor;
	}
}
