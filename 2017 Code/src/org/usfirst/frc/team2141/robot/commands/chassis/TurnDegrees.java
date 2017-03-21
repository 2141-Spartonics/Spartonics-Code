package org.usfirst.frc.team2141.robot.commands.chassis;

import org.usfirst.frc.team2141.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnDegrees extends Command {

	double target;
	double angleOffset;
	double speed;
	double error;
	
    public TurnDegrees(double degrees, double turnSpeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    	target = degrees;
    	speed = turnSpeed;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassis.zeroEncoders();
    	angleOffset = Robot.imu.getAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	error = target - (Robot.imu.getAngle() - angleOffset);
    	
    	if (error <= 0) {
    		Robot.chassis.setLeftMotorVelocity(speed);
    		Robot.chassis.setRightMotorVelocity(-speed);
    	} else if (error > 0) {
    		Robot.chassis.setLeftMotorVelocity(-speed);
    		Robot.chassis.setRightMotorVelocity(speed);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return error < 1.0;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
