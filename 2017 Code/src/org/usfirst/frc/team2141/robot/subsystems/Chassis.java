package org.usfirst.frc.team2141.robot.subsystems;

import org.usfirst.frc.team2141.robot.OI;
import org.usfirst.frc.team2141.robot.RobotMap;
import org.usfirst.frc.team2141.robot.commands.JoyStickDriving;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Chassis extends Subsystem {

	CANTalon leftMotorA;
	CANTalon leftMotorB;
	CANTalon leftMotorC;
	CANTalon rightMotorA;
	CANTalon rightMotorB;
	CANTalon rightMotorC;
	RobotDrive drive;
	
	AnalogInput leftEncoder;
	AnalogInput rightEncoder;
	
	//Pnuematic Shifter Code
	DoubleSolenoid shifterSolenoid;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.


	public Chassis() {
		leftMotorA = new CANTalon(RobotMap.LEFT_MOTOR_A);
		leftMotorB = new CANTalon(RobotMap.LEFT_MOTOR_B);
		leftMotorC = new CANTalon(RobotMap.LEFT_MOTOR_C);
		rightMotorA = new CANTalon(RobotMap.RIGHT_MOTOR_A);
		rightMotorB = new CANTalon(RobotMap.RIGHT_MOTOR_B);
		rightMotorC = new CANTalon(RobotMap.RIGHT_MOTOR_C);
		
		shifterSolenoid = new DoubleSolenoid(RobotMap.SHIFTER_SOLENOID_CHANNEL_A,RobotMap.SHIFTER_SOLENOID_CHANNEL_B);
		
		leftEncoder = new AnalogInput(RobotMap.LEFT_DRIVE_ENCODER);
		rightEncoder = new AnalogInput(RobotMap.RIGHT_DRIVE_ENCODER);
		
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
    	this.shifterSolenoid.set(DoubleSolenoid.Value.kForward);
    	
    }
    
    public void pushinLeftSolenoid(){
    	this.shifterSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
	
	public void publishToSmartDashboard() {
		SmartDashboard.putNumber("Left Distance Sensor", leftEncoder.getValue());
	}

	public void setDrive(RobotDrive drive) {
		this.drive = drive;
	}
	

	
}
