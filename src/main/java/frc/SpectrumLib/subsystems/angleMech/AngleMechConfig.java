// Created by Spectrum3847

package frc.SpectrumLib.subsystems.angleMech;

import frc.SpectrumLib.subsystems.MotorSubsystemConfig;

public abstract class AngleMechConfig extends MotorSubsystemConfig {
    public int minAngle = 0;
    public int maxAngle = 0;

    public double gearRatio = 1;

    /* Elevator FeedForward gains Values */
    // https://docs.wpilib.org/en/stable/docs/software/advanced-controls/controllers/feedforward.html?highlight=kS#elevatorfeedforward
    public double kS = 0;
    public double kG = 0;
    public double kV = 0;
    public double kA = 0;

    public AngleMechConfig(String name) {
        super(name);
        updateTalonFXConfig();
    }
}
