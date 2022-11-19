// Created by Spectrum3847
package frc.SpectrumLib.gamepads;

import edu.wpi.first.math.util.Units;
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
    private double lastAngle = 0;
    private boolean yInvert = true;
    private boolean xInvert = false;

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
        return value * (xInvert ? -1 : 1);
    }

    // return getY inverted to make Y positive when joystick is pushed up
    public double getY() {
        double value = 0;
        if (this.controller.isConnected()) {
            value = this.controller.getRawAxis(yAxis.value);
            value = expYCurve.calculateMappedVal(value);
        }
        return value * (yInvert ? -1 : 1);
    }

    public void setXinvert(boolean invert) {
        xInvert = invert;
    }

    public void setYinvert(boolean invert) {
        yInvert = invert;
    }

    public void setInvert(boolean xInvert, boolean yInvert) {
        this.xInvert = xInvert;
        this.yInvert = yInvert;
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

    /**
     * Return the direction of the joystick in radians, returns last value if joystick is in
     * from fwd Positive 0, counter clockwise
     * deadzone 0 = fwd, pi/2 = left, -pi = down, -pi/2 = right
     *
     * @param fwdPositive the axis that represents the fwd direction
     * @param leftPositive the axis that represents the left direction
     * @return the angle of the joysick
     */
    public double getDirectionRadians(double fwdPositive, double leftPositive) {
        if (getX() != 0 || getY() != 0) {
            double angle = Math.atan2(leftPositive, fwdPositive);
            lastAngle = angle;
        }
        return lastAngle;
    }
    /**
     * Return the direction of the joystick in radians, returns last value if joystick is in
     * deadzone 0 = fwd, 90 = left, 180 = down, 270 = right
     *
     * @param fwdPositive the axis that represents the fwd direction
     * @param leftPositive the axis that represents the left direction
     * @return the angle of the joysick
     */
    public double getDirectionDegrees(double fwdPositive, double leftPositive) {
        double angle = Units.radiansToDegrees(getDirectionRadians(fwdPositive, leftPositive));
        if (angle < 0) {
            angle += 360;
        }
        return angle;
    }

    public double getMagnitude() {
        return Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2));
    }
}
