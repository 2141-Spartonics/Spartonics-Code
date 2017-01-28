package org.usfirst.frc.team2141.robot.subsystems;

import org.usfirst.frc.team2141.robot.OI;
import org.usfirst.frc.team2141.robot.RobotMap;
import org.usfirst.frc.team2141.robot.commands.JoyStickDriving;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Chassis extends Subsystem {

	CANTalon leftMotorA;
	CANTalon leftMotorB;
	CANTalon leftMotorC;
	CANTalon rightMotorA;
	CANTalon rightMotorB;
	CANTalon rightMotorC;
	RobotDrive drive;
	
	//Pnuematic Shifter Code
	DoubleSolenoid shifterSolenoidLeft;
	DoubleSolenoid shifterSolenoidRight;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.


	public Chassis() {
		leftMotorA = new CANTalon(RobotMap.LEFT_MOTOR_A);
		leftMotorB = new CANTalon(RobotMap.LEFT_MOTOR_B);
		leftMotorC = new CANTalon(RobotMap.LEFT_MOTOR_C);
		rightMotorA = new CANTalon(RobotMap.RIGHT_MOTOR_A);
		rightMotorB = new CANTalon(RobotMap.RIGHT_MOTOR_B);
		rightMotorC = new CANTalon(RobotMap.RIGHT_MOTOR_C);
		
		shifterSolenoidLeft = new DoubleSolenoid(RobotMap.SHIFTER_SOLENOID_LEFT_CHANNEL_A,RobotMap.SHIFTER_SOLENOID_LEFT_CHANNEL_B);
		shifterSolenoidRight = new DoubleSolenoid(RobotMap.SHIFTER_SOLENOID_RIGHT_CHANNEL_A,RobotMap.SHIFTER_SOLENOID_RIGHT_CHANNEL_B);
		
		
		
		drive = new RobotDrive(leftMotorA, rightMotorA);
		this.leftMotorB.changeControlMode(CANTalon.TalonControlMode.Follower);
		this.leftMotorB.set(RobotMap.LEFT_MOTOR_A);
		this.leftMotorC.changeControlMode(CANTalon.TalonControlMode.Follower);
		this.leftMotorC.set(RobotMap.LEFT_MOTOR_A);
		this.rightMotorB.changeControlMode(CANTalon.TalonControlMode.Follower);
		this.rightMotorB.set(RobotMap.RIGHT_MOTOR_A);
		this.rightMotorC.changeControlMode(CANTalon.TalonControlMode.Follower);
		this.rightMotorC.set(RobotMap.RIGHT_MOTOR_A);
		this.drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
		this.drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);

	}
	public RobotDrive getDrive() {
		return drive;
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new JoyStickDriving());
	}

	public void driveWithJoystick() {
		drive.arcadeDrive(OI.driveStick);
	}
	
    public void pulloutLeftSolenoid(){
    	this.shifterSolenoidLeft.set(DoubleSolenoid.Value.kForward);
    	
    }
    public void pushinLeftSolenoid(){
    	this.shifterSolenoidLeft.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void pulloutRightSolenoid(){
    	this.shifterSolenoidRight.set(DoubleSolenoid.Value.kForward);
    	
    }
    public void pushinRightSolenoid(){
    	this.shifterSolenoidRight.set(DoubleSolenoid.Value.kReverse);
    }
	
	public void publishToSmartDashboard() {
	}

	public void setDrive(RobotDrive drive) {
		this.drive = drive;
	}
	

	
}
