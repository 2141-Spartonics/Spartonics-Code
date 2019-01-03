package org.usfirst.frc.team2141.robot.commands.autonomousGroups;

import org.usfirst.frc.team2141.robot.autonomousCommands.DriveAtVelocity;
import org.usfirst.frc.team2141.robot.autonomousCommands.DriveDistance;
import org.usfirst.frc.team2141.robot.autonomousCommands.TurnDegrees;
import org.usfirst.frc.team2141.robot.commands.Elevator_Raise;
import org.usfirst.frc.team2141.robot.commands.Intake_Close;
import org.usfirst.frc.team2141.robot.commands.Intake_Open;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class A_RightSwitch extends CommandGroup {

	public A_RightSwitch() {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.

		addSequential(new Intake_Close());
		
		addSequential(new Elevator_Raise());

		addSequential(new DriveDistance(95));
						
		//addSequential(new TurnDegrees(90, 0.5));
				
		addSequential(new Intake_Open());
		
		////
		//addSequential(new DriveDistance(25));
		
		//addSequential(new Intake_Open());
	}
}
