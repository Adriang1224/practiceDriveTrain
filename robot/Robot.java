// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.DriveTrain;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  public static RobotContainer m_robotContainer;
  public static DriveTrain driveTrain = new DriveTrain();

  private Spark motorLeft1 = new Spark(RobotMap.MOTOR_LEFT_1);
  private Spark motorLeft2 = new Spark(RobotMap.MOTOR_LEFT_2);
  private Spark motorRight1 = new Spark(RobotMap.MOTOR_RIGHT_1);
  private Spark motorRight2 = new Spark(RobotMap.MOTOR_RIGHT_2);

  private double startTime;

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void disabledExit() {}

  @Override
  public void autonomousInit() {
    //Start Timer when game start
    startTime = Timer.getFPGATimestamp();
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {
    // Get current time
    double time = Timer.getFPGATimestamp();
    
    //Subtract time since game start from autonomous time.
    if (time - startTime <  3){
      motorLeft1.set(1);
      motorLeft2.set(1);
      motorRight1.set(1);
      motorRight2.set(1);

  
    } else {
      //Stop robot after 3 seconds of driving forward
      motorLeft1.set(0);
      motorLeft2.set(0);
      motorRight1.set(0);
      motorRight2.set(0);

    }
  }

  @Override
  public void autonomousExit() {}

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void teleopExit() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void testExit() {}
}

