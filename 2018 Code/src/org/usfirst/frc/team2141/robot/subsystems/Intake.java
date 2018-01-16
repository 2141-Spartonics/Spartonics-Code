package org.usfirst.frc.team2141.robot.subsystems;

import org.usfirst.frc.team2141.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Intake extends Subsystem {
	
	private DoubleSolenoid intakePiston;
	boolean closed;
	
	private Spark leftIntakeMotor;
	private Spark rightIntakeMotor;

	public void initDefaultCommand() {
		setDefaultCommand(null);
	}

	public Intake() {
			intakePiston = new DoubleSolenoid(RobotMap.INTAKE_SOLENOID_CHANNEL_A, RobotMap.INTAKE_SOLENOID_CHANNEL_B);
			
			leftIntakeMotor = new Spark(RobotMap.LEFT_INTAKE_MOTOR);
			rightIntakeMotor = new Spark(RobotMap.RIGHT_INTAKE_MOTOR);
	}
	
	public void publishToSmartDashboard(){
		SmartDashboard.putBoolean("Intake Closed", closed);
	}

	public void closeIntake() {
		this.intakePiston.set(DoubleSolenoid.Value.kReverse);
		closed = true;
	}

	
	public void openintake() {
		this.intakePiston.set(DoubleSolenoid.Value.kForward);
		closed = false;
	}
	
    public void setIntakeSpeed(double speed) {
    	this.leftIntakeMotor.set(speed);
    	this.rightIntakeMotor.set(-speed);
    }

}
