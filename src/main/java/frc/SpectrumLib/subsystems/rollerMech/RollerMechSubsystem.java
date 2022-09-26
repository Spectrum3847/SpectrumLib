// Generic Roller Subsystem to be used for intakes, rollers, and launchers
// Should have easy way to configure 1 or 2 motors
// Should have a way to tune PIDF values
// Make a RollerConstantsConstantsConstantsConstantChtannsig tnstaottup alhtantse constants

package frc.SpectrumLib.subsystems.rollerMech;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.SpectrumLib.subsystems.MotorSubsystem;

public abstract class RollerMechSubsystem extends MotorSubsystem {
    public RollerMechConfig config;

    public RollerMechSubsystem(RollerMechConfig config, WPI_TalonFX motorLeader) {
        super(config, motorLeader);
        this.config = config;
        setDefaultCommand(new RunCommand(() -> this.stop(), this));
    }

    public void setVelocity(double velocity) {
        motorLeader.set(ControlMode.Velocity, velocity);
    }

    // Return Radians per sec velocity
    public double getCharacterizationVelocity() {
        return (getRotationsPerSec() * 2 * Math.PI);
    }
}
