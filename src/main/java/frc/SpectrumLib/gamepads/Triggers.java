// Created by Spectrum3847
package frc.SpectrumLib.gamepads;

import edu.wpi.first.wpilibj.Joystick;
import frc.SpectrumLib.gamepads.XboxGamepad.XboxAxis;
import frc.SpectrumLib.gamepads.mapping.ExpCurve;

public class Triggers {
    Joystick controller;

    public final ExpCurve expLeftCurve;
    public final ExpCurve expRightCurve;
    public final ExpCurve expTwistCurve;
    private boolean twistInvert = false;

    public Triggers(Joystick controller) {
        this.controller = controller;
        this.expLeftCurve = new ExpCurve();
        this.expRightCurve = new ExpCurve();
        this.expTwistCurve = new ExpCurve();
    }

    public double getLeft() {
        if (this.controller.isConnected()) {
            double value = this.controller.getRawAxis(XboxAxis.LEFT_TRIGGER.value);
            value = expLeftCurve.calculateMappedVal(value);
            return value;
        } else {
            return 0;
        }
    }

    public double getRight() {
        if (this.controller.isConnected()) {
            double value = this.controller.getRawAxis(XboxAxis.RIGHT_TRIGGER.value);
            value = expRightCurve.calculateMappedVal(value);
            return value;
        } else {
            return 0;
        }
    }

    public double getTwist() {
        double right = this.controller.getRawAxis(XboxAxis.RIGHT_TRIGGER.value);
        double left = this.controller.getRawAxis(XboxAxis.LEFT_TRIGGER.value);
        double value = right - left;
        if (this.controller.isConnected()) {
            value = expTwistCurve.calculateMappedVal(value) * (twistInvert ? -1 : 1);
            return value;
        }
        return 0;
    }

    public void setTwistInvert(boolean invert) {
        twistInvert = invert;
    }

    public void configLeftCurve(double expVal, double scalar) {
        expLeftCurve.setExpVal(expVal);
        expLeftCurve.setScalar(scalar);
    }

    public void configRightCurve(double expVal, double scalar) {
        expRightCurve.setExpVal(expVal);
        expRightCurve.setScalar(scalar);
    }

    public void configTwistCurve(double expVal, double scalar) {
        expTwistCurve.setExpVal(expVal);
        expTwistCurve.setScalar(scalar);
    }

    public void setLeftDeadband(double deadband) {
        expLeftCurve.setDeadzone(deadband);
    }

    public void setRightDeadband(double deadband) {
        expRightCurve.setDeadzone(deadband);
    }

    public void setTwistDeadband(double deadband) {
        expTwistCurve.setDeadzone(deadband);
    }
}
