package org.usfirst.frc.team2141.robot.subsystems;

import org.usfirst.frc.team2141.robot.RobotMap;
import org.usfirst.frc.team2141.robot.commands.Climber_Control;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator_Intake extends Subsystem {
	
	TalonSRX elevatorIntakeMotor;
	
	public Elevator_Intake() {
		elevatorIntakeMotor = new TalonSRX(RobotMap.ELEVATOR_CLIMB_MOTOR);
		
		elevatorIntakeMotor.setNeutralMode(NeutralMode.Brake);
		elevatorIntakeMotor.setSensorPhase(true);
		elevatorIntakeMotor.setInverted(true);
		elevatorIntakeMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, 10);
		elevatorIntakeMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		
		int absoluteLeftPosition = elevatorIntakeMotor.getSelectedSensorPosition(10) & 0xFFF;
		elevatorIntakeMotor.setSelectedSensorPosition(absoluteLeftPosition, 0, 10);
		
		elevatorIntakeMotor.configNominalOutputForward(0, 10);
		elevatorIntakeMotor.configNominalOutputReverse(0, 10);
		elevatorIntakeMotor.configPeakOutputForward(1, 10);
		elevatorIntakeMotor.configPeakOutputReverse(-1, 10);
		
		elevatorIntakeMotor.configAllowableClosedloopError(0, 0, 10); 
		elevatorIntakeMotor.config_kP(0, 0.1, 10);
		elevatorIntakeMotor.config_kI(0, 0, 10);
		elevatorIntakeMotor.config_kD(0, 0, 10);
		elevatorIntakeMotor.config_kF(0, 0, 10);
        elevatorIntakeMotor.selectProfileSlot(0, 0);

	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public void zeroEncoders() {
		elevatorIntakeMotor.setSelectedSensorPosition(0, 0, 10);
	}
	
	public void setElevatorVelocity(double velocity) {
		this.elevatorIntakeMotor.set(ControlMode.Velocity, velocity* 4096 * 500 / 600);	
	
	}
	
	public void setLeftMotorSpeed(double speed) {
		this.elevatorIntakeMotor.set(ControlMode.PercentOutput, speed);	
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(null);
    	
    }
}
