package org.usfirst.frc.team2141.robot.subsystems;

import org.usfirst.frc.team2141.robot.commands.IntakeIn;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class wheelIntake extends Subsystem {
	
	private Spark leftIntakeMotor;
	private Spark rightIntakeMotor;
	
	public wheelIntake() {
		leftIntakeMotor = new Spark(0);
		rightIntakeMotor = new Spark(1);
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(null);
    }
    
    public void setMotorSpeed(double speed) {
    	this.leftIntakeMotor.set(speed);
    	this.rightIntakeMotor.set(-speed);
    }
}

