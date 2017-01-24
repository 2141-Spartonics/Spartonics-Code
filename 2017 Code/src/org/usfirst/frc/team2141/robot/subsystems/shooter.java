package org.usfirst.frc.team2141.robot.subsystems;

import org.usfirst.frc.team2141.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class shooter extends Subsystem {
	CANTalon shooterMotor;
	Encoder shooterEncoder;
	PIDController shooterPID;
	PIDOutput output;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public shooter(){
    	shooterMotor = new CANTalon(RobotMap.SHOOTER_MOTOR);
    	output = new PIDOutput() {
			
			@Override
			public void pidWrite(double output) { //what the hell is this... - Justin
				// TODO Auto-generated method stub
				
			}
		};
    	shooterPID = new PIDController(0, 0, 0, this.shooterEncoder, output);// this too -Justin
    }
    public void setShooterMotor(double speed){
    	this.shooterMotor.set(speed);
    }
    public double getVelocity(){
    	return this.shooterEncoder.getRate();
    }
}

