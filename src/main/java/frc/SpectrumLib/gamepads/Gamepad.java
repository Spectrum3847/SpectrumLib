// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.SpectrumLib.gamepads;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.SpectrumLib.util.Alert;

public class Gamepad extends SubsystemBase {

    public boolean configured = false;
    public XboxGamepad gamepad;
    public Alert alert;

    /**
     * Creates a new Gamepad.
     *
     * @param port The port the gamepad is plugged into
     * @param name The name of the gamepad
     */
    public Gamepad(String name, int port) {
        gamepad = new XboxGamepad(port);
        alert = new Alert("Alerts", name + "GAMEPAD NOT FOUND", Alert.AlertType.WARNING);
    }

    @Override
    public void periodic() {
        configure();
    }

    // Configure the driver controller
    public void configure() {
        // Detect whether the xbox controller has been plugged in after start-up
        if (!configured) {
            boolean isConnected = gamepad.isConnected();
            if (!isConnected) {
                alert.set(true);
                return;
            }

            // Configure button bindings once the driver controller is connected
            if (DriverStation.isTest()) {
                setupTestButtons();
            } else if (DriverStation.isDisabled()) {
                setupDisabledButtons();
            } else {
                setupTeleopButtons();
            }
            configured = true;

            alert.set(false);
        }
    }

    // Reset the controller configure, should be used with
    // CommandScheduler.getInstance.clearButtons()
    // to reset buttons
    public void resetConfig() {
        configured = false;
        configure();
    }

    public void setupTeleopButtons() {}

    public void setupDisabledButtons() {}

    public void setupTestButtons() {}
}
