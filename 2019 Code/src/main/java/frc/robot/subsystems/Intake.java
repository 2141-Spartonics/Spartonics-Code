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
 * Intake subsytem includes linear thruster solenoid and any relative sensors
 * @author Bernie Conrad
 * @version 1/7/19
 */
public class Intake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  // TODO sensor for object
  DoubleSolenoid intake;

  public Intake() {
    intake = new DoubleSolenoid(RobotMap.INTAKE_CHANNEL_A, RobotMap.INTAKE_CHANNEL_B);

  }

  /**
   * Publishes state of intake to smartDashboard
   */
  public void publishToSmartDashboard() {
    SmartDashboard.putBoolean("Intake Closed", getIntakeClosed());
  }

  /**
   * Gets state of the intake 
   * @return true if the intake is closed
   */
  public boolean getIntakeClosed() {
    if (intake.get() == Value.kForward)
      return true;
    else
      return false;
  }

  /**
   * closes the intake
   */
  public void closeIntake() {
    intake.set(Value.kForward);
  }

  /**
   * Opens the intake
   */
  public void openIntake() {
    intake.set(Value.kReverse);
  }

  /**
   * Sets default command for the intake subsystem to be null
   */
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(null);
  }
}
 