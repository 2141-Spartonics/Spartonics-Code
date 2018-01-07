package org.usfirst.frc.team2141.robot;


import org.usfirst.frc.team2141.robot.commands.rawControlArm;

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
		
		for(int i = 1; i <= driveButtons.length - 1; i++) {
			driveButtons[i] = new JoystickButton(driveStick, i);
		}
		
		for(int i=1; i <= auxiliaryButtons.length - 1; i++){
			auxiliaryButtons[i] = new JoystickButton(auxiliaryStick, i);
		}

		//this.getButton(RobotMap.SHOOTER_CONTROL_BUTTON).whileHeld(new ShooterControl());
		this.getButton(4).whileActive(new rawControlArm());
		
	}
	
	public Joystick getDriveStick() {
		return driveStick;
	}

	public double getLeftX(){
		return driveStick.getRawAxis(0);
	}
	
	
	public double getLeftY(){
		return driveStick.getRawAxis(1);
	}
	
	public double getRightX(){
		return driveStick.getRawAxis(4);
	}
	
	public double getRightY(){
		return driveStick.getRawAxis(5);
	}
	
	public boolean getButtonValue(int buttonNum) {
		return this.driveButtons[buttonNum].get();
	}

	public boolean getButtonValue(int buttonNum, boolean auxiliary) {
		if(auxiliary){
			return this.auxiliaryButtons[buttonNum].get();
		}else{		
			return this.driveButtons[buttonNum].get();
		}
	}
	public JoystickButton getButton(int buttonNum) {
		return this.driveButtons[buttonNum];
	}
	
	public JoystickButton getButton(int buttonNum, boolean auxiliary) {
		if(auxiliary){
			return this.auxiliaryButtons[buttonNum];
		}else{
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