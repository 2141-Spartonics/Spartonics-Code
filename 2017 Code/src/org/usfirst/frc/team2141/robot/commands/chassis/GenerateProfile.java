package org.usfirst.frc.team2141.robot.commands.chassis;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.PathfinderJNI;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GenerateProfile extends Command {

	Waypoint[] points;
	Trajectory.Config config;
	String fileName;
	
    public GenerateProfile(Waypoint[] waypoints, Trajectory.Config profileConfig, String file) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	points = waypoints;
    	config = profileConfig;
    	fileName = file;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		TankModifier modifier = new TankModifier(Pathfinder.generate(points, config)).modify(26.5);
		Trajectory left = modifier.getLeftTrajectory();
		Trajectory right = modifier.getRightTrajectory();
		PathfinderJNI.trajectorySerializeCSV(left.segments, String.join("", "Left ", fileName));
		PathfinderJNI.trajectorySerializeCSV(right.segments, String.join("", "Right ", fileName));

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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
