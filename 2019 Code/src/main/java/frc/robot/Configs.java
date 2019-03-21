/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

/**
 * Config page for all TalonSRX's 
 * @author Bernie
 */

public class Configs {
    public TalonSRXConfiguration elevatorTalonConfig;
    public static final Gains elevatorGains = new Gains(0.0, 0.0, 0.0, 0.0, 0, 0.0); // TODO need to actually calculate
    public static final Gains leftChassis = new Gains(0.005, 0.0, 0.0, 0.0, 0, 0.0);
    public static final Gains rightChassis = new Gains(0.005, 0.0, 0.0, 0.0, 0, 0.0);

    public Configs() {
        elevatorTalonConfig = new TalonSRXConfiguration();

        elevatorTalonConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.QuadEncoder;
        elevatorTalonConfig.primaryPID.selectedFeedbackCoefficient = 1.0;
        elevatorTalonConfig.sum0Term = FeedbackDevice.QuadEncoder;
        elevatorTalonConfig.slot0.kP = elevatorGains.kP;
        elevatorTalonConfig.slot0.kI = elevatorGains.kI;
        elevatorTalonConfig.slot0.kD = elevatorGains.kD;
        elevatorTalonConfig.slot0.kF = elevatorGains.kF;

    }


}
