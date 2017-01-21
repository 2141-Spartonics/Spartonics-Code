	package org.usfirst.frc.team2141.robot.commands;

import org.usfirst.frc.team2141.robot.Robot;
import org.usfirst.frc.team2141.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveWithJoystick extends Command {

	public DriveWithJoystick() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.chassis.setBothToLow();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		Robot.chassis.arcadeDrive(Robot.oi.getDriveStick().getX(), Robot.oi.getDriveStick().getY());
		if (Robot.chassis.getLeftRate() > RobotMap.SPEED_THRESHOLD) {
			Robot.chassis.setLeftToHigh();
		} else {
			Robot.chassis.setLeftToLow();
		}
		
		if (Robot.chassis.getRightRate() > RobotMap.SPEED_THRESHOLD) {
			Robot.chassis.setRightToHigh();
		} else {
			Robot.chassis.setRightToLow();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
