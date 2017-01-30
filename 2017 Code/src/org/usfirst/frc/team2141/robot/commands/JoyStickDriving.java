package org.usfirst.frc.team2141.robot.commands;

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
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassis.arcadeDrive(Robot.oi.getDriveStick().getY(), Robot.oi.getDriveStick().getX(), true); 
    	
    	if(Robot.oi.getButton(RobotMap.SHIFT_UP_BUTTON)){
    		Robot.chassis.setToHighSpeed();
    	}  
    	else if (Robot.oi.getButton(RobotMap.SHIFT_DOWN_BUTTON)){
    		Robot.chassis.setToLowSpeed();
    	}else{
    		Robot.chassis.closeSolenoid();
    	}
    	
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
