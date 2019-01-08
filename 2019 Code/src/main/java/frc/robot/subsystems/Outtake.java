/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

/**
 * Outtake subsytem, includes both pistons used for outtake a hatch panel and possibly a cargo ball 
 * 
 * @author Bernie Conrad
 * @version 1/7/19
 */
public class Outtake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  DoubleSolenoid leftOuttakePiston;
  DoubleSolenoid rightOuttakePiston;

  public Outtake() {
    leftOuttakePiston = new DoubleSolenoid(RobotMap.LEFT_OUTTAKE_PISTON_CHANNEL_A,
        RobotMap.LEFT_OUTTAKE_PISTON_CHANNEL_B);
    rightOuttakePiston = new DoubleSolenoid(RobotMap.RIGHT_OUTTAKE_PISTON_CHANNEL_A,
        RobotMap.RIGHT_OUTTAKE_PISTON_CHANNEL_B);
  }

  /**
   * Publishes outtake data to the smart dashboard
   */
  public void publishToSmartDashboard() {
    SmartDashboard.putBoolean("Outtake Extended", getOuttakeExtended());
    SmartDashboard.putBoolean("Outtake Error State", getOuttakeErrorState());
  }

  /**
   * Determines whether or not the outtake is currently active
   * @return true if the outtake is extended
   */
  public boolean getOuttakeExtended() {
    if (rightOuttakePiston.get() == Value.kForward & leftOuttakePiston.get() == Value.kForward) {
      return true;
    } else if (rightOuttakePiston.get() == Value.kReverse & leftOuttakePiston.get() == Value.kReverse) {
      return false;
    } else {
      return false;
    }
  }

  /**
   * Determines whether the outtake pistons are out of sync
   * @return true if outtake piston's don't share the same state
   */
  public boolean getOuttakeErrorState() {
    if (rightOuttakePiston.get() == leftOuttakePiston.get()) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * Extends both outtake pistons
   */
  public void extendOuttake() {
    leftOuttakePiston.set(Value.kForward);
    rightOuttakePiston.set(Value.kForward);
  }

  /**
   * Reverses both outtake pistons
   */
  public void closeOuttake() {
    leftOuttakePiston.set(Value.kReverse);
    rightOuttakePiston.set(Value.kReverse);
  }

  /**
   * Sets default command of outtake subsystem to null
   */
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(null);
  }

}
