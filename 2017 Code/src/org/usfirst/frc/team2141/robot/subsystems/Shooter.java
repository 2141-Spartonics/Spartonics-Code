package org.usfirst.frc.team2141.robot.subsystems;

import org.usfirst.frc.team2141.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {

	private CANTalon shooterMotor;
    private CANTalon feederMotor;


	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public Shooter() {
		shooterMotor = new CANTalon(RobotMap.SHOOTER_MOTOR);
    	feederMotor = new CANTalon(RobotMap.FEEDER_MOTOR);

		
		shooterMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		shooterMotor.configEncoderCodesPerRev(12);
		shooterMotor.configNominalOutputVoltage(0.0, -0.0);
		shooterMotor.configPeakOutputVoltage(12, -12);
		shooterMotor.enableBrakeMode(false);
		
		shooterMotor.setProfile(0);
		shooterMotor.setP(RobotMap.SHOOTER_SPEED_P);
		shooterMotor.setI(RobotMap.SHOOTER_SPEED_I);
		shooterMotor.setD(RobotMap.SHOOTER_SPEED_D);
		shooterMotor.setF(RobotMap.SHOOTER_SPEED_F);

		shooterMotor.changeControlMode(TalonControlMode.Speed);
    	feederMotor.changeControlMode(TalonControlMode.Voltage);
	}

	public void setShooterMotorVoltage(double voltage) {
		shooterMotor.changeControlMode(TalonControlMode.Voltage);
		this.shooterMotor.set(voltage * 12);
	}

	public double getVelocity() {
		return this.shooterMotor.getSpeed();
	}
	
	public void setShooterMotorVelocity(double targetSpeed){
		shooterMotor.changeControlMode(TalonControlMode.Speed);
		this.shooterMotor.set(targetSpeed);
	}
	
    /**
     * Sets the feederMotor to speed
     * @param speed Speed is the speed chosen for the motor to be set to
     */
    public void setFeederSpeed(double speed){
	  this.feederMotor.set(speed * 12);
    }

}
