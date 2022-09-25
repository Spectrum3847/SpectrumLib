package frc.SpectrumLib.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.SpectrumLib.drivers.SpectrumSolenoid;

public class PneumaticSubsystem extends SubsystemBase {
    public final SpectrumSolenoid solenoid;

    /**
     * Create a new PneumatiSubsystem, only single solenoids
     *
     * @param name The name of the subsystem
     * @param port The port of the solenoid
     */
    public PneumaticSubsystem(String name, int port) {
        setName(name);

        // REVPH isn't working in simulation for 2022 so use CTRE device
        if (RobotBase.isSimulation()) {
            solenoid = new SpectrumSolenoid(port);
        } else {
            solenoid = new SpectrumSolenoid(PneumaticsModuleType.REVPH, port);
        }

        // Default to off
        this.setDefaultCommand(new RunCommand(() -> off(), this));
    }

    /** Set the solenoid to on */
    public void on() {
        solenoid.set(true);
    }

    /** Set the solenoid to off */
    public void off() {
        solenoid.set(false);
    }
}
