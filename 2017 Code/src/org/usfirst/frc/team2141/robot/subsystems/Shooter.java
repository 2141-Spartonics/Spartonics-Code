package org.usfirst.frc.team2141.robot.subsystems;

import org.usfirst.frc.team2141.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.CANTalon.VelocityMeasurementPeriod;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shooter extends Subsystem {

	private CANTalon shooterMotor;
	private CANTalon feederMotor;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(null);
	}

	public Shooter() {
		shooterMotor = new CANTalon(RobotMap.SHOOTER_MOTOR);
		feederMotor = new CANTalon(RobotMap.FEEDER_MOTOR);

		this.shooterMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		this.shooterMotor.configEncoderCodesPerRev(12);
		this.shooterMotor.configNominalOutputVoltage(0.0, 0.0);
		this.shooterMotor.configPeakOutputVoltage(12, 0.0);
		this.shooterMotor.enableBrakeMode(false);

		this.shooterMotor.setPID(
				RobotMap.SHOOTER_SPEED_P,
				RobotMap.SHOOTER_SPEED_I,
				RobotMap.SHOOTER_SPEED_D,
				RobotMap.SHOOTER_SPEED_F,
				100,
				36.0,
				0);
		this.shooterMotor.changeControlMode(TalonControlMode.Speed);
		this.shooterMotor.SetVelocityMeasurementPeriod(VelocityMeasurementPeriod.Period_50Ms);
		this.shooterMotor.SetVelocityMeasurementWindow(64);
		
		this.feederMotor.changeControlMode(TalonControlMode.Voltage);
	}

	public void publishToSmartDashboard(){
		SmartDashboard.putNumber("Shooter error", this.getVelocityError());
		SmartDashboard.putNumber("Shooter speed", this.getVelocity());
	}
	
	/**
	 * Sets the feederMotor to speed
	 * 
	 * @param speed
	 *            Speed is the speed chosen for the motor to be set to
	 */
	public void setFeederSpeed(double speed) {
    	if(speed > 1.0 || speed < -1.0){
    		this.feederMotor.set(speed);
    	}else{
    		this.feederMotor.set(speed * 12);
    	}
	}
	/**
	 * Sets the shooterMotors voltage 
	 * @param voltage
	 */
	public void setShooterMotorVoltage(double voltage) {
		this.shooterMotor.changeControlMode(TalonControlMode.Voltage);
		this.shooterMotor.set(voltage * 12);
	}
	/**
	 * Gets the shooter Motors speed
	 * @return The shooter Motors speed
	 */
	public double getVelocity() {
		return this.shooterMotor.getSpeed();
	}
	/**
	 * Sets the target speed 
	 * @return targetSpeed
	 */
	public void setShooterMotorVelocity(double targetSpeed) {
		this.shooterMotor.changeControlMode(TalonControlMode.Speed);
		this.shooterMotor.set(targetSpeed);
	}
	/**
	 * Tells how far away you are from your set air level
	 * @return distance from set air level
	 */
	public int getVelocityError() {
		return this.shooterMotor.getClosedLoopError();
	}

}
