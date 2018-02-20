package org.usfirst.frc.team2141.robot.autonomousCommands;

import org.usfirst.frc.team2141.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraight extends Command {

	double position;
    public DriveStraight(double inches) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    	position = inches / (4 * Math.PI) * 1030;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassis.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double error = position - Robot.chassis.getAverageEncoderPosition();
		Robot.chassis.setLeftMotorVelocity(error/Math.abs(error)*Math.min(0.25, Math.abs(.0005*error)+0.0375));
		Robot.chassis.setRightMotorVelocity(-error/Math.abs(error)*Math.min(0.25, Math.abs(.0005*error)+0.0375));

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		if (position > 0) {
			return Robot.chassis.getAverageEncoderPosition() > position;
		} else {
			return Robot.chassis.getAverageEncoderPosition() < position;
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
