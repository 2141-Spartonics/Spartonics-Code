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
import frc.robot.commands.elevatorcmds.*;
import frc.robot.commands.intakecmds.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//private Joystick primaryStick;
	private JoystickButton[] primaryButtons;
	private Joystick auxiliaryStick;
	private JoystickButton[] auxiliaryButtons;

	private XboxController xboxController;

	public OI() {

//		primaryStick = new Joystick(RobotMap.PRIMARY_STICK_PORT);
		primaryButtons = new JoystickButton[13];

		auxiliaryStick = new Joystick(RobotMap.AUXILIARY_STICK_PORT);
		auxiliaryButtons = new JoystickButton[13];

		xboxController = new XboxController(RobotMap.XBOX_CONTROLLER_PORT);

		for (int i = 1; i <= primaryButtons.length - 1; i++) {
			primaryButtons[i] = new JoystickButton(xboxController, i);
		}

		for (int i = 1; i <= auxiliaryButtons.length - 1; i++) {
			auxiliaryButtons[i] = new JoystickButton(auxiliaryStick, i);
		}

		getButton(2, true).whileHeld(new intakeCargo());
		getButton(3, true).whileHeld(new outtakeCargo());

		getButton(1, true).whileHeld(new lowerElevator(0.2));
		getButton(4, true).whileHeld(new raiseElevator(0.2));
		getButton(6, true).whenPressed(new stopElevator());

		getButton(2).whileHeld(new intakeCargo());
		getButton(3).whileHeld(new outtakeCargo());

		getButton(4).whileHeld(new lowerElevator(getLeftTrigger()));
		getButton(5).whileHeld(new raiseElevator(getRightTrigger()));
		getButton(6).whenPressed(new stopElevator());

		getButton(11, true).whenPressed(new intakeHatch());
		getButton(10, true).whenPressed(new outtakeHatch());

		//getButton(5).whileHeld(new setElevatorPosition(5000));
		/*// SmartDashboard Manual
		SmartDashboard.putData("Enable Compressor", new enableCompressor());
		SmartDashboard.putData("Disable Compressor", new disableCompressor());
		SmartDashboard.putData("Open Intake", new openIntake());
		SmartDashboard.putData("Close Intake", new closeIntake());
		*/
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
		double leftX = getXboxController().getRawAxis(0);
		if (Math.abs(leftX) > 0.1)
			return leftX;
		else	
			return 0;	}

	public double getLeftY() {
		return getXboxController().getRawAxis(1);
	}

	public double getRightX() {
		return getXboxController().getRawAxis(4);
	}

	public double getRightY() {
		double rightY = getXboxController().getRawAxis(5);
		if (Math.abs(rightY) > 0.1)
			return rightY;
		else	
			return 0;
	}

	public double getLeftTrigger() {
		return getXboxController().getRawAxis(5);
	}

	public double getRightTrigger() {
		return getXboxController().getRawAxis(6);
	}


	public void rumbleLeftJoystick(int rumbleValue) {
		this.xboxController.setRumble(RumbleType.kLeftRumble, rumbleValue);
	}

	public void rumbleRightJoystick(int rumbleValue) {
		this.xboxController.setRumble(RumbleType.kRightRumble, rumbleValue);
	}

	/**
	 * @return the auxiliaryStick
	 */
	public Joystick getAuxiliaryStick() {
		return auxiliaryStick;
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