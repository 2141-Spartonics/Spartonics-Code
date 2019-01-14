/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.DriveWithJoystick;

public class Chassis extends Subsystem {

  private Spark leftMaster;
  private Spark leftSlave;
  private Spark rightMaster;
  private Spark rightSlave;


  public Chassis() {
    leftMaster = new Spark(0);
    leftSlave = new Spark(1);
    rightMaster = new Spark(2);
    rightSlave = new Spark(3);


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
    setDefaultCommand(new DriveWithJoystick());
  }

  public void setLeftSpeed(double speed) {
    leftMaster.set(speed);
    leftSlave.set(speed);
  }

  public void setRightSpeed(double speed) {
    rightMaster.set(speed);
    rightSlave.set(speed);
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