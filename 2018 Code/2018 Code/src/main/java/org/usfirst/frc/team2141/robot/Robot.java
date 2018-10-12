
package org.usfirst.frc.team2141.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2141.robot.commands.Chassis_DriveWithJoystick;
import org.usfirst.frc.team2141.robot.commands.Compressor_Enable;
import org.usfirst.frc.team2141.robot.commands.autonomousGroups.A_DriveStraight;
import org.usfirst.frc.team2141.robot.commands.autonomousGroups.A_LeftSwitch;
import org.usfirst.frc.team2141.robot.commands.autonomousGroups.A_RightSwitch;
import org.usfirst.frc.team2141.robot.subsystems.Chassis;
import org.usfirst.frc.team2141.robot.subsystems.Elevator;
import org.usfirst.frc.team2141.robot.subsystems.Pneumatics;

import com.analog.adis16448.ADIS16448_IMU;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static Elevator elevator;
	public static Chassis chassis;
	public static Pneumatics pneumatics;

	public static ADIS16448_IMU imu;
	public static DriverStation driverStation;

	public static OI oi;
	
	public String fieldData = "";

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		elevator = new Elevator();
		chassis = new Chassis();
		pneumatics = new Pneumatics();

		imu = new ADIS16448_IMU();

		oi = new OI();
		
		pneumatics.openintake();

		//chooser.addDefault("Ignore Switch", new A_DriveStraight());
		//SmartDashboard.putData("Auto mode", chooser);

	}

	public void publishToSmartDashboard() {
		chassis.publishToSmartDashboard();
		elevator.publishToSmartDashboard();
		pneumatics.publishToSmartDashBoard();

		SmartDashboard.putNumber("Gyro-X", imu.getAngleX());
		SmartDashboard.putNumber("Gyro-Y", imu.getAngleY());
		SmartDashboard.putNumber("Gyro-Z", imu.getAngleZ());

		SmartDashboard.putNumber("Accel-X", imu.getAccelX());
		SmartDashboard.putNumber("Accel-Y", imu.getAccelY());
		SmartDashboard.putNumber("Accel-Z", imu.getAccelZ());

		SmartDashboard.putNumber("Pitch", imu.getPitch());
		SmartDashboard.putNumber("Roll", imu.getRoll());
		SmartDashboard.putNumber("Yaw", imu.getYaw());

		SmartDashboard.putNumber("Pressure: ", imu.getBarometricPressure());
		SmartDashboard.putNumber("Temperature: ", imu.getTemperature());
				
		SmartDashboard.putString("Field Data", fieldData);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
	 */
	@Override
	public void disabledInit() {
		Robot.chassis.resetEncoders();
	}

	@Override
	public void disabledPeriodic() {
		Robot.chassis.resetEncoders();
	}


	@Override
	public void autonomousInit() {
		//autonomousCommand = chooser.getSelected();

		fieldData = DriverStation.getInstance().getGameSpecificMessage();
		if (fieldData.length() > 0) {
			if (fieldData.charAt(0) == 'R') {
				autonomousCommand = new A_DriveStraight();
			} else {
				autonomousCommand = new A_DriveStraight();
			}
			if (fieldData.length() > 0) {
				autonomousCommand.start();
			}
		}
	}

	@Override 
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		publishToSmartDashboard();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		chassis.resetEncoders();
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
