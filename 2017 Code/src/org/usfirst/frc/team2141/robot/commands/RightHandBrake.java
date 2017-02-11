package org.usfirst.frc.team2141.robot.commands;

import org.usfirst.frc.team2141.robot.OI;
import org.usfirst.frc.team2141.robot.Robot;
import org.usfirst.frc.team2141.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RightHandBrake extends Command {

    public RightHandBrake() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.oi.rumbleRightJoystick(1);
    	Robot.chassis.setRightToLow();

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.oi.rumbleRightJoystick(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
