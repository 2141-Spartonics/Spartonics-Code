package org.usfirst.frc.team2141.robot.commands;

import org.usfirst.frc.team2141.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveAtSpeed extends Command {

	double setpoint;
	boolean lowSpeed;
	
    public DriveAtSpeed(double speed, boolean low) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    	setpoint = speed;
    	lowSpeed = low;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(lowSpeed){
    		Robot.chassis.setBothToLow();
    	}else{
    		Robot.chassis.setBothToHigh();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassis.setLeftMotorVelocity(setpoint);
    	Robot.chassis.setRightMotorVelocity(setpoint);

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.setRightMotorVoltage(0);
    	Robot.chassis.setLeftMotorVoltage(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
       	Robot.chassis.setRightMotorVoltage(0);
    	Robot.chassis.setLeftMotorVoltage(0);
    }
}
