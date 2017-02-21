package org.usfirst.frc.team2141.robot.commands.chassis;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.PathfinderJNI;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.usfirst.frc.team2141.robot.Robot;
import org.usfirst.frc.team2141.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GenerateProfile extends Command {

	ScheduledExecutorService scheduler;
	ScheduledFuture<?> motionFollower;
	Trajectory trajectory;
	Waypoint[] points;
	Trajectory.Config config;
	
    public GenerateProfile(Waypoint[] waypoints, Trajectory.Config profileConfig) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	points = waypoints;
    	config = profileConfig;
    	requires(Robot.chassis);
    	scheduler = Executors.newScheduledThreadPool(1);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	System.out.println("In generator");
		TankModifier modifier = new TankModifier(Pathfinder.generate(points, config)).modify(26.5);
		System.out.println("Done generating");
    	Robot.chassis.getLeftFollower().setTrajectory(modifier.getLeftTrajectory());
    	Robot.chassis.getRightFollower().setTrajectory(modifier.getRightTrajectory());
    	System.out.println("Finished all of generation");
    	
    	Robot.chassis.setBothToLow();
    	Robot.chassis.zeroEncoders();
    	
    	System.out.println("In Follower");
    	
    	motionFollower = scheduler.scheduleAtFixedRate(new Runnable(){
    	            	public void run(){
    	            		Robot.chassis.setLeftMotorVelocity(
    	            				Robot.chassis.getLeftFollower().calculate(Robot.chassis.getLeftEncoderCount()));
    	            		Robot.chassis.setRightMotorVelocity(
    	            				Robot.chassis.getRightFollower().calculate(Robot.chassis.getRightEncoderCount()));
    	            		//Robot.chassis.zeroEncoders();
    	            		}
    	            }, 
    	            (int)(RobotMap.PROFILE_DT * 1000), 
    	            (int)(RobotMap.PROFILE_DT * 1000), 
    	            TimeUnit.MILLISECONDS);   

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
		motionFollower.cancel(true);

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
		motionFollower.cancel(true);

    }
}
