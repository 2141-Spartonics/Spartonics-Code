/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.driveWithJoystick;

public class Chassis extends Subsystem {

  private Spark leftMaster;
  private Spark leftSlave;
  private Spark rightMaster;
  private Spark rightSlave;

  private static int pidProfile = 1; // 0 is no PID, 1 is tuned

  public Chassis() {
    leftMaster = new Spark(RobotMap.LEFT_MASTER_SPARK);
    leftSlave = new Spark(RobotMap.LEFT_SLAVE_SPARK);
    rightMaster = new Spark(RobotMap.RIGHT_MASTER_SPARK);
    rightSlave = new Spark(RobotMap.RIGHT_SLAVE_SPARK);

    // TODO Configure motors when SparkMax api comes out

  }

  public void publishToSmartDashboard() {
    // TODO Setup for debugging
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
    } else {
      leftMaster.set(speed); // TODO Check against controlmode.kpercentoutput
    }
  }

  public void setRightSpeed(double speed) {
    if (pidProfile == 1) {

    } else {
      rightMaster.set(speed); // TODO Check against controlmode.kpercentoutput
    }
  }

  public int getLeftPosition() {
    // TODO
    return null;
  }

  public int getRightPosition() {
    // TODO
    return null;
  } 

  public int getAveragePosition() {
    return (getLeftPosition() + getRightPosition()) / 2;
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