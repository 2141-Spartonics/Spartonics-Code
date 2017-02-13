package org.usfirst.frc.team2141.robot.subsystems;

import org.usfirst.frc.team2141.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	
	private CANTalon intakeMotor;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(null);
    }
    public Intake(){
    	intakeMotor = new CANTalon(RobotMap.INTAKE_MOTOR);
    
    	this.intakeMotor.changeControlMode(TalonControlMode.Voltage);
    }
    /**
     * Sets the intakeMotor to speed
     * @param speed the speed that the motor will be set
     */
    public void setIntakeMotor(double speed){
    	this.intakeMotor.set(speed * 12);
    }
}

