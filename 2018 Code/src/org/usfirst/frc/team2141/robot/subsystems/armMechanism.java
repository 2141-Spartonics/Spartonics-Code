package org.usfirst.frc.team2141.robot.subsystems;

import org.usfirst.frc.team2141.robot.Robot;
import org.usfirst.frc.team2141.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class armMechanism extends Subsystem {
	
	private CANTalon intakeMotor;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public armMechanism(){
    	intakeMotor = new CANTalon(RobotMap.ARM_MOTOR);
        }
    
    public void initDefaultCommand() {
    	setDefaultCommand(null);
    }
   
    
    /**
     * Sets the intakeMotor to speed
     * @param speed the speed that the motor will be set
     */
    public void setArmMotor(double speed){
    	this.intakeMotor.set(speed);
    	
    }
}
    	
    

