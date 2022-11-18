// Created by Spectrum3847

package frc.SpectrumLib.gamepads;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.SpectrumLib.gamepads.XboxGamepad.XboxAxis;

public class AxisButton {
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

    public AxisButton(Joystick joystick, int axis, double threshold, ThresholdType thresholdType) {
        this.joy = joystick;
        this.axis = axis;
        this.targetVal = threshold;
        this.thresholdType = thresholdType;
    }

    public AxisButton(
            Joystick joystick, XboxAxis axis, double threshold, ThresholdType thresholdType) {
        this(joystick, axis.value, threshold, thresholdType);
    }

    public Trigger trigger() {
        return new Trigger(() -> get());
    }

    private boolean get() {
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
