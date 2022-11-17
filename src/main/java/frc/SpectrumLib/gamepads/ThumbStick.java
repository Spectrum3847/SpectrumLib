// Created by Spectrum3847
package frc.SpectrumLib.gamepads;

import edu.wpi.first.wpilibj.Joystick;
import frc.SpectrumLib.gamepads.XboxGamepad.XboxAxis;
import frc.SpectrumLib.gamepads.mapping.ExpCurve;

// Based on Code form Bob319 2017
public class ThumbStick {
    Joystick controller;
    XboxAxis xAxis;
    XboxAxis yAxis;
    public final ExpCurve expXCurve;
    public final ExpCurve expYCurve;

    public ThumbStick(Joystick controller, XboxAxis xAxis, XboxAxis yAxis) {
        this.controller = controller;
        this.xAxis = xAxis;
        this.yAxis = yAxis;

        expXCurve = new ExpCurve();
        expYCurve = new ExpCurve();
    }

    public ThumbStick(
            Joystick controller,
            XboxAxis xAxis,
            XboxAxis yAxis,
            double yDeadzone,
            double xDeadzone) {
        this(controller, xAxis, yAxis);
        expXCurve.setDeadzone(xDeadzone);
        expYCurve.setDeadzone(yDeadzone);
    }

    public double getX() {
        double value = 0;
        if (this.controller.isConnected()) {
            value = this.controller.getRawAxis(xAxis.value);
            value = expXCurve.calculateMappedVal(value);
        }
        return value;
    }

    public double getY() {
        double value = 0;
        if (this.controller.isConnected()) {
            value = this.controller.getRawAxis(yAxis.value);
            value = expYCurve.calculateMappedVal(value);
        }
        return value;
    }

    public void configXCurve(double expVal, double scalar) {
        expXCurve.setExpVal(expVal);
        expXCurve.setScalar(scalar);
    }

    public void configYCurve(double expVal, double scalar) {
        expYCurve.setExpVal(expVal);
        expYCurve.setScalar(scalar);
    }

    public void configCurves(double expVal, double scalar) {
        expXCurve.setExpVal(expVal);
        expXCurve.setScalar(scalar);
        expYCurve.setExpVal(expVal);
        expYCurve.setScalar(scalar);
    }

    public void setXDeadband(double deadzone) {
        expXCurve.setDeadzone(deadzone);
    }

    public void setYDeadband(double deadzone) {
        expYCurve.setDeadzone(deadzone);
    }

    public void setDeadband(double xDeadzone, double yDeadzone) {
        setXDeadband(xDeadzone);
        setYDeadband(yDeadzone);
    }

    public void setDeadband(double deadzone) {
        setDeadband(deadzone, deadzone);
    }

    public double getDirectionRadians() {
        return Math.atan2(getX(), -getY());
    }

    public double getDirectionDegrees() {
        return Math.toDegrees(getDirectionRadians());
    }

    public double getMagnitude() {
        return Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2));
    }
}
