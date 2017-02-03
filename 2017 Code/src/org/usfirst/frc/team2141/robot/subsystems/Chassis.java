package org.usfirst.frc.team2141.robot.subsystems;

import org.usfirst.frc.team2141.robot.RobotMap;
import org.usfirst.frc.team2141.robot.commands.JoyStickDriving;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Chassis extends Subsystem {

	private CANTalon leftMasterMotor;
	private CANTalon leftSlaveMotorA;
	private CANTalon leftSlaveMotorB;
	private CANTalon rightMasterMotor;
	private CANTalon rightSlaveMotorA;
	private CANTalon rightSlaveMotorB;

	// Pnuematic Shifter Code
	private DoubleSolenoid shifterSolenoid;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public Chassis() {
		shifterSolenoid = new DoubleSolenoid(RobotMap.SHIFTER_SOLENOID_CHANNEL_A, RobotMap.SHIFTER_SOLENOID_CHANNEL_B);

		leftMasterMotor = new CANTalon(RobotMap.LEFT_MASTER_MOTOR);
		leftSlaveMotorA = new CANTalon(RobotMap.LEFT_SLAVE_MOTOR_A);
		leftSlaveMotorB = new CANTalon(RobotMap.LEFT_SLAVE_MOTOR_B);
		rightMasterMotor = new CANTalon(RobotMap.RIGHT_MASTER_MOTOR);
		rightSlaveMotorA = new CANTalon(RobotMap.RIGHT_SLAVE_MOTOR_A);
		rightSlaveMotorB = new CANTalon(RobotMap.RIGHT_SLAVE_MOTOR_B);

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
	 * Taring the drive train encoders.
	 */
	public void zeroEncoders() {
		this.leftMasterMotor.setPosition(0);
		this.rightMasterMotor.setPosition(0);
	}

	/**
	 *  Gets left Motors position.
	 *  @return 
	 */
	public double getLeftEncoderCount() {
		return this.leftMasterMotor.getPosition();
	}

	/**
	 * Gets the left Motors velocity.
	 * @return
	 */
	public double getLeftEncoderVelocity() {
		return this.leftMasterMotor.getSpeed();
	}

	/**
	 * Gets right Motors position
	 * @return
	 */
	public double getRightEncoderCount() {
		return this.rightMasterMotor.getPosition();
	}

	/**
	 *  Gets right Motors velocity. :) Hi Bernie
	 * @return
	 */
	public double getRightEncoderVelocity() {
		return this.rightMasterMotor.getSpeed();
	}

	// Shifter methods

	/**
	 * Sets gear to a higher speed using pnuematics.
	 * @return 
	 */
	public void setToHighSpeed() {
		this.shifterSolenoid.set(DoubleSolenoid.Value.kForward);
	}

	/**
	 * Sets gear to a lower speed using pnuematics.
	 * @return
	 */
	public void setToLowSpeed() {
		this.shifterSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
	
    /**
     * Closes the soleniod.
     * @return
     */
	public void closeSolenoid() {
		this.shifterSolenoid.set(DoubleSolenoid.Value.kOff);
	}

	// Basic driving methods
	/**
	 * Sets the left motors to speed.
	 * @param speed
	 */
	public void setLeftMotors(double speed) {
		this.leftMasterMotor.set(speed);
	}
	
	//Sets the right Motors speed.
	public void setRightMotors(double speed) {
		this.rightMasterMotor.set(speed);
	}

	// Teleoperated driving methods

		//Alex's weird code(Ray didn't put that Bernie did but I'm going to keep it :))
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

		this.setLeftMotors(leftMotorSpeed);
		this.setRightMotors(rightMotorSpeed);
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
