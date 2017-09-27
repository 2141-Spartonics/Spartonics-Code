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
		requires(Robot.chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		//Robot.chassis.setCurrentLimitEnabled(false);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

    	
    	Robot.chassis.driveWithJoystick(Robot.oi.getDriveStick());
    	
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