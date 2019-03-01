/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
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
  Spark leftIntakeMotor;
  Spark rightIntakeMotor;

  DoubleSolenoid hatchIntake;
  

  public Intake() {
    leftIntakeMotor = new Spark(RobotMap.LEFT_INTAKE_MOTOR);
    rightIntakeMotor = new Spark(RobotMap.RIGHT_INTAKE_MOTOR);

    hatchIntake = new DoubleSolenoid(RobotMap.HATCH_INTAKE_SOLENOID_A, RobotMap.HATCH_INTAKE_SOLENOID_B);

  }

  /**
   * Publishes state of intake to smartDashboard
   */
  public void publishToSmartDashboard() {
  }

  public void setIntakeMotorsSpeed(double speed) {
    leftIntakeMotor.set(speed);
    rightIntakeMotor.set(-speed);
  }

  public void extendHatchIntake() {
    hatchIntake.set(Value.kForward);
  }

  public void retractHatchIntake() {
    hatchIntake.set(Value.kReverse);
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
 