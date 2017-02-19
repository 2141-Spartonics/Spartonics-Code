package org.usfirst.frc.team2141.robot.commands;

import jaci.pathfinder.Trajectory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.usfirst.frc.team2141.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FollowMotionProfile extends Command {

	ScheduledExecutorService scheduler;
	ScheduledFuture<?> motionFollower;
	Trajectory trajectory;

    public FollowMotionProfile(Trajectory traj) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    	scheduler = Executors.newScheduledThreadPool(1);
    	trajectory = traj;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassis.setBothToLow();
    	Robot.chassis.zeroEncoders();
    	
    	motionFollower = scheduler.scheduleAtFixedRate(new Runnable(){
    	            	public void run(){
    	            		Robot.chassis.setLeftMotorVelocity(
    	            				Robot.chassis.getLeftFollower().calculate(Robot.chassis.getLeftEncoderCount()));
    	            		Robot.chassis.setRightMotorVelocity(
    	            				Robot.chassis.getRightFollower().calculate(Robot.chassis.getRightEncoderCount()));
    	            		Robot.chassis.zeroEncoders();}
    	            }, 
    	            5, 
    	            5, 
    	            TimeUnit.MILLISECONDS);   
    
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.chassis.getLeftFollower().isFinished() && Robot.chassis.getRightFollower().isFinished();
    }

    // Called once after isFinished returns true
    protected void end() {
		motionFollower.cancel(true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
		motionFollower.cancel(true);
    }
}
