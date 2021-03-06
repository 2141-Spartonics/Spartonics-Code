package org.usfirst.frc.team2141.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2141.robot.Robot;
import org.usfirst.frc.team2141.robot.RobotMap;

/**
 *
 */
public class Chassis_DriveWithJoystick extends Command {
	public Chassis_DriveWithJoystick() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.chassis);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.chassis.enablePID();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		// Robot.chassis.setCurrentLimitEnabled(false);
		Robot.chassis.arcadeDrive(Robot.oi.getRightY(), Robot.oi.getLeftX());
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}