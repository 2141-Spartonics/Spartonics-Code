/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.DriveWithJoystick;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class Chassis extends Subsystem {

  private Spark rightMotor;
  private Spark rightSlave;
  private Spark leftMotor;
  private Spark leftSlave;

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public Chassis() {
    rightMotor = new Spark(4);
    rightSlave = new Spark(2);
    leftMotor = new Spark(3);
    leftSlave = new Spark(1);

    rightMotor.setInverted(false);
    rightSlave.setInverted(false);
    leftMotor.setInverted(false);
    leftSlave.setInverted(false);

  }

  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new DriveWithJoystick());
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

  public void setLeftSpeed(double speed) {
    leftMotor.set(speed);
    leftSlave.set(speed);
  }

  public void setRightSpeed(double speed) {
    rightMotor.set(speed);
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