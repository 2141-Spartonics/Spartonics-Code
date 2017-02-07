package org.usfirst.frc.team2141.robot.subsystems;

import org.usfirst.frc.team2141.robot.RobotMap;
import org.usfirst.frc.team2141.robot.commands.JoyStickDriving;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Chassis extends Subsystem {

	// Motor Objects
	private CANTalon leftMasterMotor;
	private CANTalon leftSlaveMotorA;
	private CANTalon leftSlaveMotorB;
	private CANTalon rightMasterMotor;
	private CANTalon rightSlaveMotorA;
	private CANTalon rightSlaveMotorB;
	private CANTalon leftMotorVelocity;
	private CANTalon rightMotorVelocity;

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
	private Object leftMotor;

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

		this.leftSlaveMotorA.changeControlMode(CANTalon.TalonControlMode.Follower);
		this.leftSlaveMotorA.set(RobotMap.LEFT_MASTER_MOTOR);
		this.leftSlaveMotorB.changeControlMode(CANTalon.TalonControlMode.Follower);
		this.leftSlaveMotorB.set(RobotMap.LEFT_MASTER_MOTOR);
		this.rightSlaveMotorA.changeControlMode(CANTalon.TalonControlMode.Follower);
		this.rightSlaveMotorA.set(RobotMap.RIGHT_MASTER_MOTOR);
		this.rightSlaveMotorB.changeControlMode(CANTalon.TalonControlMode.Follower);
		this.rightSlaveMotorB.set(RobotMap.RIGHT_MASTER_MOTOR);

		this.rightMasterMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		this.rightMasterMotor.configEncoderCodesPerRev(256);
		this.leftMasterMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		this.leftMasterMotor.configEncoderCodesPerRev(256);

		this.leftMasterMotor.setProfile(0);
		this.leftMasterMotor.setP(RobotMap.LEFT_MOTOR_VELOCITY_P);
		this.leftMasterMotor.setI(RobotMap.LEFT_MOTOR_VELOCITY_I);
		this.leftMasterMotor.setD(RobotMap.LEFT_MOTOR_VELOCITY_D);
		this.leftMasterMotor.setF(RobotMap.LEFT_MOTOR_VELOCITY_F);
		this.rightMasterMotor.setProfile(0);
		this.rightMasterMotor.setP(RobotMap.RIGHT_MOTOR_VELOCITY_P);
		this.rightMasterMotor.setI(RobotMap.RIGHT_MOTOR_VELOCITY_I);
		this.rightMasterMotor.setD(RobotMap.RIGHT_MOTOR_VELOCITY_D);
		this.rightMasterMotor.setF(RobotMap.RIGHT_MOTOR_VELOCITY_F);

	}

	public void publishToSmartDashboard() {
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
		return this.leftMasterMotor.getSpeed();
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
		return this.rightMasterMotor.getSpeed();
	}

	// Shifter methods

	public void setLeftToHigh() {
		this.leftShifterSolenoid.set(DoubleSolenoid.Value.kForward);
		this.leftGearing = ShifterValue.HIGH;
	}

	public void setLeftToLow() {
		this.leftShifterSolenoid.set(DoubleSolenoid.Value.kReverse);
		this.leftGearing = ShifterValue.LOW;
	}

	public void setRightToHigh() {
		this.rightShifterSolenoid.set(DoubleSolenoid.Value.kForward);
		this.rightGearing = ShifterValue.HIGH;
	}

	public void setRightToLow() {
		this.rightShifterSolenoid.set(DoubleSolenoid.Value.kReverse);
		this.rightGearing = ShifterValue.LOW;
	}

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

	/**
	 * Sets gear to a higher speed using pnuematics.
	 */
	public void setBothToHighSpeed() {
		this.setLeftToHigh();
		this.setRightToHigh();
	}

	/**
	 * Sets gear to a lower speed using pnuematics.
	 */
	public void setBothToLowSpeed() {
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
		this.leftMasterMotor.set(speed);
	}

	public void setRightMotorVelocity(double speed) {
		this.rightMasterMotor.changeControlMode(TalonControlMode.Speed);
		this.rightMasterMotor.set(speed);
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
	}

	// Alex's weird code(Ray didn't put that Bernie did but I'm going to keep it
	// :))
	public void arcadeDrive(double moveValue, double rotateValue, boolean squaredInputs) {

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

		if (this.flipped) {
			this.setLeftMotorVoltage(-rightMotorSpeed);
			this.setRightMotorVoltage(-leftMotorSpeed);
		} else {
			this.setLeftMotorVoltage(leftMotorSpeed);
			this.setRightMotorVoltage(rightMotorSpeed);
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
