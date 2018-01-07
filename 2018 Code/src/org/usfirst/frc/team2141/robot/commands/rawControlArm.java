package org.usfirst.frc.team2141.robot.commands;

import org.usfirst.frc.team2141.robot.Robot;
import org.usfirst.frc.team2141.robot.subsystems.armMechanism;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class rawControlArm extends Command {
	
    public rawControlArm() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.armmechanism);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.armmechanism.setArmMotor(0.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.armmechanism.setArmMotor(0.0);;

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.armmechanism.setArmMotor(0.0);;

    	
    }
}
