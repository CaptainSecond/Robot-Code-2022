// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {
  /** Creates a new DriveSubsystem. */
  PWMTalonSRX LMotor;
  PWMTalonSRX RMotor;
  DifferentialDrive m_drive;

  // The gyro sensor
  private AnalogGyro m_gyro = new AnalogGyro(1);

  public DriveSubsystem() {
    LMotor = new PWMTalonSRX(DriveConstants.kLeftDriveMasterOutput);
    RMotor = new PWMTalonSRX(DriveConstants.kRightDriveMasterOutput);
    m_drive = new DifferentialDrive(LMotor, RMotor);
    zeroHeading();
  }

  public void arcadeDrive(double fwd, double rot) {
    m_drive.arcadeDrive(fwd, rot);
  }

  public void tankDrive(double l, double r) {
    m_drive.tankDrive(l, r);
  }

  public void setMaxOutput(double maxOutput) {
    m_drive.setMaxOutput(maxOutput);
  }

  public void zeroHeading() {
    m_gyro.reset();
  }

  public double getAngle(){
    return m_gyro.getAngle();
  }

  public double getHeading() {
    return Math.IEEEremainder(m_gyro.getAngle(), 360) * (DriveConstants.kGyroReversed ? -1.0 : 1.0);
  }

  public double getTurnRate() {
    return m_gyro.getRate() * (DriveConstants.kGyroReversed ? -1.0 : 1.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // addChild("LMotor", LMotor);
    // addChild("RMotor", RMotor);
    SmartDashboard.putNumber("gyro", getHeading());
    SmartDashboard.putNumber("gyroaa", getAngle());
  }
}
