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
public class Elevator_Climber extends Subsystem {
	
	TalonSRX elevatorClimbMotor;
	
	public Elevator_Climber() {
		elevatorClimbMotor = new TalonSRX(RobotMap.ELEVATOR_CLIMB_MOTOR);
		
		elevatorClimbMotor.setNeutralMode(NeutralMode.Brake);
		elevatorClimbMotor.setSensorPhase(true);
		elevatorClimbMotor.setInverted(true);
		elevatorClimbMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, 10);
		elevatorClimbMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		
		int absoluteLeftPosition = elevatorClimbMotor.getSelectedSensorPosition(10) & 0xFFF;
		elevatorClimbMotor.setSelectedSensorPosition(absoluteLeftPosition, 0, 10);
		
		elevatorClimbMotor.configNominalOutputForward(0, 10);
		elevatorClimbMotor.configNominalOutputReverse(0, 10);
		elevatorClimbMotor.configPeakOutputForward(1, 10);
		elevatorClimbMotor.configPeakOutputReverse(-1, 10);
		
		elevatorClimbMotor.configAllowableClosedloopError(0, 0, 10); 
		elevatorClimbMotor.config_kP(0, 0.1, 10);
		elevatorClimbMotor.config_kI(0, 0, 10);
		elevatorClimbMotor.config_kD(0, 0, 10);
		elevatorClimbMotor.config_kF(0, 0, 10);
        elevatorClimbMotor.selectProfileSlot(0, 0);

	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public void zeroEncoders() {
		elevatorClimbMotor.setSelectedSensorPosition(0, 0, 10);
	}
	
	public void setElevatorVelocity(double velocity) {
		this.elevatorClimbMotor.set(ControlMode.Velocity, velocity* 4096 * 500 / 600);	
	
	}
	
	public void setLeftMotorSpeed(double speed) {
		this.elevatorClimbMotor.set(ControlMode.PercentOutput, speed);	
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(null);
    	
    }
}
