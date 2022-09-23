//Created by Spectrum3847
package frc.SpectrumLib.gamepads;

import edu.wpi.first.wpilibj.Joystick;
import frc.SpectrumLib.gamepads.XboxGamepad.XboxAxis;

public class Triggers {
	Joystick controller;
	
	public Triggers(Joystick controller) {
		this.controller = controller;
	}

	public double getLeft() {
		if (this.controller.isConnected()){
			return this.controller.getRawAxis(XboxAxis.LEFT_TRIGGER.value);
		} else{
			return 0;
		}
	}
	
	public double getRight() {
		if (this.controller.isConnected()){
			return this.controller.getRawAxis(XboxAxis.RIGHT_TRIGGER.value);
		} else{
			return 0;
		}
	}
	
	public double getTwist() {
		return getRight() - getLeft();
	}
}
