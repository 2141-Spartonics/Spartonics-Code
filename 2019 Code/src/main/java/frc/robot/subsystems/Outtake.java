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
 * Add your docs here.
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

  public void publishToSmartDashboard() {
    SmartDashboard.putBoolean("Outtake Extended", getOuttakeExtended());
    SmartDashboard.putBoolean("Outtake Error State", getOuttakeErrorState());
  }

  public boolean getOuttakeExtended() {
    if (rightOuttakePiston.get() == Value.kForward & leftOuttakePiston.get() == Value.kForward) {
      return true;
    } else if (rightOuttakePiston.get() == Value.kReverse & leftOuttakePiston.get() == Value.kReverse) {
      return false;
    } else {
      return false;
    }
  }

  public boolean getOuttakeErrorState() {
    if (rightOuttakePiston.get() == leftOuttakePiston.get()) {
      return false;
    } else {
      return true;
    }
  }

  public void extendOuttake() {
    leftOuttakePiston.set(Value.kForward);
    rightOuttakePiston.set(Value.kForward);
  }

  public void closeOuttake() {
    leftOuttakePiston.set(Value.kReverse);
    rightOuttakePiston.set(Value.kReverse);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(null);
  }
}
