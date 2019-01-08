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
public class Intake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  DoubleSolenoid intake;

  public Intake() {
    intake = new DoubleSolenoid(RobotMap.INTAKE_CHANNEL_A, RobotMap.INTAKE_CHANNEL_B);

  }

  public void publishToSmartDashboard() {
    SmartDashboard.putBoolean("Intake Closed", getIntakeClosed());
  }

  public boolean getIntakeClosed() {
    if (intake.get() == Value.kForward)
      return true;
    else
      return false;
  }

  public void closeIntake() {
    intake.set(Value.kForward);
  }

  public void openIntake() {
    intake.set(Value.kReverse);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(null);
  }
}
