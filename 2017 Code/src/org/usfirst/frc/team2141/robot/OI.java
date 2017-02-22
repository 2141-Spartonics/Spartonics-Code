package org.usfirst.frc.team2141.robot;

import org.usfirst.frc.team2141.robot.commands.IntakeCommand;
import org.usfirst.frc.team2141.robot.commands.ShooterControl;
import org.usfirst.frc.team2141.robot.commands.WinchCommand;
import org.usfirst.frc.team2141.robot.commands.chassis.DriveAtSpeed;
import org.usfirst.frc.team2141.robot.commands.chassis.FlipChassisDirection;
import org.usfirst.frc.team2141.robot.commands.chassis.PathAuto;
import org.usfirst.frc.team2141.robot.commands.chassis.ShiftDown;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import manual.WinchDown;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	private Joystick driveStick;
	private JoystickButton[] buttons;

	double testSpeed;
	
	public OI() {

		
		driveStick = new Joystick(RobotMap.DRIVE_STICK_NUMBER);
		buttons = new JoystickButton[13];

		for (int i = 1; i <= 11; i++) {
			buttons[i] = new JoystickButton(driveStick, i);
		}

		//this.getButton(RobotMap.SHIFT_DOWN_BUTTON).whileHeld(new ShiftDown());
		//this.getButton(RobotMap.SHOOTER_CONTROL_BUTTON).whileHeld(new ShooterControl());
		this.getButton(RobotMap.WINCH_CONTROL_BUTTON).toggleWhenPressed(new WinchCommand());
		this.getButton(RobotMap.INTAKE_CONTROL_BUTTON).toggleWhenPressed(new IntakeCommand());
		this.getButton(RobotMap.REVERSE_DRIVE_BUTTON).whenPressed(new FlipChassisDirection());
		this.getButton(RobotMap.WINCH_DOWN).whileHeld(new WinchDown());
		this.getButton(3).whileHeld(new DriveAtSpeed(.25, true));
		this.getButton(1).whileHeld(new DriveAtSpeed(.25, false));
		this.getButton(7).whenPressed(new PathAuto());
				
	}
	
	public Joystick getDriveStick() {
		return driveStick;
	}

	public boolean getButtonValue(int buttonNum) {
		return this.buttons[buttonNum].get();
	}

	public JoystickButton getButton(int buttonNum) {
		return this.buttons[buttonNum];
	}
	
	public void rumbleLeftJoystick(int rumbleValue) {
		this.driveStick.setRumble(RumbleType.kLeftRumble, rumbleValue);
		
	}
	
	public void rumbleRightJoystick(int rumbeValue) {
		this.driveStick.setRumble(RumbleType.kRightRumble, rumbeValue);
	}
}