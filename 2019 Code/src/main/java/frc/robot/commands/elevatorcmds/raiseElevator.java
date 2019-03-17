/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.elevatorcmds;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class raiseElevator extends Command {
  double speed;
  
  public raiseElevator(double speed) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.elevator);
    this.speed = speed;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.elevator.setElevatorSpeed(speed);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.elevator.setElevatorSpeed(speed);
  }

  protected boolean isFinished() {
    if (Robot.oi.getButtonValue(7, true)) {
      return false;
    }

    if (Robot.elevator.getUppwerSwitch()) {
      return true;
    } else {
      return false;
    }

  }

  // Called once after isFinished returns true
  protected void end() {
    Robot.elevator.setElevatorSpeed(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
  }

}
