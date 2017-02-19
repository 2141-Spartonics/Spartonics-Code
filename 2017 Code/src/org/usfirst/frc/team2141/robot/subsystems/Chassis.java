package org.usfirst.frc.team2141.robot.subsystems;

import org.usfirst.frc.team2141.robot.RobotMap;
import org.usfirst.frc.team2141.robot.commands.chassis.JoyStickDriving;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.followers.DistanceFollower;


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
	private ShifterValue rightGearing = ShifterValue.HIGH;

	/**
	 * True if the controls are flipped, and the intake is the front.
	 */
	private boolean flipped;

	DistanceFollower leftDriveFollower;
	DistanceFollower rightDriveFollower;
	
	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

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

		flipped = false;

		leftDriveFollower = new DistanceFollower();
		rightDriveFollower = new DistanceFollower();
		
		//Slave motor configuration
		this.leftSlaveMotorA.enableBrakeMode(true);
		this.leftSlaveMotorA.changeControlMode(CANTalon.TalonControlMode.Follower);
		this.leftSlaveMotorA.setVoltageRampRate(RobotMap.DRIVE_RAMP_RATE);
		this.leftSlaveMotorA.set(RobotMap.LEFT_MASTER_MOTOR);
		
		this.leftSlaveMotorB.enableBrakeMode(true);
		this.leftSlaveMotorB.changeControlMode(CANTalon.TalonControlMode.Follower);
		this.leftSlaveMotorB.setVoltageRampRate(RobotMap.DRIVE_RAMP_RATE);
		this.leftSlaveMotorB.set(RobotMap.LEFT_MASTER_MOTOR);
		
		this.rightSlaveMotorA.enableBrakeMode(true);
		this.rightSlaveMotorA.changeControlMode(CANTalon.TalonControlMode.Follower);
		this.rightSlaveMotorA.setVoltageRampRate(RobotMap.DRIVE_RAMP_RATE);
		this.rightSlaveMotorA.set(RobotMap.RIGHT_MASTER_MOTOR);
		
		this.rightSlaveMotorB.enableBrakeMode(true);
		this.rightSlaveMotorB.changeControlMode(CANTalon.TalonControlMode.Follower);
		this.rightSlaveMotorB.setVoltageRampRate(RobotMap.DRIVE_RAMP_RATE);
		this.rightSlaveMotorB.set(RobotMap.RIGHT_MASTER_MOTOR);

		//Master motor setup
		this.rightMasterMotor.enableBrakeMode(true);
		this.rightMasterMotor.setInverted(false);
		this.rightMasterMotor.reverseSensor(false);
		this.rightMasterMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		
		this.leftMasterMotor.enableBrakeMode(true);
		this.leftMasterMotor.setInverted(true);
		this.leftMasterMotor.reverseSensor(true);
		this.leftMasterMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);

		//Velocity PID profile setups
		this.leftMasterMotor.setPID(
				RobotMap.DRIVE_LOW_VELOCITY_P, 
				RobotMap.DRIVE_LOW_VELOCITY_I,
				RobotMap.DRIVE_LOW_VELOCITY_D, 
				RobotMap.DRIVE_LOW_VELOCITY_F, 
				RobotMap.DRIVE_IZONE, 
				RobotMap.DRIVE_RAMP_RATE, 
				0);
		this.leftMasterMotor.setPID(
				RobotMap.DRIVE_HIGH_VELOCITY_P,
				RobotMap.DRIVE_HIGH_VELOCITY_I,
				RobotMap.DRIVE_HIGH_VELOCITY_D,
				RobotMap.DRIVE_HIGH_VELOCITY_F,
				RobotMap.DRIVE_IZONE,
				RobotMap.DRIVE_RAMP_RATE,
				1);
		this.rightMasterMotor.setPID(
				RobotMap.DRIVE_LOW_VELOCITY_P,
				RobotMap.DRIVE_LOW_VELOCITY_I,
				RobotMap.DRIVE_LOW_VELOCITY_D,
				RobotMap.DRIVE_LOW_VELOCITY_F,
				RobotMap.DRIVE_IZONE,
				RobotMap.DRIVE_RAMP_RATE,
				0);
		this.rightMasterMotor.setPID(
				RobotMap.DRIVE_HIGH_VELOCITY_P,
				RobotMap.DRIVE_HIGH_VELOCITY_I,
				RobotMap.DRIVE_HIGH_VELOCITY_D,
				RobotMap.DRIVE_HIGH_VELOCITY_F,
				RobotMap.DRIVE_IZONE,
				RobotMap.DRIVE_RAMP_RATE,
				1);
		
		//Configure the motion profile followers
		this.leftDriveFollower.configurePIDVA(
				RobotMap.PROFILE_LOW_P, 
				RobotMap.PROFILE_LOW_I, 
				RobotMap.PROFILE_LOW_D, 
				RobotMap.PROFILE_LOW_V, 
				RobotMap.PROFILE_LOW_A);
		this.rightDriveFollower.configurePIDVA(
				RobotMap.PROFILE_LOW_P, 
				RobotMap.PROFILE_LOW_I, 
				RobotMap.PROFILE_LOW_D, 
				RobotMap.PROFILE_LOW_V, 
				RobotMap.PROFILE_LOW_A);
		

	}

	public void publishToSmartDashboard() {
		SmartDashboard.putNumber("Left Encoder Speed", this.getLeftEncoderVelocity());
		SmartDashboard.putNumber("Right Encoder Speed", this.getRightEncoderVelocity());
		SmartDashboard.putNumber("Right Encoder Distance", this.getRightEncoderCount());
		SmartDashboard.putNumber("Left Encoder Distance", this.getLeftEncoderCount());
		SmartDashboard.putBoolean("Flipped", this.flipped);
		SmartDashboard.putNumber("Left Setpoint", this.getLeftMotorVelocitySetpoint());
		SmartDashboard.putNumber("Right Setpoint", this.getRightMotorVelocitySetpoint());
		SmartDashboard.putBoolean("Left in Low", this.leftInLow());
		SmartDashboard.putBoolean("Right in Low", this.rightInLow());
		SmartDashboard.putNumber("Right throttle", this.rightMasterMotor.getOutputVoltage());
		SmartDashboard.putNumber("Left throttle", this.leftMasterMotor.getOutputVoltage());

		
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new JoyStickDriving());
	}

	// Encoder methods

	/**
	 * Zeroes the drive train encoders.
	 */
	public void zeroEncoders() {
		this.leftMasterMotor.setPosition(0);
		this.rightMasterMotor.setPosition(0);
	}

	/**
	 * Gets the left wheel's position
	 * 
	 * @return the amount of ticks the left side has driven since it was zeroed
	 */
	public double getLeftEncoderCount() {
		return this.leftMasterMotor.getPosition();
	}

	/**
	 * Gets the left wheel's velocity
	 * 
	 * @return the velocity of the left wheels in encoder RPMs
	 */
	public double getLeftEncoderVelocity() {
		if(this.flipped){
			return this.rightMasterMotor.getSpeed();
		}else{
			return this.leftMasterMotor.getSpeed();
		}
	}

	/**
	 * Gets the right wheel's position
	 * 
	 * @return the amount of ticks the right side has driven since it was zeroed
	 */
	public double getRightEncoderCount() {
		return this.rightMasterMotor.getPosition();
	}

	/**
	 * Gets the right wheel's velocity
	 * 
	 * @return the velocity of the right wheels in encoder RPMs
	 */
	public double getRightEncoderVelocity() {
		if(this.flipped){
			return this.leftMasterMotor.getSpeed();
		}else{
			return this.rightMasterMotor.getSpeed();
		}
	}
	
	public double getAverageEncoderPosition(){
		return (this.getLeftEncoderCount() + this.getRightEncoderCount()) / 2;
	}
	
	public double convertTicksToInches(double ticks){
		return ticks * 3.0 * 54.0 / 30.0 * 256.0 * 4.0 * Math.PI;
	}
	
	public double convertEncoderRateToRPM(double rate){
		return rate * 3.0 * 54.0 / 30.0 * 256.0;
	}
	
	// Shifter methods

	public boolean leftInHigh() {
		return this.leftGearing == ShifterValue.HIGH;
	}

	public boolean leftInLow() {
		return this.leftGearing == ShifterValue.LOW;
	}

	public boolean rightInHigh() {
		return this.rightGearing == ShifterValue.HIGH;
	}

	public boolean rightInLow() {
		return this.rightGearing == ShifterValue.LOW;
	}

	public void setLeftToHigh() {
		this.leftShifterSolenoid.set(DoubleSolenoid.Value.kForward);
		this.leftGearing = ShifterValue.HIGH;
		this.leftMasterMotor.setProfile(1);
	}

	public void setLeftToLow() {
		this.leftShifterSolenoid.set(DoubleSolenoid.Value.kReverse);
		this.leftGearing = ShifterValue.LOW;
		this.leftMasterMotor.setProfile(0);
	}

	public void setRightToHigh() {
		this.rightShifterSolenoid.set(DoubleSolenoid.Value.kForward);
		this.rightGearing = ShifterValue.HIGH;
		this.rightMasterMotor.setProfile(1);
	}

	public void setRightToLow() {
		this.rightShifterSolenoid.set(DoubleSolenoid.Value.kReverse);
		this.rightGearing = ShifterValue.LOW;
		this.rightMasterMotor.setProfile(0);
	}
	
	/**
	 * Sets gear to a higher speed using pnuematics.
	 */
	public void setBothToHigh() {
		this.setLeftToHigh();
		this.setRightToHigh();
	}

	/**
	 * Sets gear to a lower speed using pnuematics.
	 */
	public void setBothToLow() {
		this.setLeftToLow();
		this.setRightToLow();
	}

	/**
	 * Closes the soleniod and prevents the robot from shifting.
	 */
	public void closeSolenoids() {
		this.leftShifterSolenoid.set(DoubleSolenoid.Value.kOff);
		this.rightShifterSolenoid.set(DoubleSolenoid.Value.kOff);
	}

	// Basic driving methods

	public void setLeftMotorVelocity(double speed) {
		this.leftMasterMotor.changeControlMode(TalonControlMode.Speed);
		this.leftMasterMotor.set(5330*speed*12/50*34/50*3/60*256*4/10);
	}

	public void setRightMotorVelocity(double speed) {
		this.rightMasterMotor.changeControlMode(TalonControlMode.Speed);
		this.rightMasterMotor.set(5330*speed*12/50*34/50*3/60*256*4/10);
	}

	public double getLeftMotorVelocitySetpoint(){
		if(this.flipped){
			return this.rightMasterMotor.getSetpoint();
		}else{
			return this.leftMasterMotor.getSetpoint();
		}
	}
	
	public double getRightMotorVelocitySetpoint(){
		if(this.flipped){
			return this.leftMasterMotor.getSetpoint();
		}else{
			return this.rightMasterMotor.getSetpoint();
		}
	}
	
	/**
	 * Sets the left motors to speed.
	 * 
	 * @param speed
	 */
	public void setLeftMotorVoltage(double speed) {
		this.leftMasterMotor.changeControlMode(TalonControlMode.Voltage);
		this.leftMasterMotor.set(speed * 12);
	}

	/**
	 * Sets the right motors to speed.
	 * 
	 * @param speed
	 */
	public void setRightMotorVoltage(double speed) {
		this.rightMasterMotor.changeControlMode(TalonControlMode.Voltage);
		this.rightMasterMotor.set(speed * 12);
	}

	// Teleoperated driving methods

	/**
	 * Flips the direction of the chassis
	 */
	public void flipDirection() {
		this.flipped = !this.flipped;
		this.leftMasterMotor.reverseSensor(!this.flipped);
		this.rightMasterMotor.reverseSensor(this.flipped);
		this.leftMasterMotor.setInverted(!this.flipped);
		this.rightMasterMotor.setInverted(this.flipped);
		this.zeroEncoders();
		
	}

	public void arcadeDrive(double moveValue, double rotateValue, boolean squaredInputs, boolean velocityControl) {

		double leftMotorSpeed;
		double rightMotorSpeed;

		moveValue = limit(moveValue);
		rotateValue = limit(rotateValue);

		if (squaredInputs) {
			// square the inputs (while preserving the sign) to increase fine
			// control
			// while permitting full power
			if (moveValue >= 0.0) {
				moveValue = moveValue * moveValue;
			} else {
				moveValue = -(moveValue * moveValue);
			}
			if (rotateValue >= 0.0) {
				rotateValue = rotateValue * rotateValue;
			} else {
				rotateValue = -(rotateValue * rotateValue);
			}
		}

		if (moveValue > 0.0) {
			if (rotateValue > 0.0) {
				leftMotorSpeed = moveValue - rotateValue;
				rightMotorSpeed = Math.max(moveValue, rotateValue);
			} else {
				leftMotorSpeed = Math.max(moveValue, -rotateValue);
				rightMotorSpeed = moveValue + rotateValue;
			}
		} else {
			if (rotateValue > 0.0) {
				leftMotorSpeed = -Math.max(-moveValue, rotateValue);
				rightMotorSpeed = moveValue + rotateValue;
			} else {
				leftMotorSpeed = moveValue - rotateValue;
				rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
			}
		}

		
		if(velocityControl){
			if (this.flipped) {
				this.setLeftMotorVelocity(rightMotorSpeed);
				this.setRightMotorVelocity(leftMotorSpeed);
			} else {
				this.setLeftMotorVelocity(-leftMotorSpeed);
				this.setRightMotorVelocity(-rightMotorSpeed);
			}
		}else{
			if (this.flipped) {
				this.setLeftMotorVoltage(rightMotorSpeed);
				this.setRightMotorVoltage(leftMotorSpeed);
			} else {
				this.setLeftMotorVoltage(-leftMotorSpeed);
				this.setRightMotorVoltage(-rightMotorSpeed);
			}
		}
	}

	private double limit(double val) {
		if (val > 1.0) {
			return 1.0;
		} else if (val < -1.0) {
			return -1.0;
		} else {
			return val;
		}
	}

}
