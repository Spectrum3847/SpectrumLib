// Created by Spectrum3847
package frc.SpectrumLib.gamepads;

import edu.wpi.first.wpilibj.Joystick;
import frc.SpectrumLib.gamepads.AxisButton.ThresholdType;

public class XboxGamepad extends Joystick {

    public XboxGamepad(int port) {
        super(port);
    }

    public XboxGamepad(int port, double xDeadband, double yDeadband) {
        this(port);
        this.leftStick.setDeadband(xDeadband, yDeadband);
        this.rightStick.setDeadband(xDeadband, yDeadband);
    }

    public XboxGamepad(
            int port,
            double leftXDeadband,
            double leftYDeadband,
            double rightXDeadband,
            double rightYDeadband) {
        this(port);
        this.leftStick.setDeadband(leftXDeadband, leftYDeadband);
        this.rightStick.setDeadband(rightXDeadband, rightYDeadband);
    }

    public SpectrumButton xButton = new SpectrumButton(this, XboxButton.X);
    public SpectrumButton yButton = new SpectrumButton(this, XboxButton.Y);
    public SpectrumButton aButton = new SpectrumButton(this, XboxButton.A);
    public SpectrumButton bButton = new SpectrumButton(this, XboxButton.B);
    public SpectrumButton rightBumper = new SpectrumButton(this, XboxButton.RIGHT_BUMPER);
    public SpectrumButton leftBumper = new SpectrumButton(this, XboxButton.LEFT_BUMPER);
    public SpectrumButton startButton = new SpectrumButton(this, XboxButton.START);
    public SpectrumButton selectButton = new SpectrumButton(this, XboxButton.SELECT);
    public SpectrumButton leftStickButton = new SpectrumButton(this, XboxButton.LEFT_STICK);
    public SpectrumButton rightStickButton = new SpectrumButton(this, XboxButton.RIGHT_STICK);

    public AxisButton leftTriggerButton =
            new AxisButton(this, XboxAxis.LEFT_TRIGGER, .05, ThresholdType.GREATER_THAN);
    public AxisButton rightTriggerButton =
            new AxisButton(this, XboxAxis.RIGHT_TRIGGER, .05, ThresholdType.GREATER_THAN);
    public Dpad Dpad = new Dpad(this);

    public ThumbStick leftStick = new ThumbStick(this, XboxAxis.LEFT_X, XboxAxis.LEFT_Y);
    public ThumbStick rightStick = new ThumbStick(this, XboxAxis.RIGHT_X, XboxAxis.RIGHT_Y);

    public Triggers triggers = new Triggers(this);

    public void setRumble(double leftValue, double rightValue) {
        setRumble(RumbleType.kLeftRumble, leftValue);
        setRumble(RumbleType.kRightRumble, rightValue);
    }

    static enum XboxButton {
        A(1),
        B(2),
        X(3),
        Y(4),
        LEFT_BUMPER(5),
        RIGHT_BUMPER(6),
        SELECT(7),
        START(8),
        LEFT_STICK(9),
        RIGHT_STICK(10);

        final int value;

        XboxButton(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    public static enum XboxAxis {
        LEFT_X(0),
        LEFT_Y(1),
        LEFT_TRIGGER(2),
        RIGHT_TRIGGER(3),
        RIGHT_X(4),
        RIGHT_Y(5),
        DPAD(6);

        final int value;

        XboxAxis(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    public static enum XboxDpad {
        UNPRESSED(-1),
        UP(0),
        UP_RIGHT(45),
        RIGHT(90),
        DOWN_RIGHT(135),
        DOWN(180),
        DOWN_LEFT(225),
        LEFT(270),
        UP_LEFT(315);

        final int value;

        XboxDpad(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }
}
