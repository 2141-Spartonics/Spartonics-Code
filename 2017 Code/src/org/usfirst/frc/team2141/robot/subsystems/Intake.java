package org.usfirst.frc.team2141.robot.subsystems;

import org.usfirst.frc.team2141.robot.RobotMap;
import org.usfirst.frc.team2141.robot.commands.IntakeCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	CANTalon intakeMotor;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new IntakeCommand());
    }
    public Intake(){
    	intakeMotor = new CANTalon(RobotMap.INTAKE_MOTOR);
    }
    /**
     * Sets the intakeMotor to speed
     * @param speed the speed that the motor will be set
     */
    public void setIntakeMotor(double speed){
    	//sets the intakeMotor to speed 
    	this.intakeMotor.set(speed);
    }
}

