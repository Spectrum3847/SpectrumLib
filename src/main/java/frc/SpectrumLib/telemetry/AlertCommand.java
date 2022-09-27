// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.SpectrumLib.telemetry;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.SpectrumLib.telemetry.Alert.AlertType;

/**
 * A command that can be used to display an alert on the dashboard. Create the alert and sets status
 * to true when the command is initialized. Sets status to false when the command is ended.
 */
public class AlertCommand extends CommandBase {
    private Alert alert;

    public AlertCommand(String alertMessage) {
        this(alertMessage, AlertType.INFO);
    }

    public AlertCommand(String alertMessage, AlertType alertType) {
        alert = new Alert(alertMessage, alertType);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        alert.set(true);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {}

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        alert.set(false);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
