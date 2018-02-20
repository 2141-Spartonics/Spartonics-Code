package org.usfirst.frc.team2141.robot.subsystems;

import org.usfirst.frc.team2141.robot.RobotMap;
import org.usfirst.frc.team2141.robot.commands.Chassis_DriveWithJoystick;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Chassis extends Subsystem {
	
	// Motor Objects
	TalonSRX leftMasterMotor;
	TalonSRX leftSlaveMotorAlpha;
	TalonSRX leftSlaveMotorBeta;
	TalonSRX rightMasterMotor;
	TalonSRX rightSlaveMotorAlpha;
	TalonSRX rightSlaveMotorBeta;

	int currentProfile;

	public static final int kPIDProfile = 0;
	public static final int kIgnoreProfile = 1;
	public static final int kTimeoutMs = 10;
				
	public Chassis() {
		leftMasterMotor = new TalonSRX(RobotMap.LEFT_MASTER_MOTOR);
		leftSlaveMotorAlpha = new TalonSRX(RobotMap.LEFT_SLAVE_MOTOR_ALPHA);
		leftSlaveMotorBeta = new TalonSRX(RobotMap.LEFT_SLAVE_MOTOR_BETA);
		rightMasterMotor = new TalonSRX(RobotMap.RIGHT_MASTER_MOTOR);
		rightSlaveMotorAlpha = new TalonSRX(RobotMap.RIGHT_SLAVE_MOTOR_ALPHA);
		rightSlaveMotorBeta = new TalonSRX(RobotMap.RIGHT_SLAVE_MOTOR_BETA);
		
		
		//Setup Master Motor
		leftMasterMotor.setNeutralMode(NeutralMode.Brake);
		leftMasterMotor.setSensorPhase(true);
		leftMasterMotor.setInverted(false);
		leftMasterMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, kPIDProfile, kTimeoutMs);
		leftMasterMotor.configAllowableClosedloopError(kPIDProfile, 10, kTimeoutMs);
		leftMasterMotor.configClosedloopRamp(RobotMap.CHASSIS_RAMP_RATE, kTimeoutMs);
		
		rightMasterMotor.setNeutralMode(NeutralMode.Brake);
		rightMasterMotor.setSensorPhase(true);
		rightMasterMotor.setInverted(false);
		rightMasterMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, kPIDProfile, kTimeoutMs);
		rightMasterMotor.configAllowableClosedloopError(kPIDProfile, 10, kTimeoutMs);
		rightMasterMotor.configClosedloopRamp(RobotMap.CHASSIS_RAMP_RATE, kTimeoutMs);
		
        leftSlaveMotorAlpha.setNeutralMode(NeutralMode.Brake);
		leftSlaveMotorAlpha.follow(leftMasterMotor);
		leftSlaveMotorAlpha.setInverted(false);
		
		rightSlaveMotorAlpha.setNeutralMode(NeutralMode.Brake);
		rightSlaveMotorAlpha.follow(rightMasterMotor);
		rightSlaveMotorAlpha.setInverted(false);
		
		leftSlaveMotorBeta.setNeutralMode(NeutralMode.Brake);
		leftSlaveMotorBeta.follow(leftMasterMotor);
		leftSlaveMotorBeta.setInverted(false);
		
		rightSlaveMotorBeta.setNeutralMode(NeutralMode.Brake);
		rightSlaveMotorBeta.follow(rightMasterMotor);
		rightSlaveMotorBeta.setInverted(false);
		//int absoluteLeftPosition = leftMasterMotor.getSelectedSensorPosition(kTimeoutMs) & 0xFFF;
		///leftMasterMotor.setSelectedSensorPosition(absoluteLeftPosition, kPIDLoopIdx, kTimeoutMs);
		
		//int absoluteRightPosition = rightMasterMotor.getSelectedSensorPosition(kTimeoutMs) & 0xFFF;
		//rightMasterMotor.setSelectedSensorPosition(absoluteRightPosition, kPIDLoopIdx, kTimeoutMs);
        
        //Set motor limits for master motor
		leftMasterMotor.configNominalOutputForward(0, kTimeoutMs);
		leftMasterMotor.configNominalOutputReverse(0, kTimeoutMs);
		leftMasterMotor.configPeakOutputForward(1, kTimeoutMs);
		leftMasterMotor.configPeakOutputReverse(-1, kTimeoutMs);
		
		rightMasterMotor.configNominalOutputForward(0, kTimeoutMs);
		rightMasterMotor.configNominalOutputReverse(0, kTimeoutMs);
		rightMasterMotor.configPeakOutputForward(1, kTimeoutMs);
		rightMasterMotor.configPeakOutputReverse(-1, kTimeoutMs);
        
        //Configure & Enable Closed PID Loop
		leftMasterMotor.configAllowableClosedloopError(0, 25, kTimeoutMs); 
		leftMasterMotor.config_kP(kPIDProfile, RobotMap.LEFT_CHASSIS_P, kTimeoutMs);
		leftMasterMotor.config_kI(kPIDProfile, RobotMap.LEFT_CHASSIS_I, kTimeoutMs);
		leftMasterMotor.config_kD(kPIDProfile, RobotMap.LEFT_CHASSIS_D, kTimeoutMs);
        leftMasterMotor.config_kF(kPIDProfile, RobotMap.LEFT_CHASSIS_F, kTimeoutMs);
        leftMasterMotor.selectProfileSlot(0, 0);
        
        rightMasterMotor.configAllowableClosedloopError(0, 25, kTimeoutMs); 
        rightMasterMotor.config_kP(kPIDProfile, RobotMap.RIGHT_CHASSIS_P, kTimeoutMs);
        rightMasterMotor.config_kI(kPIDProfile, RobotMap.RIGHT_CHASSIS_I, kTimeoutMs);
        rightMasterMotor.config_kD(kPIDProfile, RobotMap.RIGHT_CHASSIS_D, kTimeoutMs);
        rightMasterMotor.config_kF(kPIDProfile, RobotMap.RIGHT_CHASSIS_F, kTimeoutMs);
        rightMasterMotor.selectProfileSlot(0, 0);
        
        //Configure Ignore Loop
		leftMasterMotor.config_kP(kIgnoreProfile, 0, kTimeoutMs);
		leftMasterMotor.config_kI(kIgnoreProfile, 0, kTimeoutMs);
		leftMasterMotor.config_kD(kIgnoreProfile, 0, kTimeoutMs);	
		leftMasterMotor.config_kF(kIgnoreProfile, 0, kTimeoutMs);
      
		rightMasterMotor.config_kP(kIgnoreProfile, 0, kTimeoutMs);
		rightMasterMotor.config_kI(kIgnoreProfile, 0, kTimeoutMs);
		rightMasterMotor.config_kD(kIgnoreProfile, 0, kTimeoutMs);
		rightMasterMotor.config_kF(kIgnoreProfile, 0, kTimeoutMs);
	
				
	}

	
	public void publishToSmartDashboard() {
        SmartDashboard.putNumber("PID Profile", currentProfile);
		SmartDashboard.putNumber("Left Encoder Distance", getLeftMotorPosistion());
		SmartDashboard.putNumber("Left Motor Voltage", leftMasterMotor.getMotorOutputVoltage());
		SmartDashboard.putNumber("Left Motor Percent", leftMasterMotor.getMotorOutputPercent());
		SmartDashboard.putNumber("Left Motor Error", leftMasterMotor.getClosedLoopError(0));
		SmartDashboard.putNumber("Left Encoder Velocity", leftMasterMotor.getSelectedSensorVelocity(0));
		SmartDashboard.putNumber("Left Encoder Velocity Setpoint", getLeftMotorVelocitySetpoint());
		
		SmartDashboard.putNumber("Right Encoder Distance", getRightMotorPosistion());
		SmartDashboard.putNumber("Right Motor Voltage", rightMasterMotor.getMotorOutputVoltage());
		SmartDashboard.putNumber("Right Motor Percent", rightMasterMotor.getMotorOutputPercent());
		SmartDashboard.putNumber("Right Motor Error", rightMasterMotor.getClosedLoopError(0));
		SmartDashboard.putNumber("Right Encoder Velocity", rightMasterMotor.getSelectedSensorVelocity(0));
		SmartDashboard.putNumber("Right Encoder Velocity Setpoint", getRightMotorPosistion());


	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new Chassis_DriveWithJoystick());
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
		this.setRightMotorSpeed(rightMotorSpeed);
	}
	
	public void setLeftMotorVelocity(double velocity) {
		this.leftMasterMotor.set(ControlMode.Velocity, velocity * 5000 * 5840 / 3600);
	}

	public void setRightMotorVelocity(double velocity) {
		this.rightMasterMotor.set(ControlMode.Velocity, velocity * 5000 *  5840  / 3600);		
	}
	
	public double getLeftMotorVelocitySetpoint() {
		return this.leftMasterMotor.getClosedLoopTarget(0);
	}
	
	public double getRightMotorVelocitySetpoint() {
		return rightMasterMotor.getClosedLoopTarget(0);
	}
	
	public void setLeftMotorSpeed(double speed) {
		this.leftMasterMotor.set(ControlMode.PercentOutput, speed);	
	}

	public void setRightMotorSpeed(double speed) {
		this.rightMasterMotor.set(ControlMode.PercentOutput, speed);
	}
	public void setLeftMotorPosistion(double posistion) {
		this.leftMasterMotor.set(ControlMode.Position, posistion);
	}
	
	public void setRightMotorPosistion(double posistion) {
		this.rightMasterMotor.set(ControlMode.Position, posistion);
	}
	
	public double getLeftMotorPosistion() {
		return leftMasterMotor.getSelectedSensorPosition(kPIDProfile) ;
	}
	
	public double getRightMotorPosistion() {
		return rightMasterMotor.getSelectedSensorPosition(kPIDProfile) ;
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
		return ((Math.abs(rightMasterMotor.getSelectedSensorPosition(0))) + Math.abs(leftMasterMotor.getSelectedSensorPosition(0))) / 2.0;
	}
	
	public void enablePID() {
		leftMasterMotor.selectProfileSlot(kPIDProfile, 0);
		rightMasterMotor.selectProfileSlot(kPIDProfile, 0);
	}
	
	public void disablePID() {
		leftMasterMotor.selectProfileSlot(kIgnoreProfile, 0);
		rightMasterMotor.selectProfileSlot(kIgnoreProfile, 0);
	}
	
	public void resetEncoders() {
		leftMasterMotor.setSelectedSensorPosition(0, kPIDProfile, kTimeoutMs);
		rightMasterMotor.setSelectedSensorPosition(0, kPIDProfile, kTimeoutMs);
	}


	}