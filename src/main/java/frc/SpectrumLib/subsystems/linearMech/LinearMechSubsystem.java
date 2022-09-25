package frc.SpectrumLib.subsystems.linearMech;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.SpectrumLib.subsystems.MotorSubsystem;

public abstract class LinearMechSubsystem extends MotorSubsystem {
    public LinearMechConfigs configs;

    public LinearMechSubsystem(LinearMechConfigs configs, WPI_TalonFX motorLeader) {
        super(configs, motorLeader);
        this.configs = configs;
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
