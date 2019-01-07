/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * TODO the whole thing
 */
public class Pneumatics extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	DoubleSolenoid intake;
	DoubleSolenoid elevatorGearboxShifter;
	DoubleSolenoid extraValve;

	Relay compressor;
	Compressor compressorPressure;

	AnalogInput pressureSensor;
	boolean intakeClosed;
	boolean gearboxInHigh;

	public Pneumatics() {
		compressor = new Relay(1);
		compressorPressure = new Compressor();

		pressureSensor = new AnalogInput(0);
	}

	public void publishToSmartDashBoard() {

	}

	public void initDefaultCommand() {
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(null);
	}

	public boolean getCompressorPressure() {
		return compressorPressure.getPressureSwitchValue();
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