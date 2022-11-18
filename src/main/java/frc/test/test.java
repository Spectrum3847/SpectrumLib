package frc.test;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.SpectrumLib.gamepads.XboxGamepad;

/** Add your docs here. */
public class test extends TimedRobot {
    XboxGamepad controller = new XboxGamepad(0);

    @Override
    public void robotInit() {}

    @Override
    public void robotPeriodic() {
        System.out.println(controller.leftStick.getDirectionDegrees());
    }
}
