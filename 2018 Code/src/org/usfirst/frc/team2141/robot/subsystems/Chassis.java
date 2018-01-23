package org.usfirst.frc.team2141.robot.subsystems;

import org.usfirst.frc.team2141.robot.RobotMap;
import org.usfirst.frc.team2141.robot.commands.DriveWithJoystick;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Chassis extends Subsystem {
	
	// Motor Objects
	TalonSRX leftMasterMotor;
	TalonSRX leftSlaveMotor;
	TalonSRX rightMasterMotor;
	TalonSRX rightSlaveMotor;

	Encoder absoluteEncoder;

	double pidP = 0.1;
	double pidI = 0.0;
	double pidD = 0.0;
	double pidF = 0.0;
	int currentProfile;
		
	public static final int kSlotIdx = 0;
	public static final int kPIDLoopIdx = 0;
	public static final int kTimeoutMs = 10;
		
	//private boolean flipped;
		
	public Chassis() {
		leftMasterMotor = new TalonSRX(RobotMap.LEFT_MASTER_MOTOR);
		leftSlaveMotor = new TalonSRX(RobotMap.LEFT_SLAVE_MOTOR);
		rightMasterMotor = new TalonSRX(RobotMap.RIGHT_MASTER_MOTOR);
		rightSlaveMotor = new TalonSRX(RobotMap.RIGHT_SLAVE_MOTOR);
		
		absoluteEncoder = new Encoder(0, 1);
		
		//Setup Master Motor
		this.leftMasterMotor.setNeutralMode(NeutralMode.Brake);
		this.leftMasterMotor.setSensorPhase(true);
		this.leftMasterMotor.setInverted(true);
		this.leftMasterMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, kTimeoutMs);
		this.leftMasterMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, kSlotIdx, kTimeoutMs);
		
		this.rightMasterMotor.setNeutralMode(NeutralMode.Brake);
		this.rightMasterMotor.setSensorPhase(true);
		this.rightMasterMotor.setInverted(false);
		this.rightMasterMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, kTimeoutMs);
		this.rightMasterMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, kSlotIdx, kTimeoutMs);
		
		//Tell the motor its starting position
		int absoluteLeftPosition = leftMasterMotor.getSelectedSensorPosition(kTimeoutMs) & 0xFFF;
		leftMasterMotor.setSelectedSensorPosition(absoluteLeftPosition, kPIDLoopIdx, kTimeoutMs);
		
		int absoluteRightPosition = rightMasterMotor.getSelectedSensorPosition(kTimeoutMs) & 0xFFF;
		rightMasterMotor.setSelectedSensorPosition(absoluteRightPosition, kPIDLoopIdx, kTimeoutMs);
        
        //Set motor limits for master motor
		leftMasterMotor.configNominalOutputForward(0, kTimeoutMs);
		leftMasterMotor.configNominalOutputReverse(0, kTimeoutMs);
		leftMasterMotor.configPeakOutputForward(1, kTimeoutMs);
		leftMasterMotor.configPeakOutputReverse(-1, kTimeoutMs);
		
		rightMasterMotor.configNominalOutputForward(0, kTimeoutMs);
		rightMasterMotor.configNominalOutputReverse(0, kTimeoutMs);
		rightMasterMotor.configPeakOutputForward(1, kTimeoutMs);
		rightMasterMotor.configPeakOutputReverse(-1, kTimeoutMs);
        
        //Configure closed pid loop
		leftMasterMotor.configAllowableClosedloopError(0, kPIDLoopIdx, kTimeoutMs); 
		leftMasterMotor.config_kP(kPIDLoopIdx, pidP, kTimeoutMs);
		leftMasterMotor.config_kI(kPIDLoopIdx, pidI, kTimeoutMs);
		leftMasterMotor.config_kD(kPIDLoopIdx, pidD, kTimeoutMs);
        leftMasterMotor.config_kF(kPIDLoopIdx, pidF, kTimeoutMs);
        
        rightMasterMotor.configAllowableClosedloopError(0, kPIDLoopIdx, kTimeoutMs); 
        rightMasterMotor.config_kP(kPIDLoopIdx, pidP, kTimeoutMs);
        rightMasterMotor.config_kI(kPIDLoopIdx, pidI, kTimeoutMs);
        rightMasterMotor.config_kD(kPIDLoopIdx, pidD, kTimeoutMs);
        rightMasterMotor.config_kF(kPIDLoopIdx, pidF, kTimeoutMs);
        
        //Ignore PID
		leftMasterMotor.configAllowableClosedloopError(0, kPIDLoopIdx, kTimeoutMs); 
		leftMasterMotor.config_kP(1, 0, kTimeoutMs);
		leftMasterMotor.config_kI(1, 0, kTimeoutMs);
		leftMasterMotor.config_kD(1, 0, kTimeoutMs);
        leftMasterMotor.config_kF(1, 0, kTimeoutMs);
        
        rightMasterMotor.configAllowableClosedloopError(0, kPIDLoopIdx, kTimeoutMs); 
        rightMasterMotor.config_kP(1, 0, kTimeoutMs);
        rightMasterMotor.config_kI(1, 0, kTimeoutMs);
        rightMasterMotor.config_kD(1, 0, kTimeoutMs);
        rightMasterMotor.config_kF(1, 0, kTimeoutMs);


		//Setup Slave Motor
        leftSlaveMotor.setNeutralMode(NeutralMode.Brake);
		leftSlaveMotor.follow(leftMasterMotor);
		leftSlaveMotor.configClosedloopRamp(0.5, 0);
		leftSlaveMotor.setInverted(true);
		
		rightSlaveMotor.setNeutralMode(NeutralMode.Brake);
		rightSlaveMotor.follow(rightMasterMotor);
		rightSlaveMotor.configClosedloopRamp(0.5, 0);
		leftSlaveMotor.setInverted(true);
				
	}

	
	public void publishToSmartDashboard() {
        SmartDashboard.putNumber("PID Profile", currentProfile);
        SmartDashboard.putNumber("Abolute Encoder", getEncoderValue());

		SmartDashboard.putNumber("Left Encoder Speed", leftMasterMotor.getSelectedSensorVelocity(0));
		SmartDashboard.putNumber("Left Encoder Distance", leftMasterMotor.getSelectedSensorPosition(0) / 1024);
		//SmartDashboard.putNumber("Velocity Error", armMotorAlpha.getClosedLoopError());
		SmartDashboard.putNumber("Left Motor Voltage", leftMasterMotor.getMotorOutputVoltage());
		SmartDashboard.putNumber("Left Motor Percent", leftMasterMotor.getMotorOutputPercent());
		SmartDashboard.putNumber("Left Motor Error", leftMasterMotor.getClosedLoopError(0));
		
		SmartDashboard.putNumber("Right Encoder Speed", rightMasterMotor.getSelectedSensorVelocity(0));
		SmartDashboard.putNumber("Right Encoder Distance", rightMasterMotor.getSelectedSensorPosition(0));
		//SmartDashboard.putNumber("Velocity Error", armMotorAlpha.getClosedLoopError());
		SmartDashboard.putNumber("Right Motor Voltage", rightMasterMotor.getMotorOutputVoltage());
		SmartDashboard.putNumber("Right Motor Percent", rightMasterMotor.getMotorOutputPercent());
		SmartDashboard.putNumber("Right Motor Error", rightMasterMotor.getClosedLoopError(0));

		SmartDashboard.putNumber("Right Slave Motor Percent", rightSlaveMotor.getMotorOutputPercent());
		SmartDashboard.putNumber("Left Slave Motor Percent", leftSlaveMotor.getMotorOutputPercent());

	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
	}
	
	public void setPIDProfile(int profile) {
        leftMasterMotor.selectProfileSlot(profile, 0);
        rightMasterMotor.selectProfileSlot(profile, 0);
        currentProfile = profile;
	}
	
	public void arcadeDrive(double moveValue, double rotateValue) {

		double leftMotorSpeed;
		double rightMotorSpeed;

		moveValue = limit(moveValue);
		rotateValue = limit(rotateValue);

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
		
		this.setLeftMotorSpeed(-leftMotorSpeed);
		this.setRightMotorSpeed(-rightMotorSpeed);
	}
	
	public void setLeftMotorVelocity(double speed) {
		this.leftMasterMotor.set(ControlMode.Velocity, speed);	
	}

	public void setRightMotorVelocity(double speed) {
		this.rightMasterMotor.set(ControlMode.Velocity, speed);	
	
	}
	
	public void setLeftMotorSpeed(double speed) {
		this.leftMasterMotor.set(ControlMode.PercentOutput, speed);	
	}

	public void setRightMotorSpeed(double speed) {
		this.rightMasterMotor.set(ControlMode.PercentOutput, speed);	
	
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
	
	
	public TalonSRX getLeftMotor(){
		return this.leftMasterMotor;
	}
	
	public TalonSRX getRightMotor(){
		return this.rightMasterMotor;
	}
	
	public double getAverageEncoderPosition(){
		return ((rightMasterMotor.getSelectedSensorPosition(0)) + leftMasterMotor.getSelectedSensorPosition(0)) / 1.0;
	}
	
	public void zeroEncoders() {
		leftMasterMotor.setSelectedSensorPosition(0, kSlotIdx, kTimeoutMs);
		rightMasterMotor.setSelectedSensorPosition(0, kSlotIdx, kTimeoutMs);
	}
	
	public double convertRPMToTicks(double RPM) {
		return RPM * 12;
	}
	
	public double convertTicksToInches(double ticks) {
		return ticks / 1024 * 4 * Math.PI;
	}
	
	public double convertInchesToTicks(double inches) {
		return inches / (4 * Math.PI) * 1024;
	}
	
	public int getEncoderValue() {
		return absoluteEncoder.getRaw();
	}
	
	}