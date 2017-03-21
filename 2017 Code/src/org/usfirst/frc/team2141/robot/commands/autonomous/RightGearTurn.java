package org.usfirst.frc.team2141.robot.commands.autonomous;

import org.usfirst.frc.team2141.robot.commands.chassis.DriveStraight;
import org.usfirst.frc.team2141.robot.commands.chassis.TurnDegrees;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightGearTurn extends CommandGroup {

    public RightGearTurn() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	addSequential(new DriveStraight(100, .5));
    	addSequential(new TurnDegrees(60, .3));
    	addSequential(new DriveStraight(100, .5));
    	
    }
}
