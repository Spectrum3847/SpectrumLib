// Created by Spectrum3847

package frc.SpectrumLib.subsystems.angleMech;

import frc.SpectrumLib.subsystems.MotorSubsystemConfig;

public final class AngleMechConfig extends MotorSubsystemConfig {
    public final int minAngle = 0;
    public final int maxAngle = 0;

    // Physical Constants
    public final double pulleyDiameterInches = 2;
    public final double pulleyDiameterMeters = pulleyDiameterInches * 0.0254;

    public final double gearRatio = 1;

    public final double pulleyCircumferenceMeters = pulleyDiameterMeters * Math.PI;
    public final double pulleyCircumferenceInches = pulleyDiameterInches * Math.PI;

    /* Elevator FeedForward gains Values */
    // https://docs.wpilib.org/en/stable/docs/software/advanced-controls/controllers/feedforward.html?highlight=kS#elevatorfeedforward
    public final double kS = 0;
    public final double kG = 0;
    public final double kV = 0;
    public final double kA = 0;

    public AngleMechConfig(String name) {
        super(name);
        updateTalonFXConfig();
    }
}
