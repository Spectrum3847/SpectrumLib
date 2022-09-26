// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.SpectrumLib.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXSimCollection;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/** Add your docs here. */
public class MotorSubsystem extends SubsystemBase {

    public MotorSubsystemConfig config;
    public WPI_TalonFX motorLeader;
    public TalonFXSimCollection motorSim;

    public MotorSubsystem(MotorSubsystemConfig config) {
        super();
        this.config = config;
        motorSim = motorLeader.getSimCollection();
        setDefaultCommand(new RunCommand(() -> this.stop(), this));
    }

    public void setManualOutput(double speed) {
        motorLeader.set(speed);
    }

    public void setVoltageOutput(double voltage) {
        motorLeader.setVoltage(voltage);
    }

    public void stop() {
        motorLeader.stopMotor();
    }

    public void resetEncoder() {
        motorLeader.setSelectedSensorPosition(0);
    }

    public double getCurrent() {
        return motorLeader.getSupplyCurrent();
    }

    public void setBrakeMode(boolean mode) {
        motorLeader.setNeutralMode(mode ? NeutralMode.Brake : NeutralMode.Coast);
    }

    // Return Rotations per sec velocity
    public double getRotationsPerSec() {
        return (motorLeader.getSelectedSensorVelocity() / 2048) * 10;
    }

    // Return RPM velocity
    public double getRPM() {
        return getRotationsPerSec() * 60;
    }

    // Get Position
    public double getPosition() {
        return motorLeader.getSelectedSensorPosition();
    }

    // Set Encoder
    public void setEncoder(double position) {
        motorLeader.setSelectedSensorPosition(position);
    }
}
