package org.usfirst.frc.team2141.robot.commands.chassis;

import jaci.pathfinder.modifiers.TankModifier;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.usfirst.frc.team2141.robot.Robot;
import org.usfirst.frc.team2141.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class FollowProfile extends Command {

	ScheduledExecutorService scheduler;
	ScheduledFuture<?> motionFollower;
	TankModifier modifier;
	
    public FollowProfile(TankModifier profile) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    	scheduler = Executors.newScheduledThreadPool(1);
    	modifier = profile;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	Robot.chassis.getLeftFollower().setTrajectory(modifier.getLeftTrajectory());
    	Robot.chassis.getRightFollower().setTrajectory(modifier.getRightTrajectory());
    	
    	Robot.chassis.setBothToLow();
    	Robot.chassis.zeroEncoders();	
    	motionFollower = scheduler.scheduleAtFixedRate(new Runnable(){
    	            	public void run(){
    	            		Robot.chassis.setLeftMotorVelocity(
    	            				Robot.chassis.getLeftFollower().calculate(
    	            						Robot.chassis.convertVelocityTicksToInchesPerSecond(Robot.chassis.getLeftEncoderCount())/10.0));
    	            		Robot.chassis.setRightMotorVelocity(
    	            				Robot.chassis.getRightFollower().calculate(
    	            						Robot.chassis.convertVelocityTicksToInchesPerSecond(Robot.chassis.getRightEncoderCount())/10.0));
    	            		}
    	            }, 
    	            (int)(RobotMap.PROFILE_DT * 1000), 
    	            (int)(RobotMap.PROFILE_DT * 1000), 
    	            TimeUnit.MILLISECONDS);   

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Left Profile Error", Robot.chassis.convertVelocityTicksToInchesPerSecond(Robot.chassis.getLeftEncoderCount())/10.0 - Robot.chassis.getLeftFollower().getSegment().position);
    	SmartDashboard.putNumber("Right Profile Error", Robot.chassis.convertVelocityTicksToInchesPerSecond(Robot.chassis.getRightEncoderCount())/10.0 - Robot.chassis.getRightFollower().getSegment().position);

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.chassis.getLeftFollower().isFinished() && Robot.chassis.getRightFollower().isFinished();
    }

    // Called once after isFinished returns true
    protected void end() {
		motionFollower.cancel(true);
    	System.out.println("Left Profile Error = " + Double.toString(Robot.chassis.convertVelocityTicksToInchesPerSecond(Robot.chassis.getLeftEncoderCount())/10.0 - Robot.chassis.getLeftFollower().getSegment().position));
    	System.out.println("Right Profile Error = " + Double.toString(Robot.chassis.convertVelocityTicksToInchesPerSecond(Robot.chassis.getRightEncoderCount())/10.0 - Robot.chassis.getRightFollower().getSegment().position));
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
		motionFollower.cancel(true);

    }
}
