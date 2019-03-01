/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.elevatorcmds;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class lowerElevator extends Command {
  public lowerElevator() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.elevator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.elevator.setElevatorSpeed(-0.5);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.elevator.setElevatorSpeed(-0.5);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override

  protected boolean isFinished() {

    if (Robot.oi.getButtonValue(7, true)) {

      return false;
    }

    if (Robot.elevator.getBottomSwitch()) {
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
