package org.usfirst.frc.team2141.robot.commands.autonomous;

import org.usfirst.frc.team2141.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraight extends Command {

	double encoderCount;
	double maxSpeed;
	
	public DriveStraight(double distanceInInches, double drivingSpeed) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.chassis);		
		encoderCount = Robot.chassis.convertInchesToTicks(distanceInInches);
		maxSpeed = drivingSpeed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.chassis.zeroEncoders();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double error = encoderCount - Robot.chassis.getAverageEncoderPosition();
		//double setpoint = error/Math.abs(error)*Math.min(maxSpeed, Math.abs(.00005*error)+0.05);
		
		Robot.chassis.setLeftMotorVelocity(maxSpeed);
		Robot.chassis.setRightMotorVelocity(maxSpeed);

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
		Robot.chassis.setLeftMotorVelocity(0);
		Robot.chassis.setRightMotorVelocity(0);
	}
}