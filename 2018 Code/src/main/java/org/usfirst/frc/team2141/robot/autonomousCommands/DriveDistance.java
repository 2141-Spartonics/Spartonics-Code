package org.usfirst.frc.team2141.robot.autonomousCommands;

import org.usfirst.frc.team2141.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveDistance extends Command {

	double position;
	double error;

	public DriveDistance(double inches) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.chassis);
		position = inches / (4 * Math.PI) * 1024;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.chassis.resetEncoders();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		error = position - Robot.chassis.getAverageEncoderPosition();
		Robot.chassis.setLeftMotorSpeed(error / Math.abs(error) * Math.min(0.25, Math.abs(.0005 * error) + 0.0375), true);
		Robot.chassis.setRightMotorSpeed(-error / Math.abs(error) * Math.min(0.25, Math.abs(.0005 * error) + 0.0375), true);
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (error < 50) {
			return true;
		} else {
			return false;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.chassis.setLeftMotorSpeed(0, true);
		Robot.chassis.setRightMotorSpeed(0, true);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
