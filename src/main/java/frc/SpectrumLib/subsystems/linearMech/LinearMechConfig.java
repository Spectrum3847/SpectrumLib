// Created by Spectrum3847

package frc.SpectrumLib.subsystems.linearMech;

import frc.SpectrumLib.subsystems.MotorSubsystemConfig;

public abstract class LinearMechConfig extends MotorSubsystemConfig {
    public int fullExtend = 0;
    public int fullRetract = 0;

    // Physical Constants
    public double gearRatio = 1;

    // Physical Constants
    public double pulleyDiameterInches = 2;
    public double pulleyDiameterMeters = pulleyDiameterInches * 0.0254;

    public double pulleyCircumferenceMeters = pulleyDiameterMeters * Math.PI;
    public double pulleyCircumferenceInches = pulleyDiameterInches * Math.PI;

    /* Elevator FeedForward gains Values */
    // https://docs.wpilib.org/en/stable/docs/software/advanced-controls/controllers/feedforward.html?highlight=kS#elevatorfeedforward
    public double kS = 0;
    public double kG = 0;
    public double kV = 0;
    public double kA = 0;

    public LinearMechConfig(String name) {
        super(name);
        updateTalonFXConfig();
    }
}
