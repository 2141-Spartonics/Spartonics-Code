package org.usfirst.frc.team2141.robot.subsystems;

import org.usfirst.frc.team2141.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Intake extends Subsystem {
	
	private DoubleSolenoid intake;
	boolean intakeClosed;
	AnalogInput pressureSensor;

	public void initDefaultCommand() {
		setDefaultCommand(null);
	}

	public Intake() {
			intake = new DoubleSolenoid(RobotMap.INTAKE_SOLENOID_CHANNEL_A, RobotMap.INTAKE_SOLENOID_CHANNEL_B);
			//pressureSensor = new AnalogInput(0);
	}
	
	public void publishToSmartDashboard(){
		SmartDashboard.putBoolean("Intake Closed", intakeClosed);
		//SmartDashboard.putNumber("Pressure", convertVoltageToPressure(getVoltageRecieved(), .5));
		//SmartDashboard.putNumber("raw pressure", pressureSensor.getVoltage());
	}

	public void closeIntake() {
		this.intake.set(DoubleSolenoid.Value.kReverse);
		intakeClosed = true;
	}
	
	
	public void openintake() {
		this.intake.set(DoubleSolenoid.Value.kForward);
		intakeClosed = false;
	}
	
	/*public double convertVoltageToPressure(double voltageRecieved, double voltageSent) {
		return 250 * (voltageRecieved / voltageSent) - 25;
	}
	
	*/
 /*
	public double getVoltageRecieved() {
		return pressureSensor.getVoltage();
	}
*/
}