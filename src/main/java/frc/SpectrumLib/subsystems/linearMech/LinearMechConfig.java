// Created by Spectrum3847

package frc.SpectrumLib.subsystems.linearMech;

import frc.SpectrumLib.subsystems.MotorSubsystemConfig;

public abstract class LinearMechConfig extends MotorSubsystemConfig {
    public final int fullExtend = 0;
    public final int fullRetract = 0;

    // Physical Constants
    public final double gearRatio = 1;

    /* Elevator FeedForward gains Values */
    // https://docs.wpilib.org/en/stable/docs/software/advanced-controls/controllers/feedforward.html?highlight=kS#elevatorfeedforward
    public final double kS = 0;
    public final double kG = 0;
    public final double kV = 0;
    public final double kA = 0;

    public LinearMechConfig(String name) {
        super(name);
        updateTalonFXConfig();
    }
}
