// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.SpectrumLib.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;
import frc.SpectrumLib.motorControllers.TalonFXSetup;

/** Add your docs here. */
public class MotorSubsystemConfig {
    public String name;
    /* Inverted */
    public boolean kInverted = false;
    public boolean kFollowerInverted = true;

    /* Neutral Modes */
    public NeutralMode kNeutralMode = NeutralMode.Brake;

    /* Control Loop Constants */
    public double kP = 0.0;
    public double kI = 0;
    public double kD = 0;
    public double kF = 0.05;
    public double kIz = 150;
    public double motionCruiseVelocity = 0;
    public double motionAcceleration = 0;

    /* Current Limiting */
    public int currentLimit = 40;
    public int tirggerThresholdLimit = 45;
    public double PeakCurrentDuration = 0.5;
    public boolean EnableCurrentLimit = true;
    public SupplyCurrentLimitConfiguration supplyLimit =
            new SupplyCurrentLimitConfiguration(
                    EnableCurrentLimit, currentLimit, tirggerThresholdLimit, PeakCurrentDuration);

    /* Voltage Compensation */
    public double voltageCompSaturation = 12;

    /* Ramp Rate */
    public double openLoopRamp = 0;
    public double closedLoopRamp = 0;

    /* Intialization Strategy */
    public SensorInitializationStrategy sensorStrat = SensorInitializationStrategy.BootToZero;

    // Falcon Setup
    public TalonFXConfiguration TalonFXConfig = new TalonFXConfiguration();

    public MotorSubsystemConfig(String name) {
        this.name = name;
        updateTalonFXConfig();
    }

    public void updateTalonFXConfig() {
        TalonFXConfig.slot0.kP = kP;
        TalonFXConfig.slot0.kI = kI;
        TalonFXConfig.slot0.kD = kD;
        TalonFXConfig.slot0.kF = kF;
        TalonFXConfig.slot0.integralZone = kIz;
        TalonFXConfig.motionCruiseVelocity = motionCruiseVelocity;
        TalonFXConfig.motionAcceleration = motionAcceleration;

        TalonFXConfig.supplyCurrLimit = supplyLimit;
        TalonFXConfig.openloopRamp = openLoopRamp;
        TalonFXConfig.closedloopRamp = closedLoopRamp;
        TalonFXConfig.voltageCompSaturation = voltageCompSaturation;
        TalonFXConfig.initializationStrategy = sensorStrat;
    }

    public void setupFalconLeader(TalonFX motor) {
        TalonFXSetup.configAllSetup(motor, TalonFXConfig);
        motor.setInverted(kInverted);
        motor.setNeutralMode(kNeutralMode);
    }

    public void setupFalconFollower(TalonFX motorFollower, TalonFX motorLeader) {
        TalonFXSetup.configFollowerSetup(motorFollower, TalonFXConfig);
        motorFollower.setInverted(kFollowerInverted);
        motorFollower.setNeutralMode(kNeutralMode);
        motorFollower.follow(motorLeader);
    }

    public void setupFalconFollower(TalonFX motorFollower, TalonFX motorLeader, boolean inverted) {
        TalonFXSetup.configFollowerSetup(motorFollower, TalonFXConfig);
        motorFollower.setInverted(inverted);
        motorFollower.setNeutralMode(kNeutralMode);
        motorFollower.follow(motorLeader);
    }
}
