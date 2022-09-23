// Generic Roller Subsystem to be used for intakes, rollers, and launchers
// Should have easy way to configure 1 or 2 motors
// Should have a way to tune PIDF values
// Make a RollerConstantsConfig to setup all the constants

package frc.SpectrumLib.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXSimCollection;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public abstract class RollerSubsystem extends SubsystemBase {

    public WPI_TalonFX motorLeader;
    public TalonFXSimCollection motorSim;

    // motorLeader = new WPI_TalonFX(0); // Replace with correct CAN ID
    // RollerConstantsConfig.setupRollerFalconLeader(motorLeader);

    /*
     * Example for follower
     * motorFollower = new WPI_TalonFX(1); //Replace with correct CAN ID
     * RollerFalconConfig.setupRollerFalconFollower(motorFollower, motorLeader);
     */

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public void setManualOutput(double speed) {
        motorLeader.set(speed);
    }

    public void setVelocity(double velocity) {
        motorLeader.set(ControlMode.Velocity, velocity);
    }

    public void setVoltageOutput(double voltage) {
        motorLeader.setVoltage(voltage);
    }

    public void stop() {
        motorLeader.stopMotor();
    }

    // Return Radians per sec velocity
    public double getCharacterizationVelocity() {
        return (getRotationPerSec() * 2 * Math.PI);
    }

    // Return Rotations per sec velocity
    public double getRotationPerSec() {
        return (motorLeader.getSelectedSensorVelocity() / 2048) * 10;
    }

    // Return RPM velocity
    public double getRPM() {
        return getRotationPerSec() * 60;
    }

    public void resetEncoder() {
        motorLeader.setSelectedSensorPosition(0);
    }

    public double getCurrent() {
        return motorLeader.getSupplyCurrent();
    }

    public void setBrakeMode(boolean mode) {
        motorLeader.setNeutralMode(mode ? NeutralMode.Brake : NeutralMode.Coast);
    }

    public void dashboard() {}
}
