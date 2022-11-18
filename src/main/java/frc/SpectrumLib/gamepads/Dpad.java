// Created by Spectrum3847
package frc.SpectrumLib.gamepads;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.SpectrumLib.gamepads.AxisButton.ThresholdType;
import frc.SpectrumLib.gamepads.XboxGamepad.XboxAxis;
import frc.SpectrumLib.gamepads.XboxGamepad.XboxDpad;

public class Dpad {
    public final Joystick joy;
    public Trigger Up;
    public Trigger Down;
    public Trigger Left;
    public Trigger Right;
    public Trigger UpLeft;
    public Trigger UpRight;
    public Trigger DownLeft;
    public Trigger DownRight;

    public Dpad(Joystick joystick) {
        this.joy = joystick;
        this.Up =
                new AxisButton(joy, XboxAxis.DPAD, XboxDpad.UP.value, ThresholdType.POV).trigger();
        this.Down =
                new AxisButton(joy, XboxAxis.DPAD, XboxDpad.DOWN.value, ThresholdType.POV)
                        .trigger();
        this.Left =
                new AxisButton(joy, XboxAxis.DPAD, XboxDpad.LEFT.value, ThresholdType.POV)
                        .trigger();
        this.Right =
                new AxisButton(joy, XboxAxis.DPAD, XboxDpad.RIGHT.value, ThresholdType.POV)
                        .trigger();
        this.UpLeft =
                new AxisButton(joy, XboxAxis.DPAD, XboxDpad.UP_LEFT.value, ThresholdType.POV)
                        .trigger();
        this.UpRight =
                new AxisButton(joy, XboxAxis.DPAD, XboxDpad.UP_RIGHT.value, ThresholdType.POV)
                        .trigger();
        this.DownLeft =
                new AxisButton(joy, XboxAxis.DPAD, XboxDpad.DOWN_LEFT.value, ThresholdType.POV)
                        .trigger();
        this.DownRight =
                new AxisButton(joy, XboxAxis.DPAD, XboxDpad.DOWN_RIGHT.value, ThresholdType.POV)
                        .trigger();
    }

    public double getValue() {
        return joy.getRawAxis(XboxAxis.DPAD.value);
    }
}
