package org.usfirst.frc.team2141.robot.commands;

import org.usfirst.frc.team2141.robot.Robot;

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
    	
    	if (Robot.oi.getDriveStick().getY() > 0.8 || Robot.oi.getDriveStick().getY() < -0.8 ){
    		Robot.chassis.setBothToHighSpeed();
    	}else{
    		Robot.chassis.setBothToLowSpeed();
    	}
    	
    	System.out.println("Right Position " + Robot.chassis.getRightEncoderCount());
    	System.out.println("Right Velocity " + Robot.chassis.getRightEncoderVelocity());
    	System.out.println("Left Position " + Robot.chassis.getLeftEncoderCount());
    	System.out.println("Left Velocity " + Robot.chassis.getLeftEncoderVelocity());

    	Robot.oi.rumbleLeftJoystick(0);
    	Robot.oi.rumbleRightJoystick(0);
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
