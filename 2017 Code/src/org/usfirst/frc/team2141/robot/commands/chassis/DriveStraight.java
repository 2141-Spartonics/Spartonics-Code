package org.usfirst.frc.team2141.robot.commands.chassis;

import org.usfirst.frc.team2141.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraight extends Command {

	double encoderCount;
	double speed;
	
	public DriveStraight(double distance, double drivingSpeed) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.chassis);		
		encoderCount = distance;
		speed = drivingSpeed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.chassis.zeroEncoders();
		Robot.chassis.setBothToLow();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.chassis.setLeftMotorVelocity(speed);
		Robot.chassis.setRightMotorVelocity(speed);
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
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}