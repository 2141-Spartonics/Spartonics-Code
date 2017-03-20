package org.usfirst.frc.team2141.robot.commands.chassis;

import org.usfirst.frc.team2141.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnDegrees extends Command {

	double degreesToTurn;
	double currentAngle;
	double speed;
	
    public TurnDegrees(double degrees, double turnSpeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    	degreesToTurn = degrees;
    	speed = turnSpeed;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassis.zeroEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentAngle = Robot.imu.getAngleX();
    	
    	if (degreesToTurn <= 0) {
    		Robot.chassis.setLeftMotorVoltage(0.8);
    		Robot.chassis.setRightMotorVoltage(0.8);
    	} else if (degreesToTurn > 0) {
    		Robot.chassis.setLeftMotorVoltage(0.8);
    		Robot.chassis.setRightMotorVoltage(0.8);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (degreesToTurn >= currentAngle + 1 || degreesToTurn <= currentAngle - 1){
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
