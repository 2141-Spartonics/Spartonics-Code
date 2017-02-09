package org.usfirst.frc.team2141.robot.commands;

import org.usfirst.frc.team2141.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraight extends Command {

	double encoderCount;
	double bernsConstant;

	public DriveStraight() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.chassis);
		bernsConstant = 12/13;
		
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.chassis.zeroEncoders();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute(double distance) {
		Robot.chassis.setLeftMotorVoltage(.5);
		Robot.chassis.setRightMotorVoltage(.5);
		
		encoderCount = distance / (12.56);
		System.out.println("Encoder Count="+encoderCount);

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if ((Math.abs(Robot.chassis.getRightEncoderCount()) + (Math.abs(Robot.chassis.getLeftEncoderCount()))) / 2 > encoderCount) {
			return true;
		} else {
			return false;
		}

	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.chassis.zeroEncoders();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}