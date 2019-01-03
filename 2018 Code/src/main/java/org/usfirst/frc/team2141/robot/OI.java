package org.usfirst.frc.team2141.robot;

import org.usfirst.frc.team2141.robot.commands.Intake_Close;
import org.usfirst.frc.team2141.robot.commands.Intake_Open;
import org.usfirst.frc.team2141.robot.commands.Elevator_Lower;
import org.usfirst.frc.team2141.robot.commands.Elevator_Raise;
import org.usfirst.frc.team2141.robot.commands.CurrentLimiting_Disable;
import org.usfirst.frc.team2141.robot.commands.Compressor_Enable;
import org.usfirst.frc.team2141.robot.commands.CurrentLimiting_Enable;
import org.usfirst.frc.team2141.robot.commands.Elevator_ShiftUp;
import org.usfirst.frc.team2141.robot.commands.Elevator_StickControl;
import org.usfirst.frc.team2141.robot.commands.Elevator_ShiftDown;
import org.usfirst.frc.team2141.robot.autonomousCommands.DriveAtVelocity;
import org.usfirst.frc.team2141.robot.autonomousCommands.DriveDistance;
import org.usfirst.frc.team2141.robot.commands.Chassis_DriveAtSpeed;
import org.usfirst.frc.team2141.robot.commands.Chassis_TestLeft;
import org.usfirst.frc.team2141.robot.commands.Chassis_TestRight;
import org.usfirst.frc.team2141.robot.commands.Elevator_Stop;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	private Joystick driveStick;
	private JoystickButton[] driveButtons;
	private Joystick auxiliaryStick;
	private JoystickButton[] auxiliaryButtons;

	public OI() {

		driveStick = new Joystick(RobotMap.DRIVE_STICK_NUMBER);
		driveButtons = new JoystickButton[13];

		auxiliaryStick = new Joystick(RobotMap.AUXILLIARY_STICK_NUMBER);
		auxiliaryButtons = new JoystickButton[13];

		for (int i = 1; i <= driveButtons.length - 1; i++) {
			driveButtons[i] = new JoystickButton(driveStick, i);
		}

		for (int i = 1; i <= auxiliaryButtons.length - 1; i++) {
			auxiliaryButtons[i] = new JoystickButton(auxiliaryStick, i);
		}

		// this.getButton(RobotMap.SHOOTER_CONTROL_BUTTON).whileHeld(new
		// ShooterControl());

		// this.getButton(10, true).whenPressed(new DriveStraight(4 * Math.PI));
		// this.getButton(11, true).whileHeld(new DriveAtVelocity(0.25));
		// this.getButton(8, true).whenPressed(new Chassis_TestLeft());
		// this.getButton(9, true).whenPressed(new Chassis_TestRight());
		// this.getButton(3).whenPressed(new DriveAtSpeed());

		//getButton(5).whileHeld(new Elevator_Raise());
		//getButton(6).whileHeld(new Elevator_Lower());
		//getButton(2).whenPressed(new Intake_Close());
		//getButton(3).whenPressed(new Intake_Open());
		//getButton(1).whileHeld(new Chassis_DriveAtSpeed());
		// this.getButton(10, true).whenPressed(new CurrentLimiting_Enable());
		// this.getButton(10).whenPressed(new CurrentLimiting_Disable());

		getButton(8, true).whenPressed(new Elevator_ShiftUp());
		getButton(9, true).whenPressed(new Elevator_ShiftDown());
		getButton(4, true).whenPressed(new Intake_Open());
		//getButton(1, true).whenPressed(new Intake_Close());
		getButton(5, true).whenPressed(new Intake_Open());
		getButton(3, true).whileHeld(new Elevator_Raise());
		getButton(2, true).whileHeld(new Elevator_Lower());
		
		getButton(10, true).whileHeld(new Compressor_Enable());
		//getButton(8, true).whileHeld(new DriveAtVelocity(0.5));
			 
		//getButton(1).whileHeld(new Chassis_TestLeft(-0.15));
		//getButton(1).whileHeld(new Chassis_TestRight(-0.15));
		
		getButton(2).whileHeld(new Chassis_TestLeft(0.15));
		getButton(2).whileHeld(new Chassis_TestRight(-0.15));
		
		getButton(3).whileHeld(new Chassis_TestLeft(-0.15));
		getButton(4).whileHeld(new Chassis_TestRight(0.15));
				
		// this.getButton(10, true).whenPressed(new Extra_Close());
		// this.getButton(11, true).whenPressed(new ExtraValve_Open());

	}

	public Joystick getDriveStick() {
		return driveStick;
	}

	public double getLeftX() {
		return driveStick.getRawAxis(0);
	}

	public double getLeftY() {
		return driveStick.getRawAxis(1);
	}

	public double getRightX() {
		return driveStick.getRawAxis(4);
	}

	public double getRightY() {
		return driveStick.getRawAxis(5);
	}

	public double getExtraY() {
		return auxiliaryStick.getRawAxis(1);
	}

	public boolean getButtonValue(int buttonNum) {
		return this.driveButtons[buttonNum].get();
	}

	public boolean getButtonValue(int buttonNum, boolean auxiliary) {
		if (auxiliary) {
			return this.auxiliaryButtons[buttonNum].get();
		} else {
			return this.driveButtons[buttonNum].get();
		}
	}

	public JoystickButton getButton(int buttonNum) {
		return this.driveButtons[buttonNum];
	}

	public JoystickButton getButton(int buttonNum, boolean auxiliary) {
		if (auxiliary) {
			return this.auxiliaryButtons[buttonNum];
		} else {
			return this.driveButtons[buttonNum];
		}
	}

	public void rumbleLeftJoystick(int rumbleValue) {
		this.driveStick.setRumble(RumbleType.kLeftRumble, rumbleValue);
	}

	public void rumbleRightJoystick(int rumbeValue) {
		this.driveStick.setRumble(RumbleType.kRightRumble, rumbeValue);
	}
}