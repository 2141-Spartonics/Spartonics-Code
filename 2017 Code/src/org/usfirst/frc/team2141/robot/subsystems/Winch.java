package org.usfirst.frc.team2141.robot.subsystems;

import org.usfirst.frc.team2141.robot.Robot;
import org.usfirst.frc.team2141.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Winch extends Subsystem {
	private CANTalon winchMotor;
	private DoubleSolenoid winchBrake;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(null);
	}

	public Winch() {
		winchMotor = new CANTalon(RobotMap.WINCH_MOTOR);
		winchBrake = new DoubleSolenoid(RobotMap.WINCH_STOPPER_CHANNEL_A, RobotMap.WINCH_STOPPER_CHANNEL_B);

		this.winchMotor.enableBrakeMode(true);
		this.winchMotor.changeControlMode(TalonControlMode.Voltage);
		this.putBrakeOff();
	}
	
	public void publishToSmartDashboard(){
		SmartDashboard.putNumber("Winch Current", this.getCurrent());
	}

	/**
	 * Sets the the winchMotor to speed
	 * 
	 * @param speed the speed the motor is set to
	 */
	public void setWinchSpeed(double speed) {
    	if(speed > 1.0 || speed < -1.0){
    		this.winchMotor.set(speed);
    	}
		
		this.winchMotor.set(speed * 12);
	}

	/**
	 * Turns off the brake for the winch
	 */
	public void putBrakeOff() {
		this.winchBrake.set(DoubleSolenoid.Value.kForward);
	}

	/**
	 * Stops the winch from moving
	 */
	public void putBrakeOn() {
		this.winchBrake.set(DoubleSolenoid.Value.kReverse);
	}

	/**
	 * Reads how much current is going into the winchMotor
	 * 
	 * @return the amount of current used for the winchMotor in amps
	 */
	public double getCurrent() {
		return Robot.PDP.getCurrent(RobotMap.WINCH_PDP_CHANNEL);
	}

}
