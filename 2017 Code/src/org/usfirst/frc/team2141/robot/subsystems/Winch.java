package org.usfirst.frc.team2141.robot.subsystems;

import org.usfirst.frc.team2141.robot.Robot;
import org.usfirst.frc.team2141.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Winch extends Subsystem {
	private CANTalon winchMotor;
	private DoubleSolenoid winchStopper;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public Winch() {
		winchMotor = new CANTalon(RobotMap.WINCH_MOTOR);
		winchStopper = new DoubleSolenoid(RobotMap.WINCH_STOPPER_CHANNEL_A, RobotMap.WINCH_STOPPER_CHANNEL_B);
	}

	/**
	 * sets the the winchMotor to speed
	 * 
	 * @param speed
	 *            Speed is the speed the motor is set to
	 */

	public void setWinchSpeed(double speed) {
		this.winchMotor.set(speed);
	}

	/**
	 * Turns off the break for the winch
	 */
	public void putBrakeOff() {
		this.winchStopper.set(DoubleSolenoid.Value.kForward);

	}

	/**
	 * Stops the winch from moving
	 */
	public void putBrakeOn() {
		this.winchStopper.set(DoubleSolenoid.Value.kReverse);
	}

	/**
	 * Reads how much power is going into the winchMotor
	 * 
	 * @return returns the amount of power used for the winchMotor
	 */
	public double getpower() {
		return Robot.PDP.getCurrent(RobotMap.WINCH_PDP_CHANNEL);
	}

}
