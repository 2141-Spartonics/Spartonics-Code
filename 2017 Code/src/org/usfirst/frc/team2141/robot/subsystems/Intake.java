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
public class Intake extends Subsystem {
	
	private CANTalon intakeMotor;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public Intake(){
    	intakeMotor = new CANTalon(RobotMap.INTAKE_MOTOR);
    
    	this.intakeMotor.changeControlMode(TalonControlMode.Voltage);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(null);
    }
    
    public void publishToSmartDashboard(){
    	SmartDashboard.putNumber("Intake Amps", Robot.PDP.getCurrent(5));
    }
    
    /**
     * Sets the intakeMotor to speed
     * @param speed the speed that the motor will be set
     */
    public void setIntakeMotor(double speed){
    	if(speed > 1.0 || speed < -1.0){
    		this.intakeMotor.set(speed);
    	}else{
    		this.intakeMotor.set(speed * 12.0);
    	}
    }
}

