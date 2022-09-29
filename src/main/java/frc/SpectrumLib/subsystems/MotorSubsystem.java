package frc.SpectrumLib.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.SpectrumLib.motorControllers.TalonFXSetup;

/** Add your docs here. */
public class MotorSubsystem extends SubsystemBase {

    public MotorSubsystemConfig config;
    protected WPI_TalonFX motorLeader;

    public MotorSubsystem(MotorSubsystemConfig config) {
        super();
        this.config = config;
        setDefaultCommand(new RunCommand(() -> this.stop(), this));
    }

    public void setupFalconLeader() {
        TalonFXSetup.configAllSetup(motorLeader, config.TalonFXConfig);
        motorLeader.setInverted(config.kInverted);
        motorLeader.setNeutralMode(config.kNeutralMode);
    }

    public void setupFalconFollower(TalonFX motorFollower) {
        setupFalconFollower(motorFollower, motorLeader, config.kFollowerInverted);
    }

    public void setupFalconFollower(TalonFX motorFollower, TalonFX motorLeader, boolean inverted) {
        TalonFXSetup.configFollowerSetup(motorFollower, config.TalonFXConfig);
        motorFollower.setInverted(inverted);
        motorFollower.setNeutralMode(config.kNeutralMode);
        motorFollower.follow(motorLeader);
    }

    public void setManualOutput(double speed) {
        motorLeader.set(speed);
    }

    public void setVoltageOutput(double voltage) {
        motorLeader.setVoltage(voltage);
    }

    public void stop() {
        motorLeader.stopMotor();
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

    // Return Rotations per sec velocity
    public double getRotationsPerSec() {
        return (motorLeader.getSelectedSensorVelocity() / 2048) * 10;
    }

    // Return RPM velocity
    public double getRPM() {
        return getRotationsPerSec() * 60;
    }

    // Get Position
    public double getPosition() {
        return motorLeader.getSelectedSensorPosition();
    }

    // Set Encoder
    public void setEncoder(double position) {
        motorLeader.setSelectedSensorPosition(position);
    }
}
