/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Configs;
import frc.robot.RobotMap;
import frc.robot.commands.driveWithJoystick;

public class Chassis extends Subsystem {
  Configs configs = new Configs();

  private CANSparkMax leftMaster;
  private CANSparkMax leftSlave;
  private CANSparkMax rightMaster;
  private CANSparkMax rightSlave;

  private CANPIDController leftMasterController;
  private CANPIDController rightMasterController;

  private static int pidProfile = 0; // 0 is no PID, 1 is tuned

  public Chassis() {
    
    leftMaster = new CANSparkMax(RobotMap.LEFT_MASTER_SPARK, MotorType.kBrushless);
    leftSlave = new CANSparkMax(RobotMap.LEFT_SLAVE_SPARK, MotorType.kBrushless);
    rightMaster = new CANSparkMax(RobotMap.RIGHT_MASTER_SPARK, MotorType.kBrushless);
    rightSlave = new CANSparkMax(RobotMap.RIGHT_SLAVE_SPARK, MotorType.kBrushless);

    leftMasterController = new CANPIDController(leftMaster);
    rightMasterController = new CANPIDController(rightMaster);

    //leftMasterController.setP(Configs.leftChassis.kP, 1);
    //leftMasterController.setI(Configs.leftChassis.kI, 1);
    //leftMasterController.setD(Configs.leftChassis.kD, 1);

    //rightMasterController.setP(Configs.rightChassis.kP, 1);
    //rightMasterController.setI(Configs.rightChassis.kI, 1);
    //rightMasterController.setD(Configs.rightChassis.kD, 1);

    leftSlave.follow(leftMaster);
    rightSlave.follow(rightMaster);

    leftMaster.setIdleMode(IdleMode.kCoast);
    rightMaster.setIdleMode(IdleMode.kCoast);

    leftMaster.setInverted(true);
    rightMaster.setInverted(true);

  }

  public void publishToSmartDashboard() {
    SmartDashboard.putNumber("Left Velocity", getLeftVelocity());
    SmartDashboard.putNumber("Right Velocity", getRightVelocity());
    SmartDashboard.putNumber("Left Position", getLeftPosition());
    SmartDashboard.putNumber("Right Position", getRightPosition());
    SmartDashboard.putNumber("PID State", pidProfile);
  }

  public void arcadeDrive(double moveValue, double rotateValue) {

    double leftMotorSpeed;
    double rightMotorSpeed;

    moveValue = limit(moveValue);
    rotateValue = limit(rotateValue);

    if (moveValue >= 0.0) {
      moveValue = moveValue * moveValue;
    } else {
      moveValue = -(moveValue * moveValue);
    }
    if (rotateValue >= 0.0) {
      rotateValue = rotateValue * rotateValue;
    } else {
      rotateValue = -(rotateValue * rotateValue);
    }

    if (moveValue > 0.0) {
      if (rotateValue > 0.0) {
        leftMotorSpeed = moveValue - rotateValue;
        rightMotorSpeed = Math.max(moveValue, rotateValue);
      } else {
        leftMotorSpeed = Math.max(moveValue, -rotateValue);
        rightMotorSpeed = moveValue + rotateValue;
      }
    } else {
      if (rotateValue > 0.0) {
        leftMotorSpeed = -Math.max(-moveValue, rotateValue);
        rightMotorSpeed = moveValue + rotateValue;
      } else {
        leftMotorSpeed = moveValue - rotateValue;
        rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
      }
    }

    this.setLeftSpeed(-leftMotorSpeed);
    this.setRightSpeed(rightMotorSpeed);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new driveWithJoystick());
  }

  public void setLeftSpeed(double speed) {
    if (pidProfile == 1) {
      leftMasterController.setReference(speed, ControlType.kVelocity, 1);
    } else {
      leftMasterController.setReference(speed, ControlType.kDutyCycle);
      leftMaster.set(speed);
    }
  }

  public void setRightSpeed(double speed) {
    if (pidProfile == 1) {
      rightMasterController.setReference(speed, ControlType.kVelocity, 1);
    } else {
      rightMaster.set(speed);
    }
  }

  public double getLeftPosition() {
    return leftMaster.getEncoder().getPosition();
  }

  public double getRightPosition() {
    return rightMaster.getEncoder().getPosition();
  }

  public double getLeftVelocity() {
    return leftMaster.getEncoder().getVelocity();

  }

  public double getRightVelocity() {
    return rightMaster.getEncoder().getVelocity();
  }

  public void testAllMotors(double speed) {
    leftMaster.set(speed);
    rightMaster.set(speed);

  }

  public void enablePid() {
    pidProfile = 1;
  }

  public void disablePid() {
    pidProfile = 0;
  }

  private double limit(double val) {
    if (val > 1.0) {
      return 1.0;
    } else if (val < -1.0) {
      return -1.0;
    } else {
      return val;
    }
  }
}