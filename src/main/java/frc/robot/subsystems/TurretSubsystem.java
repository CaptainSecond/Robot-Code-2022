// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TurretConstants;

public class TurretSubsystem extends SubsystemBase {
  /** Creates a new TurretSubsystem. */
  private WPI_VictorSPX TurretMotor = new WPI_VictorSPX(TurretConstants.kTurretMasterId);
  private final double cpr = 360; // if am-3132
  private final double whd = 2; // for 2 inch wheel
  private final double twh= 35/2.54; // inch Turret wheel 
  public final Encoder TurretEncoder;
  public boolean isFollowingTarget = false;
  public boolean isAtSetpoint = false;

  public TurretSubsystem() {
    TurretMotor.setNeutralMode(NeutralMode.Brake); // Elektriksel Frenleme
    TurretMotor.configFactoryDefault();
    TurretEncoder = new Encoder(TurretConstants.kTurretEncoderA, TurretConstants.kTurretEncoderB,
        TurretConstants.kIsEncoderReversed);
  }

  public void runTurret(double speed) {
    TurretMotor.set(speed);
  }

  public void setTurretVolts(double volts) {
    TurretMotor.setVoltage(volts);
}

public double HeadEncoderGetValue() {
  TurretEncoder.setDistancePerPulse(Math.PI* whd / cpr);
  return TurretEncoder.getDistance() * (whd /twh);
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Turret Encoder Value", HeadEncoderGetValue());
  }
}
