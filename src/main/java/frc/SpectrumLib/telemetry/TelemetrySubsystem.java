// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.SpectrumLib.telemetry;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TelemetrySubsystem extends SubsystemBase {
    protected ShuffleboardTab tab;
    protected double updateRate = 0.2;

    public TelemetrySubsystem(String name) {
        tab = Shuffleboard.getTab(name);
        TelemetryThread.start();
    }

    protected void update() {}

    protected void setUpdateRate(double rate) {
        updateRate = rate;
    }

    Thread TelemetryThread =
            new Thread(
                    new Runnable() {
                        public void run() {
                            while (true) {
                                update();
                                Timer.delay(updateRate); // Loop runs at 5hz
                            }
                        }
                    });
}
