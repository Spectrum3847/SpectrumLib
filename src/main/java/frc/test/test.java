package frc.test;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.SpectrumLib.gamepads.XboxGamepad;

/** Add your docs here. */
public class test extends TimedRobot {
    XboxGamepad controller = new XboxGamepad(0);

    @Override
    public void robotInit() {
        controller.leftStick.setXinvert(true);
        controller.leftStick.setYinvert(true);
    }

    @Override
    public void robotPeriodic() {
        System.out.println(
                controller.leftStick.getDirectionDegrees(
                        controller.leftStick.getY(), controller.leftStick.getX()));

        System.out.println(
                controller.leftStick.getDirectionRadians(
                        controller.leftStick.getY(), controller.leftStick.getX()));
    }
}
