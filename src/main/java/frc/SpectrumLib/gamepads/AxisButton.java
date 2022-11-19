// Created by Spectrum3847

package frc.SpectrumLib.gamepads;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.SpectrumLib.gamepads.XboxGamepad.XboxAxis;
import java.util.function.BooleanSupplier;

public class AxisButton implements BooleanSupplier {
    private final GenericHID joy;
    private final int axis;
    private double targetVal;
    private ThresholdType thresholdType;

    public static enum ThresholdType {
        LESS_THAN,
        GREATER_THAN,
        EXACT,
        POV,
        DEADBAND;
    }

    public static Trigger create(
            Joystick joystick, int axis, double threshold, ThresholdType thresholdType) {
        return new Trigger(new AxisButton(joystick, axis, threshold, thresholdType));
    }

    public static Trigger create(
            Joystick joystick, XboxAxis axis, double threshold, ThresholdType thresholdType) {
        return new Trigger(new AxisButton(joystick, axis, threshold, thresholdType));
    }

    public static Trigger create(Joystick joystick, XboxAxis axis, double threshold) {
        return new Trigger(new AxisButton(joystick, axis, threshold));
    }

    private AxisButton(Joystick joystick, int axis, double threshold, ThresholdType thresholdType) {
        this.joy = joystick;
        this.axis = axis;
        this.targetVal = threshold;
        this.thresholdType = thresholdType;
    }

    private AxisButton(
            Joystick joystick, XboxAxis axis, double threshold, ThresholdType thresholdType) {
        this(joystick, axis.value, threshold, thresholdType);
    }

    private AxisButton(Joystick joystick, XboxAxis axis, double threshold) {
        this(joystick, axis.value, threshold, ThresholdType.DEADBAND);
    }

    public Trigger trigger() {
        return new Trigger(this);
    }

    public boolean getAsBoolean() {
        switch (this.thresholdType) {
            case EXACT:
                // System.out.println("axis value: " + joy.getRawAxis(this.axis));
                return joy.getRawAxis(this.axis) == this.targetVal;
            case LESS_THAN:
                return joy.getRawAxis(this.axis) < this.targetVal;
            case GREATER_THAN:
                return joy.getRawAxis(this.axis) > this.targetVal;
            case POV:
                return joy.getPOV() == this.targetVal;
            case DEADBAND:
                return Math.abs(joy.getRawAxis(this.axis)) > this.targetVal;
            default:
                return false;
        }
    }
}
