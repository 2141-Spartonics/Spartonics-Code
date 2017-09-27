package org.usfirst.frc.team2141.robot.subsystems;

import org.usfirst.frc.team2141.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Chassis extends Subsystem {

	private Spark rightMotor;
	private Spark leftMotor;
	private RobotDrive drive;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public Chassis() {
		rightMotor = new Spark(RobotMap.RIGHT_MOTOR);
		leftMotor = new Spark(RobotMap.LEFT_MOTOR);
		drive = new RobotDrive(rightMotor, leftMotor);
		
		this.drive.setInvertedMotor(MotorType.kFrontLeft, true);
		this.drive.setInvertedMotor(MotorType.kFrontRight, true);
		
		
	}
	
	
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void driveWithJoystick(Joystick driveStick) {
    	this.drive.arcadeDrive(driveStick);
    }

    
    
    
}
