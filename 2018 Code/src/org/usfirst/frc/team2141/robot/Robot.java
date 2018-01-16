
package org.usfirst.frc.team2141.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2141.robot.commands.DriveWithJoystick;

import org.usfirst.frc.team2141.robot.subsystems.Chassis;
import org.usfirst.frc.team2141.robot.subsystems.Elevator_Climber;
import org.usfirst.frc.team2141.robot.subsystems.Elevator_Intake;
import org.usfirst.frc.team2141.robot.subsystems.Intake;

import com.analog.adis16448.frc.ADIS16448_IMU;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static Elevator_Climber elevator_climber;
	public static Elevator_Intake elevator_intake;
	public static Chassis chassis;
	public static Intake intake;
	
	PowerDistributionPanel pdp;
    ADIS16448_IMU imu;

	public static OI oi;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		elevator_climber = new Elevator_Climber();
		elevator_intake = new Elevator_Intake();
		chassis = new Chassis();
		intake = new Intake();
		
		imu = new ADIS16448_IMU();
		pdp = new PowerDistributionPanel();

		oi = new OI();
		chooser.addDefault("Default Auto", new DriveWithJoystick());
		SmartDashboard.putData("Auto mode", chooser);
		
		chassis.publishToSmartDashboard();
		intake.publishToSmartDashboard();
	
	}
	
	public void publishToSmartDashboard(){
		chassis.publishToSmartDashboard();
		intake.publishToSmartDashboard();
		SmartDashboard.putData(imu);
		SmartDashboard.putData(pdp);
		
		SmartDashboard.putNumber("Angle", imu.getYaw());
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		chassis.zeroEncoders();
		chassis.setPIDProfile(0);
		
		intake.openintake();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		publishToSmartDashboard();
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
