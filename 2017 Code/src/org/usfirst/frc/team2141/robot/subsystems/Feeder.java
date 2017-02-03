package org.usfirst.frc.team2141.robot.subsystems;

import org.usfirst.frc.team2141.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Feeder extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
    CANTalon feederMotor;
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
 
    }
    public Feeder(){
    	feederMotor = new CANTalon(RobotMap.FEEDER_MOTOR);
    	
    }
    /**
     * Sets the feederMotor to speed
     * @param speed Speed is the speed chosen for the motor to be set to
     */
    public void setFeederSpeed(double speed){
	  this.feederMotor.set(speed);
    }    
    
    
    
    
    
}

