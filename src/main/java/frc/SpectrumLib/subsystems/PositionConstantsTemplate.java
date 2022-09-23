// Created by Spectrum3847

package frc.SpectrumLib.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;
import frc.SpectrumLib.motorControllers.TalonFXSetup;

public final class PositionConstantsTemplate {
    public static final String name = "PositionSubsystem";

    public static final int fullExtend = 0;
    public static final int fullRetract = 0;

    // Physical Constants
    public static final double pulleyDiameterInches = 2;
    public static final double pulleyDiameterMeters = pulleyDiameterInches * 0.0254;

    public static final double gearRatio = 1;

    public static final double pulleyCircumferenceMeters = pulleyDiameterMeters * Math.PI;
    public static final double pulleyCircumferenceInches = pulleyDiameterInches * Math.PI;

    /* Motor Characterization Values */
    public static final double kS = 0;
    public static final double kV = 0;
    public static final double kA = 0;

    // Falcon Setup
    public static TalonFXConfiguration config = new TalonFXConfiguration();

    /* Inverted */
    public static final boolean kInverted = false;
    public static final boolean kFollowerInverted = true;

    /* Neutral Modes */
    public static final NeutralMode kNeutralMode = NeutralMode.Brake;

    /* Control Loop Constants */
    public static final double kP = 0.0;
    public static final double kI = 0;
    public static final double kD = 0;
    public static final double kF = 0.05;
    public static final double kIz = 150;
    public static final double motionCruiseVelocity = 0;
    public static final double motionAcceleration = 0;

    /* Current Limiting */
    public static final int currentLimit = 40;
    public static final int tirggerThresholdLimit = 45;
    public static final double PeakCurrentDuration = 0.5;
    public static final boolean EnableCurrentLimit = true;
    public static final SupplyCurrentLimitConfiguration supplyLimit =
            new SupplyCurrentLimitConfiguration(
                    EnableCurrentLimit, currentLimit, tirggerThresholdLimit, PeakCurrentDuration);

    /* Voltage Compensation */
    public static final double voltageCompSaturation = 12;

    /* Ramp Rate */
    public static final double openLoopRamp = 0;
    public static final double closedLoopRamp = 0;

    /* Intialization Strategy */
    public static final SensorInitializationStrategy sensorStrat =
            SensorInitializationStrategy.BootToZero;

    /* getConfig */
    private static final PositionConstantsTemplate instance = new PositionConstantsTemplate();

    public static PositionConstantsTemplate getInstance() {
        return instance;
    }

    private PositionConstantsTemplate() {
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

    public static void setupFalconLeader(TalonFX motor) {
        TalonFXSetup.configAllSetup(motor, config);
        motor.setInverted(kInverted);
        motor.setNeutralMode(kNeutralMode);
    }

    public static void setupFalconFollower(TalonFX motorFollower, TalonFX motorLeader) {
        TalonFXSetup.configFollowerSetup(motorFollower, config);
        motorFollower.setInverted(kFollowerInverted);
        motorFollower.setNeutralMode(kNeutralMode);
        motorFollower.follow(motorLeader);
    }
}
