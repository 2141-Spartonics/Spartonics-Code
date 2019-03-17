/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class ArduinoInterface extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public SerialPort arduino;

  public ArduinoInterface() {
    arduino = new SerialPort(9600, SerialPort.Port.kUSB);

  }

  public void setSerialPortBuffer(String message) {
    arduino.writeString(message);
  }

  public String getSerialPortBuffer() {
    System.out.println(arduino.readString());
    return arduino.readString();
  }

  public void coordsRequest() {
    setSerialPortBuffer("get");
  }

  /**
   * Find angle given string containing vector coordinates
   * @param arduinoOutput
   * @return 
   */
  public double findAngle(String arduinoOutput) {
    double angleFindX1 = Double.parseDouble((arduinoOutput.substring(9, 10)));
    double angleFindY1 = Double.parseDouble(arduinoOutput.substring(12, 13));
    double angleFindX2 = Double.parseDouble(arduinoOutput.substring(17, 18));
    double angleFindY2 = Double.parseDouble(arduinoOutput.substring(20, 21));

    double differenceX = Math.abs(angleFindX2 - angleFindX1);
    double differenceY = Math.abs(angleFindY2 - angleFindY1);
    double Angle = Math.toDegrees(Math.atan((differenceY) / (differenceX)));

    return Angle;

  }

  public double getAngleFromArduino() {
    String arduinoData = getSerialPortBuffer();
    
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
