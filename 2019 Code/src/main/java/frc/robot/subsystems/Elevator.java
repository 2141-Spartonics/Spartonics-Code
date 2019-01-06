/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Configs;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  Configs configs = new Configs();

  private TalonSRX masterElevatorMotor;
  private TalonSRX slaveElevatorMotorOne;
  private TalonSRX slaveElevatorMotorTwo;

  private int PIDProfile = 1;
  private boolean usingPid = true;
  private int pidTimout = 10;

  public Elevator() {
    masterElevatorMotor = new TalonSRX(RobotMap.MASTER_ELEVATOR_TALON);
    slaveElevatorMotorOne = new TalonSRX(RobotMap.SLAVE_ELEVATOR_TALON_ONE);
    slaveElevatorMotorTwo = new TalonSRX(RobotMap.SLAVE_ELEVATOR_TALON_TWO);

    masterElevatorMotor.configFactoryDefault();
    slaveElevatorMotorOne.configFactoryDefault();
    slaveElevatorMotorTwo.configFactoryDefault();

    masterElevatorMotor.setInverted(InvertType.None);
    masterElevatorMotor.setNeutralMode(NeutralMode.Brake);
    masterElevatorMotor.setSensorPhase(true);

    masterElevatorMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, PIDProfile, pidTimout);
    masterElevatorMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, pidTimout, 0);
    masterElevatorMotor.configAllowableClosedloopError(PIDProfile, 0);
    masterElevatorMotor.configAllSettings(configs.elevatorTalonConfig);

    slaveElevatorMotorOne.follow(masterElevatorMotor, FollowerType.PercentOutput);
    slaveElevatorMotorTwo.follow(masterElevatorMotor, FollowerType.PercentOutput);

    slaveElevatorMotorOne.setInverted(InvertType.FollowMaster);
    slaveElevatorMotorTwo.setInverted(InvertType.FollowMaster);
    // TODO figure out difference between percent output and AuxOutput1

  }

  public void publishToSmartDashboard() {
    //TODO have fun flushing this mess 
  }

  public void setElevatorSpeed(double speed) {
    if (usingPid)
      masterElevatorMotor.set(ControlMode.Velocity, speed);
    else
      masterElevatorMotor.set(ControlMode.PercentOutput, speed);
  }

  public void enablePid() {
    usingPid = true;
  }

  public void disablePid() {
    usingPid = false;
  }

  public double getRawElevatorPosition() {
    return masterElevatorMotor.getSelectedSensorPosition();
  }

  public void setElevatorPosition(double position) {
    masterElevatorMotor.set(ControlMode.Position, position);
  }

  public void setElevatorPosition(double position, boolean useMagic) {
    // TODO masterElevatorMotor.set(ControlMode.motion)
  }

  public void changeElevatorPosition(double deltaHeight) {
    masterElevatorMotor.set(ControlMode.Position, deltaHeight + getRawElevatorPosition());
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

}
