package org.usfirst.frc.team2141.robot.subsystems;

import org.usfirst.frc.team2141.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.time.StopWatch;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Elevator extends Subsystem {
	
	TalonSRX elevatorClimbMotorAlpha;
	TalonSRX elevatorClimbMotorBeta;
	TalonSRX elevatorClimbMotorCharlie;
	
	StopWatch elevatorTimer;
	DigitalInput bottomLimitSwitch;
	DigitalInput upperLimitSwitch;
	AnalogInput proximitySensor;
	AnalogInput pressureSensor;
	DoubleSolenoid elevatorGearboxShifter;
	DoubleSolenoid extraValve;
	Relay compressor;
	
	Encoder elevatorEncoder;
	
	int ampCap = 20;
	boolean highGear;
	boolean currentLimit;
	
	public Elevator() {
		elevatorClimbMotorAlpha = new TalonSRX(RobotMap.ELEVATOR_CLIMB_MOTOR_ALPHA);
		elevatorClimbMotorBeta = new TalonSRX(RobotMap.ELEVATOR_CLIMB_MOTOR_BETA);
		elevatorClimbMotorCharlie = new TalonSRX(RobotMap.ELEVATOR_CLIMB_MOTOR_CHARLIE);
		
		bottomLimitSwitch = new DigitalInput(1);
		upperLimitSwitch = new DigitalInput(0);
		proximitySensor = new AnalogInput(0);
		pressureSensor = new AnalogInput(1);
		compressor = new Relay(RobotMap.COMPRESSOR_RELAY);
		elevatorTimer = new StopWatch();
		elevatorGearboxShifter = new DoubleSolenoid(RobotMap.GEARBOX_SHIFTER_A, RobotMap.GEARBOX_SHIFTER_B);
		extraValve = new DoubleSolenoid(6, 7);
		
		elevatorClimbMotorAlpha.setNeutralMode(NeutralMode.Brake);
		elevatorClimbMotorBeta.setNeutralMode(NeutralMode.Brake);
		elevatorClimbMotorCharlie.setNeutralMode(NeutralMode.Brake);
		
		elevatorClimbMotorBeta.follow(elevatorClimbMotorAlpha);
		elevatorClimbMotorCharlie.follow(elevatorClimbMotorAlpha);
		
		elevatorClimbMotorCharlie.configContinuousCurrentLimit(20, 10);
			

	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public void setPercentOutput(double speed) {
		elevatorClimbMotorAlpha.set(ControlMode.PercentOutput, speed);
	}
	
	public void setVoltage(double speed) {
		elevatorClimbMotorAlpha.set(ControlMode.Current, speed * 12);
	}	
	
	public double getPressure() {
		return 250 * (pressureSensor.getVoltage() / 5) - 25;
	}
	
	public void enableCompressor() {
		compressor.set(Value.kOn);
	}
	
	public void disableCompressor() {
		compressor.set(Value.kOff);
	}
	
	public void enableExtra() {
		extraValve.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void disableExtra() {
		extraValve.set(DoubleSolenoid.Value.kForward);
	}
	
	public void publishToSmartDashboard() {
		SmartDashboard.putBoolean("Gearbox in High", highGear);
		SmartDashboard.putNumber("Elevator Speed", this.elevatorClimbMotorAlpha.getMotorOutputPercent());
		
		SmartDashboard.putBoolean("Current Limiting Elevator", currentLimit);
		SmartDashboard.putNumber("Elevator Timing", elevatorTimer.getDuration());
		
		SmartDashboard.putBoolean("Bottom Limit Switch", getBottomSwitch());
		SmartDashboard.putBoolean("Upper Limit Switch", getUppwerSwitch());
		SmartDashboard.putNumber("Proximity Value", proximitySensor.getVoltage());
		
		SmartDashboard.putNumber("Stored Pressure", getPressure());
	}
	
	public void enableCurrentLimiting() {
		currentLimit = true;
		elevatorClimbMotorCharlie.enableCurrentLimit(currentLimit);
	}
	
	public void disableCurrentLimiting() {
		currentLimit = false	;
		elevatorClimbMotorCharlie.enableCurrentLimit(currentLimit);
	}
	
	public void startElevatorTimer() {
		elevatorTimer.start();
	}
	
	public boolean getBottomSwitch() {
		return bottomLimitSwitch.get();
	}
	
	public boolean getUppwerSwitch() {
		return upperLimitSwitch.get();
	}
	


	public void setToLow() {
		elevatorGearboxShifter.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void setToHigh() {
		elevatorGearboxShifter.set(DoubleSolenoid.Value.kForward);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(null);
    	
    }
}
