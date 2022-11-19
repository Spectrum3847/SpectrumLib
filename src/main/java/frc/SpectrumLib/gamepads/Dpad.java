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
        this.Up = AxisButton.create(joy, XboxAxis.DPAD, XboxDpad.UP.value, ThresholdType.POV);
        this.Down = AxisButton.create(joy, XboxAxis.DPAD, XboxDpad.DOWN.value, ThresholdType.POV);
        this.Left = AxisButton.create(joy, XboxAxis.DPAD, XboxDpad.LEFT.value, ThresholdType.POV);
        this.Right = AxisButton.create(joy, XboxAxis.DPAD, XboxDpad.RIGHT.value, ThresholdType.POV);
        this.UpLeft =
                AxisButton.create(joy, XboxAxis.DPAD, XboxDpad.UP_LEFT.value, ThresholdType.POV);
        this.UpRight =
                AxisButton.create(joy, XboxAxis.DPAD, XboxDpad.UP_RIGHT.value, ThresholdType.POV);
        this.DownLeft =
                AxisButton.create(joy, XboxAxis.DPAD, XboxDpad.DOWN_LEFT.value, ThresholdType.POV);
        this.DownRight =
                AxisButton.create(joy, XboxAxis.DPAD, XboxDpad.DOWN_RIGHT.value, ThresholdType.POV);
    }

    public double getValue() {
        return joy.getRawAxis(XboxAxis.DPAD.value);
    }
}
