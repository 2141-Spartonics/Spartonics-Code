package org.usfirst.frc.team2141.robot.subsystems;

import org.usfirst.frc.team2141.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Intake extends Subsystem {
	
	private DoubleSolenoid intakePiston;
	boolean intakeClosed;

	public void initDefaultCommand() {
		setDefaultCommand(null);
	}

	public Intake() {
			intakePiston = new DoubleSolenoid(RobotMap.INTAKE_SOLENOID_CHANNEL_A, RobotMap.INTAKE_SOLENOID_CHANNEL_B);

	}
	
	public void publishToSmartDashboard(){
		SmartDashboard.putBoolean("Intake Closed", intakeClosed);
	}

	public void closeIntake() {
		this.intakePiston.set(DoubleSolenoid.Value.kReverse);
		intakeClosed = true;
	}
	
	
	public void openintake() {
		this.intakePiston.set(DoubleSolenoid.Value.kForward);
		intakeClosed = false;
	}
 

}