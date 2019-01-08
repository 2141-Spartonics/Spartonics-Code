/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.outtakecmds.*;
import frc.robot.commands.pneumaticscmds.*;
import frc.robot.commands.intakecmds.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	private Joystick primaryStick;
	private JoystickButton[] primaryButtons;
	private Joystick auxiliaryStick;
	private JoystickButton[] auxiliaryButtons;

	private XboxController xboxController;

	public OI() {

		primaryStick = new Joystick(RobotMap.PRIMARY_STICK_PORT);
		primaryButtons = new JoystickButton[13];

		auxiliaryStick = new Joystick(RobotMap.AUXILIARY_STICK_PORT);
		auxiliaryButtons = new JoystickButton[13];

		xboxController = new XboxController(RobotMap.XBOX_CONTROLLER_PORT);

		for (int i = 1; i <= primaryButtons.length - 1; i++) {
			primaryButtons[i] = new JoystickButton(primaryStick, i);
		}

		for (int i = 1; i <= auxiliaryButtons.length - 1; i++) {
			auxiliaryButtons[i] = new JoystickButton(auxiliaryStick, i);
		}

		// Pneumatics
		getButton(1, true).whileHeld(new enableCompressor());
		getButton(2, true).whenPressed(new disableCompressor());
		getButton(3, true).whenPressed(new extendOuttake());
		getButton(4, true).whenPressed(new retractOuttake());
		getButton(5, true).whenPressed(new openIntake());
		getButton(6, true).whenPressed(new closeIntake());

		// SmartDashboard Manual
		SmartDashboard.putData("Enable Compressor", new enableCompressor());
		SmartDashboard.putData("Disable Compressor", new disableCompressor());
		SmartDashboard.putData("Extend Outtake", new extendOuttake());
		SmartDashboard.putData("Retract Outtake", new retractOuttake());
		SmartDashboard.putData("Open Intake", new openIntake());
		SmartDashboard.putData("Close Intake", new closeIntake());
	}

	public boolean getButtonValue(int buttonNum) {
		return this.primaryButtons[buttonNum].get();
	}

	public boolean getButtonValue(int buttonNum, boolean auxiliary) {
		if (auxiliary) {
			return this.auxiliaryButtons[buttonNum].get();
		} else {
			return this.primaryButtons[buttonNum].get();
		}
	}

	public JoystickButton getButton(int buttonNum) {
		return this.primaryButtons[buttonNum];
	}

	public JoystickButton getButton(int buttonNum, boolean auxiliary) {
		if (auxiliary) {
			return this.auxiliaryButtons[buttonNum];
		} else {
			return this.primaryButtons[buttonNum];
		}
	}

	public double getLeftX() {
		return primaryStick.getRawAxis(0);
	}

	public double getLeftY() {
		return primaryStick.getRawAxis(1);
	}

	public double getRightX() {
		return primaryStick.getRawAxis(4);
	}

	public double getRightY() {
		return primaryStick.getRawAxis(5);
	}

	public void rumbleLeftJoystick(int rumbleValue) {
		this.primaryStick.setRumble(RumbleType.kLeftRumble, rumbleValue);
	}

	public void rumbleRightJoystick(int rumbeValue) {
		this.primaryStick.setRumble(RumbleType.kRightRumble, rumbeValue);
	}

	/**
	 * @return the primaryStick
	 */
	public Joystick getPrimaryStick() {
		return primaryStick;
	}

	/**
	 * @return the auxiliaryStick
	 */
	public Joystick getAuxiliaryStick() {
		return auxiliaryStick;
	}

	/**
	 * @param primaryStick the primaryStick to set
	 */
	public void setPrimaryStick(Joystick primaryStick) {
		this.primaryStick = primaryStick;
	}

	/**
	 * @return the primaryButtons
	 */
	public JoystickButton[] getPrimaryButtons() {
		return primaryButtons;
	}

	/**
	 * @param primaryButtons the primaryButtons to set
	 */
	public void setPrimaryButtons(JoystickButton[] primaryButtons) {
		this.primaryButtons = primaryButtons;
	}

	/**
	 * @param auxiliaryStick the auxiliaryStick to set
	 */
	public void setAuxiliaryStick(Joystick auxiliaryStick) {
		this.auxiliaryStick = auxiliaryStick;
	}

	/**
	 * @return the auxiliaryButtons
	 */
	public JoystickButton[] getAuxiliaryButtons() {
		return auxiliaryButtons;
	}

	/**
	 * @param auxiliaryButtons the auxiliaryButtons to set
	 */
	public void setAuxiliaryButtons(JoystickButton[] auxiliaryButtons) {
		this.auxiliaryButtons = auxiliaryButtons;
	}

	/**
	 * @return the xboxController
	 */
	public XboxController getXboxController() {
		return xboxController;
	}

	/**
	 * @param xboxController the xboxController to set
	 */
	public void setXboxController(XboxController xboxController) {
		this.xboxController = xboxController;
	}

}