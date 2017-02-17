package org.usfirst.frc.team2141.robot.commands.chassis;

import org.usfirst.frc.team2141.robot.Robot;
import org.usfirst.frc.team2141.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class JoyStickDriving extends Command {

	public JoyStickDriving() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.chassis.setBothToLow();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double leftVelocity = Robot.chassis.getLeftEncoderVelocity();
		double leftSetpoint = Robot.chassis.getLeftMotorVelocitySetpoint();
		double rightVelocity = Robot.chassis.getRightEncoderVelocity();
		double rightSetpoint = Robot.chassis.getRightMotorVelocitySetpoint();
		    	
    	if (leftSetpoint < RobotMap.SHIFTING_SPEED_THRESHOLD &&			
    			leftSetpoint > -RobotMap.SHIFTING_SPEED_THRESHOLD){
    		Robot.chassis.setLeftToLow();
    	}else if(leftVelocity > RobotMap.SHIFTING_SPEED_THRESHOLD &&
    			leftSetpoint > leftVelocity){
    		Robot.chassis.setLeftToHigh();
    	}else if(leftVelocity < -RobotMap.SHIFTING_SPEED_THRESHOLD &&
    			leftSetpoint < leftVelocity){
    		Robot.chassis.setLeftToHigh();
    	}
    	
    	if (rightSetpoint < RobotMap.SHIFTING_SPEED_THRESHOLD &&			
    			rightSetpoint > -RobotMap.SHIFTING_SPEED_THRESHOLD){
    		Robot.chassis.setRightToLow();
    	}else if(rightVelocity > RobotMap.SHIFTING_SPEED_THRESHOLD &&
    			rightSetpoint > rightVelocity){
    		Robot.chassis.setRightToHigh();
    	}else if(rightVelocity < -RobotMap.SHIFTING_SPEED_THRESHOLD &&
    			rightSetpoint < rightVelocity){
    		Robot.chassis.setRightToHigh();
    	}
    	
    	Robot.chassis.arcadeDrive(Robot.oi.getDriveStick().getY(), Robot.oi.getDriveStick().getX(), true, false);
    	
    }

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
