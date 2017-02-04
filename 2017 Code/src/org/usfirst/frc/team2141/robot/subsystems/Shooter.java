package org.usfirst.frc.team2141.robot.subsystems;

import org.usfirst.frc.team2141.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {

	private CANTalon shooterMotor;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public Shooter() {
		shooterMotor = new CANTalon(RobotMap.SHOOTER_MOTOR);
		shooterMotor.changeControlMode(TalonControlMode.Voltage);
		
	}

	public void setShooterMotor(double speed) {
		shooterMotor.changeControlMode(TalonControlMode.Voltage);
		this.shooterMotor.set(speed * 12);
	}

	public double getVelocity() {
		return this.shooterMotor.getSpeed();
	}

}
