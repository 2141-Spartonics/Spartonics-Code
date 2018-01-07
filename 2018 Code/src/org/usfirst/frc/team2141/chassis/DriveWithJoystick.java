package org.usfirst.frc.team2141.chassis;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2141.robot.Robot;
import org.usfirst.frc.team2141.robot.RobotMap;

/**
 *
 */
public class DriveWithJoystick extends Command {
	public DriveWithJoystick() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.chassis);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		//Robot.chassis.setCurrentLimitEnabled(false);
				//double leftVelocity = Robot.chassis.getLeftEncoderVelocity();
				//double leftSetpoint = Robot.chassis.getLeftMotorVelocitySetpoint();
				//double rightVelocity = Robot.chassis.getRightEncoderVelocity();
				//double rightSetpoint = Robot.chassis.getRightMotorVelocitySetpoint();
				    	
		    	/*if (leftSetpoint < RobotMap.SHIFTING_SPEED_THRESHOLD &&			
		    			leftSetpoint > -RobotMap.SHIFTING_SPEED_THRESHOLD && 
		    			rightSetpoint < RobotMap.SHIFTING_SPEED_THRESHOLD &&			
		    			rightSetpoint > -RobotMap.SHIFTING_SPEED_THRESHOLD){
		    		Robot.chassis.setBothToLow();
		    	}else if(leftVelocity > RobotMap.SHIFTING_SPEED_THRESHOLD &&
		    			leftSetpoint > leftVelocity && 
		    			rightVelocity > RobotMap.SHIFTING_SPEED_THRESHOLD &&
		    			rightSetpoint > rightVelocity){
		    		Robot.chassis.setBothToHigh();
		    	}else if(leftVelocity < -RobotMap.SHIFTING_SPEED_THRESHOLD &&
		    			leftSetpoint < leftVelocity && 
		    			rightVelocity < -RobotMap.SHIFTING_SPEED_THRESHOLD &&
		    			rightSetpoint < rightVelocity){
		    		Robot.chassis.setBothToHigh();
		    	*/
		    	
		    	//Robot.chassis.arcadeDrive(Robot.oi.getRightY(), Robot.oi.getLeftX());
		    	
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
