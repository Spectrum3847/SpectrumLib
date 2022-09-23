// Created by Spectrum3847

package frc.SpectrumLib.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;
import frc.SpectrumLib.motorControllers.TalonFXSetup;

public abstract class RollerConstants {
    public String name;

    // Physical Constants
    public double diameterInches = 2;
    public double diameterMeters = diameterInches * 0.0254;

    public double gearRatio = 1;

    public double wheelCircumferenceMeters = diameterMeters * Math.PI;
    public double wheelCircumferenceInches = diameterInches * Math.PI;

    public double maxRPM = 6000;

    // Falcon Setup
    public static TalonFXConfiguration config = new TalonFXConfiguration();

    /* Inverted */
    public boolean kInverted = false;
    public boolean kFollowerInverted = true;

    /* Neutral Modes */
    public NeutralMode kNeutralMode = NeutralMode.Coast;

    /* Control Loop Constants */
    public double kP = 0.0;
    public double kI = 0.0;
    public double kD = 0.0;
    public double kF = 0.0;
    public double kIz = 150;
    public double motionCruiseVelocity = 0;
    public double motionAcceleration = 0;

    /* Motor Characterization Values */
    public double kS = 0;
    public double kV = 0;
    public double kA = 0;

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

    public void setConfig() {
        config.slot0.kP = kP;
        config.slot0.kI = kI;
        config.slot0.kD = kD;
        config.slot0.kF = kF;
        config.slot0.integralZone = kIz;
        config.motionCruiseVelocity = motionCruiseVelocity;
        config.motionAcceleration = motionAcceleration;

        config.supplyCurrLimit = supplyLimit;
        config.openloopRamp = openLoopRamp;
        config.closedloopRamp = closedLoopRamp;
        config.voltageCompSaturation = voltageCompSaturation;
        config.initializationStrategy = sensorStrat;
    }

    public void setupFalconLeader(TalonFX motor) {
        TalonFXSetup.configAllSetup(motor, config);
        motor.setInverted(kInverted);
        motor.setNeutralMode(kNeutralMode);
    }

    public void setupFalconFollower(TalonFX motorFollower, TalonFX motorLeader) {
        TalonFXSetup.configFollowerSetup(motorFollower, config);
        motorFollower.setInverted(kFollowerInverted);
        motorFollower.setNeutralMode(kNeutralMode);
        motorFollower.follow(motorLeader);
    }
}
