// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.SpectrumLib.telemetry;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TelemetrySubsystem extends SubsystemBase {
    protected ShuffleboardTab tab;

    public TelemetrySubsystem(String name) {
        tab = Shuffleboard.getTab(name);
    }
}
