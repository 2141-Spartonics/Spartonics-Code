package org.usfirst.frc.team2141.robot.subsystems;

import org.usfirst.frc.team2141.robot.Robot;
import org.usfirst.frc.team2141.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Winch extends Subsystem {
	CANTalon winchMotor;
	DoubleSolenoid winchStopper;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    
    public Winch(){
    	winchMotor = new CANTalon(RobotMap.WINCH_MOTOR);
    	winchStopper = new DoubleSolenoid(RobotMap.WINCH_STOPPER_CHANNEL_A, RobotMap.WINCH_STOPPER_CHANNEL_B);
    }
    
    
    public void setWinchSpeed(double speed){
    	this.winchMotor.set(speed);
    }
    public void pulloutDoubleSolenoid(){
    	this.winchStopper.set(DoubleSolenoid.Value.kForward);
    	
    }
    public void pushinDoubleSolenoid(){
    	this.winchStopper.set(DoubleSolenoid.Value.kReverse);
    }
    public double getpower(){
    	return Robot.PDP.getCurrent(RobotMap.WINCH_PDP_CHANNEL);
    }
    
}

