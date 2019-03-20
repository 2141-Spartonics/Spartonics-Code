/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
  public TalonSRXConfiguration elevatorTalonConfig;
  /// private TalonSRX slaveElevatorMotorOne;
  // private TalonSRX slaveElevatorMotorTwo;
  private int PIDProfile = 1;
  private int pidTimout = 10;

  DigitalInput bottomLimitSwitch;
  DigitalInput upperLimitSwitch;

  public Elevator() {
    try {
    masterElevatorMotor = new TalonSRX(RobotMap.MASTER_ELEVATOR_TALON);
    } catch (Exception e) {
      System.out.println("Talon Error: " + e);
    }

    bottomLimitSwitch = new DigitalInput(RobotMap.ELEVATOR_BOTTOM_LIMIT_SWITCH);
    upperLimitSwitch = new DigitalInput(RobotMap.ELEVATOR_UPPER_LIMIT_SWITCH);

    TalonSRXConfiguration elevatorTalonConfig = new TalonSRXConfiguration();
    masterElevatorMotor.setNeutralMode(NeutralMode.Brake);

    elevatorTalonConfig.slot0.kP = Configs.elevatorGains.kP;
    elevatorTalonConfig.slot0.kI = Configs.elevatorGains.kI;
    elevatorTalonConfig.slot0.kD = Configs.elevatorGains.kD;

    masterElevatorMotor.configAllSettings(elevatorTalonConfig);
    masterElevatorMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, PIDProfile, pidTimout);

  }

  public void publishToSmartDashboard() {
    SmartDashboard.putNumber("Elevator Speed", getElevatorSpeed());
    SmartDashboard.putBoolean("Bottom Limit Switch", getBottomSwitch());
    SmartDashboard.putBoolean("Upper Limit Switch", getUppwerSwitch());
  }

  public void setElevatorSpeed(double speed) {
    if (Math.abs(speed) > 1)
      speed = speed / Math.abs(speed);
    masterElevatorMotor.set(ControlMode.PercentOutput, speed);
  }

  public double getElevatorSpeed() {
    return masterElevatorMotor.getSelectedSensorVelocity();
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

  public boolean getBottomSwitch() {
    return !bottomLimitSwitch.get();
  }

  public boolean getUppwerSwitch() {
    return !upperLimitSwitch.get();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(null);
  }

}
