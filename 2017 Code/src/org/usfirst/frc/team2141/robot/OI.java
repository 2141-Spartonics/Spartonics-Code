package org.usfirst.frc.team2141.robot;

import org.usfirst.frc.team2141.robot.commands.FeederControl;
import org.usfirst.frc.team2141.robot.commands.ManualDriving;
import org.usfirst.frc.team2141.robot.commands.WinchCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	private Joystick driveStick;
	private JoystickButton[] buttons;

	public Joystick getDriveStick() {
		return driveStick;
	}

	public boolean getButtonValue(int buttonNum) {
		return this.buttons[buttonNum].get();
	}

	public JoystickButton getButton(int buttonNum) {
		return this.buttons[buttonNum];
	}

	public OI() {

		driveStick = new Joystick(RobotMap.DRIVE_STICK);
		buttons = new JoystickButton[13];

		for (int i = 2; i < 10; i++) {
			buttons[i] = new JoystickButton(driveStick, i);
		}

		this.getButton(RobotMap.SHIFT_DOWN_BUTTON).whileHeld(new ManualDriving());
		this.getButton(RobotMap.FEEDER_CONTROL_BUTTON).toggleWhenPressed(new FeederControl());
		this.getButton(RobotMap.WINCH_CONTROL_BUTTON).whenPressed(new WinchCommand());
		

	}
}