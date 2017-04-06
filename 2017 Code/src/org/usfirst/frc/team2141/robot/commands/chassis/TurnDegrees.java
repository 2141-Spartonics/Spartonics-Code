package org.usfirst.frc.team2141.robot.commands.chassis;

import org.usfirst.frc.team2141.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnDegrees extends Command {

	double target;
	double angleOffset;
	double maxSpeed;
	double error;
	
    public TurnDegrees(double degrees, double turnSpeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    	target = degrees;
    	maxSpeed = turnSpeed;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassis.zeroEncoders();
    	Robot.chassis.setBothToLow();
    	angleOffset = Robot.imu.getAngleX()/4;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	error = target - (Robot.imu.getAngleX()/4 - angleOffset);
    	
    	Robot.chassis.setLeftMotorVelocity(-error/Math.abs(error)*Math.min(maxSpeed, Math.abs(.005*error) + .05));
    	Robot.chassis.setRightMotorVelocity(error/Math.abs(error)*Math.min(maxSpeed, Math.abs(.005*error) + .05));
    	
    	System.out.println("Turn Error = " + Double.toString(error));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Math.abs(error) < 1.0;
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
