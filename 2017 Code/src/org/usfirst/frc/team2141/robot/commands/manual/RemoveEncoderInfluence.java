package org.usfirst.frc.team2141.robot.commands.manual;

import org.usfirst.frc.team2141.robot.Robot;
import org.usfirst.frc.team2141.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RemoveEncoderInfluence extends Command {

    public RemoveEncoderInfluence() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassis.getLeftMotor().setPID(
				0.0, 
				0.0,
				0.0, 
				RobotMap.DRIVE_LOW_VELOCITY_F, 
				RobotMap.DRIVE_IZONE, 
				RobotMap.DRIVE_RAMP_RATE, 
				0);
    	Robot.chassis.getLeftMotor().setPID(
				0.0,
				0.0,
				0.0,
				RobotMap.DRIVE_HIGH_VELOCITY_F,
				RobotMap.DRIVE_IZONE,
				RobotMap.DRIVE_RAMP_RATE,
				1);
    	Robot.chassis.getRightMotor().setPID(
				0.0,
				0.0,
				0.0,
				RobotMap.DRIVE_LOW_VELOCITY_F,
				RobotMap.DRIVE_IZONE,
				RobotMap.DRIVE_RAMP_RATE,
				0);
    	Robot.chassis.getRightMotor().setPID(
				0.0,
				0.0,
				0.0,
				RobotMap.DRIVE_HIGH_VELOCITY_F,
				RobotMap.DRIVE_IZONE,
				RobotMap.DRIVE_RAMP_RATE,
				1);
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
    	Robot.chassis.getLeftMotor().setPID(
				RobotMap.DRIVE_LOW_VELOCITY_P, 
				RobotMap.DRIVE_LOW_VELOCITY_I,
				RobotMap.DRIVE_LOW_VELOCITY_D, 
				RobotMap.DRIVE_LOW_VELOCITY_F, 
				RobotMap.DRIVE_IZONE, 
				RobotMap.DRIVE_RAMP_RATE, 
				0);
    	Robot.chassis.getLeftMotor().setPID(
				RobotMap.DRIVE_HIGH_VELOCITY_P,
				RobotMap.DRIVE_HIGH_VELOCITY_I,
				RobotMap.DRIVE_HIGH_VELOCITY_D,
				RobotMap.DRIVE_HIGH_VELOCITY_F,
				RobotMap.DRIVE_IZONE,
				RobotMap.DRIVE_RAMP_RATE,
				1);
    	Robot.chassis.getRightMotor().setPID(
				RobotMap.DRIVE_LOW_VELOCITY_P,
				RobotMap.DRIVE_LOW_VELOCITY_I,
				RobotMap.DRIVE_LOW_VELOCITY_D,
				RobotMap.DRIVE_LOW_VELOCITY_F,
				RobotMap.DRIVE_IZONE,
				RobotMap.DRIVE_RAMP_RATE,
				0);
    	Robot.chassis.getRightMotor().setPID(
				RobotMap.DRIVE_HIGH_VELOCITY_P,
				RobotMap.DRIVE_HIGH_VELOCITY_I,
				RobotMap.DRIVE_HIGH_VELOCITY_D,
				RobotMap.DRIVE_HIGH_VELOCITY_F,
				RobotMap.DRIVE_IZONE,
				RobotMap.DRIVE_RAMP_RATE,
				1);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
