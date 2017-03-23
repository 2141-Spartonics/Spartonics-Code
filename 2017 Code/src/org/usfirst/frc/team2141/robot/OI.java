package org.usfirst.frc.team2141.robot;

import org.usfirst.frc.team2141.robot.commands.ForwardIntake;
import org.usfirst.frc.team2141.robot.commands.ReverseIntake;
import org.usfirst.frc.team2141.robot.commands.WinchUp;
import org.usfirst.frc.team2141.robot.commands.WinchDown;
import org.usfirst.frc.team2141.robot.commands.chassis.FlipChassisDirection;
import org.usfirst.frc.team2141.robot.commands.chassis.ShiftDown;
import org.usfirst.frc.team2141.robot.commands.chassis.ShiftUp;
import org.usfirst.frc.team2141.robot.commands.manual.RemoveEncoderInfluence;

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

		auxiliaryStick = new Joystick(RobotMap.AUXILIARY_STICK_NUMBER);
		auxiliaryButtons = new JoystickButton[13];
		
		for(int i = 1; i <= driveButtons.length; i++) {
			driveButtons[i] = new JoystickButton(driveStick, i);
		}
		
		for(int i=1; i <= auxiliaryButtons.length; i++){
			auxiliaryButtons[i] = new JoystickButton(auxiliaryStick, i);
		}

		//this.getButton(RobotMap.SHOOTER_CONTROL_BUTTON).whileHeld(new ShooterControl());
		this.getButton(RobotMap.WINCH_UP_BUTTON).whenPressed(new WinchUp());
		this.getButton(RobotMap.WINCH_DOWN_BUTTON).whileHeld(new WinchDown());
		this.getButton(RobotMap.INTAKE_FORWARD_BUTTON).toggleWhenPressed(new ForwardIntake());
		this.getButton(RobotMap.INTAKE_BACKWARD_BUTTON).whileHeld(new ReverseIntake());		
		this.getButton(RobotMap.REVERSE_DRIVE_BUTTON).whenPressed(new FlipChassisDirection());
		this.getButton(RobotMap.SHIFT_DOWN_BUTTON).whileHeld(new ShiftDown());
		this.getButton(RobotMap.SHIFT_UP_BUTTON).whileHeld(new ShiftUp());
	
		this.getButton(RobotMap.TURN_ENCODERS_OFF, true).toggleWhenPressed(new RemoveEncoderInfluence());
		
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
		return driveStick.getRawAxis(2);
	}
	
	public double getRightY(){
		return driveStick.getRawAxis(3);
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