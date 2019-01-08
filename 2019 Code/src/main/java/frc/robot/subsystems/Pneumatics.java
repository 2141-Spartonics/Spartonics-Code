/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

/**
 * Pneumatics subsystem, inclides all pressure sensors and the on board
 * compressor
 * 
 * @author Bernie Conrad
 * @version 1/7/19
 */
public class Pneumatics extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	Relay compressor;
	boolean manualControl = false;

	AnalogInput storedPressureSensor;
	AnalogInput workingPressureSensor;

	public Pneumatics() {
		compressor = new Relay(RobotMap.COMPRESSOR);
		storedPressureSensor = new AnalogInput(RobotMap.STORED_PRESSURE_SENSOR);
		workingPressureSensor = new AnalogInput(RobotMap.WORKING_PRESSURE_SENSOR);
	}

	/**
	 * Provides a method to post data from the subsystem to the smartDashboard
	 */
	public void publishToSmartDashBoard() {
		SmartDashboard.putNumber("Stored Pressure", getPressure(getStoredPressureSensor()));
		SmartDashboard.putNumber("Working Pressure", getPressure(getWorkingPressureSensor()));
		SmartDashboard.putBoolean("Compressor Enabled", getCompressorState());
	}

	/**
	 * Sets the default command for the subystem to use
	 */
	public void initDefaultCommand() {
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(null);
	}

	/** 
	 * Automatically controls the compressor when a command that would otherwise control it is not enabled with some fancy pants historesis
	 */
	public void automaticCompressorControl() {
		// TODO test accuracy of method for compressor control
		if (!manualControl & getPressure(getStoredPressureSensor()) < 100) {
			manualEnableCompressor();
		}

		if (!manualControl & getPressure(getStoredPressureSensor()) > 120) {
			manualDisableCompressor();
		}
	}

	/** 
	 * Sets control mode of the compressor
	 */
	public void setControlMode(boolean manualEnabled) {
		manualControl = manualEnabled;
	}

	/**
	 * Sets the value of the relay that controls the compressor to on
	 */
	public void manualEnableCompressor() {
		compressor.set(Value.kOn);
	}

	/**
	 * Sets the value of the relay that controls the compressor to off
	 */
	public void manualDisableCompressor() {
		compressor.set(Value.kOff);
	}

	/**
	 * Gets the pressure of a given analog input calibrated per the Rev datasheets
	 * See: http://www.revrobotics.com/content/docs/REV-11-1107-DS.pdf
	 * 
	 * @param pressureSensor
	 * @return calibratedPressure
	 */

	public double getPressure(AnalogInput pressureSensor) {
		return 250 * (pressureSensor.getVoltage() / 5) - 25;
	}

	/**
	 * @return the compressor
	 */
	public Relay getCompressor() {
		return compressor;
	}

	/**
	 * Returns the compressors state in a boolean
	 * @return boolean indicating whether or not the compressor is enabled
	 */
	public boolean getCompressorState() {
		if (getCompressor().get() == Value.kOn) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return the storedPressureSensor
	 */
	public AnalogInput getStoredPressureSensor() {
		return storedPressureSensor;
	}

	/**
	 * @return the workingPressureSensor
	 */
	public AnalogInput getWorkingPressureSensor() {
		return workingPressureSensor;
	}

}