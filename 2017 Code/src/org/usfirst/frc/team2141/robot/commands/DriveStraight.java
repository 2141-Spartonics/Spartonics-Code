package org.usfirst.frc.team2141.robot.commands;

import org.usfirst.frc.team2141.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraight extends Command {

	double encoderCount;
	
	public DriveStraight(double distance) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.chassis);		
		encoderCount = distance;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.chassis.zeroEncoders();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.chassis.setLeftMotorVoltage(.5);
		Robot.chassis.setRightMotorVoltage(.5);
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