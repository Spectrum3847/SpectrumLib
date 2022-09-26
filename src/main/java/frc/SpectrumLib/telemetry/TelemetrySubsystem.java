// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.SpectrumLib.telemetry;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.shuffleboard.ComplexWidget;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TelemetrySubsystem extends SubsystemBase {
    protected ShuffleboardTab tab;

    public TelemetrySubsystem(String name) {
        tab = Shuffleboard.getTab("name");
    }

    protected SimpleWidget addWidget(String name, Object data) {
        return tab.add(name, data);
    }

    protected ComplexWidget addWidget(String name, Sendable sendable) {
        return tab.add(name, sendable);
    }
}
