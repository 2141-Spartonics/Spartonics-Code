/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class ArduinoInterface extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private SerialPort arduino;
  public Timer timer;

  public String lastMessage = "";
  public double lastAngle = 0.0;

  public ArduinoInterface() {

    timer = new Timer();
    timer.start();

    try {
      for (SerialPort.Port c : SerialPort.Port.values())
        System.out.println(c);
      arduino = new SerialPort(9600, SerialPort.Port.kUSB);
      System.out.println("Connected to Arduino kUSB");

    } catch (Exception e) {
      System.out.println("Failed to initially connect to Arduino, gonna try again!");

      try {
        for (SerialPort.Port c : SerialPort.Port.values())
          System.out.println(c);
        arduino = new SerialPort(9600, SerialPort.Port.kUSB1);
        System.out.println("Connected to Arduino kUSB1");

      } catch (Exception ekusb1) {
        System.out.println("Failed to initially connect to Arduino on kUSB1, gonna try again!");

        try {
          for (SerialPort.Port c : SerialPort.Port.values())
            System.out.println(c);
          arduino = new SerialPort(9600, SerialPort.Port.kUSB2);
          System.out.println("Connected to Arduino");

        } catch (Exception ekusb2) {
          System.out.println("Failed to initially connect to Arduino on kUSB2, gonna try again!");
        }
      }

    }

  }

  public void setSerialPortBuffer(String message) {
    arduino.writeString(message);
  }

  public void publishToSmartDashboard() {
    try {
      lastMessage = getSerialPortBuffer();
      lastAngle = findAngle(lastMessage);
      SmartDashboard.putString("ArduinoData", lastMessage);
      SmartDashboard.putNumber("Angle", lastAngle);
      SmartDashboard.putNumber("Time til next check", 0.2 - timer.get());
      SmartDashboard.putBoolean("Buffer Filled", arduino.getBytesReceived() > 0);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public String getSerialPortBuffer() {
    if (arduino.getBytesReceived() > 0 && timer.get() > 0.2) {
      // System.out.println(arduino.readString());
      timer.reset();
      return arduino.readString();
    } else {
      return lastMessage;
    }
  }

  public void coordsRequest() {
    setSerialPortBuffer("get");
  };

  /**
   * Find angle given string containing vector coordinates
   * 
   * @param arduinoOutput
   * @return
   */
  public double findAngle(String arduinoOutput) {
    // System.out.println(arduinoOutput);
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
      // System.out.println("ArduinoData error, check buffer");
      return lastAngle;
    } finally {
    }

    double differenceX = Math.abs(x2 - x1);
    double differenceY = Math.abs(y2 - y1);
    double Angle = Math.toDegrees(Math.atan((differenceY) / (differenceX)));
    System.out.println(Angle);
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
      // System.out.println("ArduinoData error, check buffer");
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
