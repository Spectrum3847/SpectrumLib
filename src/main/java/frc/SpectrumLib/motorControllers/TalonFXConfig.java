package frc.SpectrumLib.motorControllers;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;

public final class TalonFXConfig {
    public static TalonFXConfiguration config;

    /* Neutral Modes */
    public final NeutralMode neutralMode = NeutralMode.Coast;

    /* Control Loop Config */
    public final double kP = 0;
    public final double kI = 0;
    public final double kD = 0;
    public final double kF = 0;
    public final double kIz = 0;
    public final double motionCruiseVelocity = 0;
    public final double motionAcceleration = 0;

    /* Current Limiting */
    public final int currentLimit = 8;
    public final int tirggerThresholdLimit = 0;
    public final double PeakCurrentDuration = 0;
    public final boolean EnableCurrentLimit = false;
    public final SupplyCurrentLimitConfiguration supplyLimit =
            new SupplyCurrentLimitConfiguration(
                    EnableCurrentLimit, currentLimit, tirggerThresholdLimit, PeakCurrentDuration);

    /* Voltage Compensation */
    public final double voltageCompSaturation = 12;

    /* Ramp Rate */
    public final double openLoopRamp = 0;
    public final double closedLoopRamp = 0;

    /* Motor Characterization Values */
    public final double kS = 0;
    public final double kV = 0;
    public final double kA = 0;

    /* Intialization Strategy */
    public final SensorInitializationStrategy sensorStrat = SensorInitializationStrategy.BootToZero;

    /* getConfig */
    private static final TalonFXConfig instance = new TalonFXConfig();

    public static TalonFXConfig getInstance() {
        return instance;
    }

    private TalonFXConfig() {
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
    }
}
