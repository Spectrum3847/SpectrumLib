//Created by Spectrum3847
package frc.SpectrumLib.gamepads;

import edu.wpi.first.wpilibj2.command.button.Button;

public class AndButton extends Button {

	Button b1;
	Button b2;

	public AndButton(Button button, Button button2) {
		b1 = button;
		b2 = button2;
	}

	public boolean get() {
		return b1.get() && b2.get();
	}
}
