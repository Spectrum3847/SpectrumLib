// Created by Spectrum3847

package frc.SpectrumLib.gamepads;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.SpectrumLib.gamepads.XboxGamepad.XboxButton;

public class SpectrumButton extends JoystickButton {

    public SpectrumButton(GenericHID joystick, int buttonNumber) {
        super(joystick, buttonNumber);
    }

    public SpectrumButton(edu.wpi.first.wpilibj.XboxController joystick, XboxButton button) {
        super(joystick, button.value);
    }

    public SpectrumButton(XboxGamepad joystick, XboxButton button) {
        super(joystick, button.value);
    }
}
