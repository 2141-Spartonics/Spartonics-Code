package org.usfirst.frc.team2141.robot.subsystems;

import org.usfirst.frc.team2141.robot.RobotMap;
import org.usfirst.frc.team2141.robot.commands.Compressor_Enable;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Pneumatics extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	DoubleSolenoid intake;
	DoubleSolenoid elevatorGearboxShifter;
	DoubleSolenoid extraValve;
	
	Relay compressor;

	AnalogInput pressureSensor;
	boolean intakeClosed;
	boolean gearboxInHigh;
	
	public Pneumatics() {
		intake = new DoubleSolenoid(RobotMap.INTAKE_SOLENOID_CHANNEL_A, RobotMap.INTAKE_SOLENOID_CHANNEL_B);
		elevatorGearboxShifter = new DoubleSolenoid(RobotMap.GEARBOX_SOLENOID_CHANNEL_A, RobotMap.GEARBOX_SOLENOID_CHANNEL_B);
		extraValve = new DoubleSolenoid(RobotMap.EXTRA_SOLENOID_CHANNEL_A, RobotMap.EXTRA_SOLENOID_CHANNEL_B);

		compressor = new Relay(RobotMap.COMPRESSOR_SPIKE);
	}
	
	public void publishToSmartDashBoard() {
		SmartDashboard.putBoolean("Intake Closed", intakeClosed);
		SmartDashboard.putBoolean("Elevator Gearbox in High", gearboxInHigh);
		
		SmartDashboard.putNumber("Stored Pressure", getPressure());
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Compressor_Enable());
    }
    
	public void closeIntake() {
		intake.set(DoubleSolenoid.Value.kReverse);
		intakeClosed = true;
	}

	public void openintake() {
		intake.set(DoubleSolenoid.Value.kForward);
		intakeClosed = false;
	}
	
	public void setGearboxToHigh() {
		elevatorGearboxShifter.set(DoubleSolenoid.Value.kReverse);
		gearboxInHigh = true;
	}

	public void setGearboxToLow() {
		elevatorGearboxShifter.set(DoubleSolenoid.Value.kForward);
		gearboxInHigh = false;
	}
	
	public void closeExtra() {
		extraValve.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void openExtra() {
		extraValve.set(DoubleSolenoid.Value.kForward);
	}
	
	public void enableCompressor() {
		compressor.set(Value.kOn);
	}
	
	public void disableCompressor() {
		compressor.set(Value.kOff);
	}
	
	public double getPressure() {
		return 250 * (pressureSensor.getVoltage() / 5) - 25;
	}
    
}

