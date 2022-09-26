package frc.SpectrumLib.subsystems.linearMech;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.SpectrumLib.subsystems.MotorSubsystem;

public abstract class LinearMechSubsystem extends MotorSubsystem {
    public LinearMechConfig config;

    public LinearMechSubsystem(LinearMechConfig config, WPI_TalonFX motorLeader) {
        super(config, motorLeader);
        this.config = config;
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public void setManualOutput(double speed) {
        motorLeader.set(speed);
    }

    public void setMMPosition(double position) {
        motorLeader.set(ControlMode.MotionMagic, position);
    }

    public void enableSoftLimits(boolean fwdEnable, boolean revEnable) {
        motorLeader.configForwardSoftLimitEnable(fwdEnable);
        motorLeader.configReverseSoftLimitEnable(revEnable);
    }
}
