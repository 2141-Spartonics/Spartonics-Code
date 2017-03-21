package org.usfirst.frc.team2141.robot.commands.chassis;

import org.usfirst.frc.team2141.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnDegrees extends Command {

	double target;
	double currentAngle;
	double speed;
	
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
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentAngle = Robot.imu.getAngleX();
    	
    	if (target <= 0) {
    		Robot.chassis.setLeftMotorVelocity(speed);
    		Robot.chassis.setRightMotorVelocity(-speed);
    	} else if (target > 0) {
    		Robot.chassis.setLeftMotorVelocity(-speed);
    		Robot.chassis.setRightMotorVelocity(speed);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (target >= currentAngle + 1 || target <= currentAngle - 1){
    		return false;
    	} else {
    		return true;
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
