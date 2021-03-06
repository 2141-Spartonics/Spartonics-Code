package org.usfirst.frc.team2141.robot.autonomousCommands;


import org.usfirst.frc.team2141.robot.Robot;
import org.usfirst.frc.team2141.robot.subsystems.Chassis;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveAtVelocity extends Command {

	double velocity;
    public DriveAtVelocity(double velocity) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		requires(Robot.chassis);
		this.velocity = velocity;
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassis.setLeftMotorSpeed(velocity, true);
    	Robot.chassis.setRightMotorSpeed(-velocity, true);

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Robot.chassis.setLeftMotorVelocity(0);
    	//Robot.chassis.setRightMotorVelocity(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.chassis.setLeftMotorSpeed(0, true);
    	Robot.chassis.setRightMotorSpeed(0, true);
    }
}
