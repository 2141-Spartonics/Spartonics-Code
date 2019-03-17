/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

  public void publishToSmartDashboard() {
    SmartDashboard.putString("ArduinoData", getSerialPortBuffer());
    SmartDashboard.putNumber("Angle", findAngle(getSerialPortBuffer());
  }

  public String getSerialPortBuffer() {
    try {
      System.out.println(arduino.readString());
      return arduino.readString();
    } catch (Exception e) {
      return "damit, guess what didn't work";
    }
  }

  public void coordsRequest() {
    setSerialPortBuffer("get");
  }

  /**
   * Find angle given string containing vector coordinates
   * 
   * @param arduinoOutput
   * @return
   */
  public double findAngle(String arduinoOutput) {

    // String arduinoOutput = "vector: (34 16) (37 0) index: 2 flags 4";
    double x1, x2, y1, y2;
    x1 = x2 = y1 = y2 = 0;
    try {
      arduinoOutput = arduinoOutput.substring(9);
      x1 = Double.parseDouble(arduinoOutput.substring(0, arduinoOutput.indexOf(" ")));

      arduinoOutput = arduinoOutput.substring(arduinoOutput.indexOf(" ") + 1);
      y1 = Double.parseDouble(arduinoOutput.substring(0, arduinoOutput.indexOf(")")));

      arduinoOutput = arduinoOutput.substring(arduinoOutput.indexOf("(") + 1);
      x2 = Double.parseDouble(arduinoOutput.substring(0, arduinoOutput.indexOf(" ")));

      arduinoOutput = arduinoOutput.substring(arduinoOutput.indexOf(" ") + 1);
      y2 = Double.parseDouble(arduinoOutput.substring(0, arduinoOutput.indexOf(")")));

    } catch (Exception StringIndexOutOfBoundsException) {
      System.out.println("ArduinoData error, check buffer");
    } finally {
    }

    double differenceX = Math.abs(x2 - x1);
    double differenceY = Math.abs(y2 - y1);
    double Angle = Math.toDegrees(Math.atan((differenceY) / (differenceX)));

    return Angle;

  }

  public double findAngle(String arduinoOutput, boolean DEBUG) {
    // String arduinoOutput = "vector: (34 16) (37 0) index: 2 flags 4";
    double x1, x2, y1, y2;
    x1 = x2 = y1 = y2 = 0;
    try {
      arduinoOutput = arduinoOutput.substring(9);
      x1 = Double.parseDouble(arduinoOutput.substring(0, arduinoOutput.indexOf(" ")));

      arduinoOutput = arduinoOutput.substring(arduinoOutput.indexOf(" ") + 1);
      y1 = Double.parseDouble(arduinoOutput.substring(0, arduinoOutput.indexOf(")")));

      arduinoOutput = arduinoOutput.substring(arduinoOutput.indexOf("(") + 1);
      x2 = Double.parseDouble(arduinoOutput.substring(0, arduinoOutput.indexOf(" ")));

      arduinoOutput = arduinoOutput.substring(arduinoOutput.indexOf(" ") + 1);
      y2 = Double.parseDouble(arduinoOutput.substring(0, arduinoOutput.indexOf(")")));

    } catch (Exception StringIndexOutOfBoundsException) {
      System.out.println("ArduinoData error, check buffer");
    } finally {
      System.out.println("Arduino data parse: " + x1 + " " + y1 + " " + x2 + " " + y2);
    }

    double differenceX = Math.abs(x2 - x1);
    double differenceY = Math.abs(y2 - y1);
    double Angle = Math.toDegrees(Math.atan((differenceY) / (differenceX)));

    return Angle;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
