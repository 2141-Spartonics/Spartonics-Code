package org.usfirst.frc.team2141.robot.commands.chassis;

import org.usfirst.frc.team2141.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShiftDown extends Command {

    public ShiftDown() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassis.setBothToLow();
		//Robot.chassis.setCurrentLimitEnabled(true);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassis.arcadeDrive(Robot.oi.getRightY(), Robot.oi.getLeftX());
    	Robot.oi.rumbleLeftJoystick(1);
    	Robot.oi.rumbleRightJoystick(1);
		//Robot.chassis.setCurrentLimitEnabled(true);

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
		//Robot.chassis.setCurrentLimitEnabled(false);
    	Robot.oi.rumbleLeftJoystick(0);
    	Robot.oi.rumbleRightJoystick(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
		//Robot.chassis.setCurrentLimitEnabled(false);
    	Robot.oi.rumbleLeftJoystick(0);
    	Robot.oi.rumbleRightJoystick(0);
    }
}
