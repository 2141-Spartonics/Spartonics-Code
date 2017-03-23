package org.usfirst.frc.team2141.robot.commands.chassis;

import org.usfirst.frc.team2141.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraight extends Command {

	double encoderCount;
	double maxSpeed;
	
	public DriveStraight(double distanceInInches, double drivingSpeed) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.chassis);		
		encoderCount = distanceInInches * 440.0;
		maxSpeed = drivingSpeed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.chassis.zeroEncoders();
		Robot.chassis.setBothToLow();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double error = encoderCount - Robot.chassis.getAverageEncoderPosition();
		Robot.chassis.setLeftMotorVelocity(error/Math.abs(error)*Math.min(maxSpeed, Math.abs(.00005*error)));
		Robot.chassis.setRightMotorVelocity(error/Math.abs(error)*Math.min(maxSpeed, Math.abs(.00005*error)));
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (encoderCount > 0) {
			return Robot.chassis.getAverageEncoderPosition() > encoderCount;
		} else {
			return Robot.chassis.getAverageEncoderPosition() < encoderCount;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.chassis.setLeftMotorVelocity(0);
		Robot.chassis.setRightMotorVelocity(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}