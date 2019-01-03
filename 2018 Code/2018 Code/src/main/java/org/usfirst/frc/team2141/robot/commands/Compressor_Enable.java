package org.usfirst.frc.team2141.robot.commands;

import org.usfirst.frc.team2141.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Compressor_Enable extends Command {
	boolean currentStatus;


	public Compressor_Enable() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.pneumatics);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		currentStatus = false;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.pneumatics.enableCompressor();

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {

		if (Robot.pneumatics.getPressure() > 120){
			currentStatus = true;
		} else 
			currentStatus = false;
		
		return currentStatus;
		
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.pneumatics.disableCompressor();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.pneumatics.disableCompressor();
	}
}
