package frc.SpectrumLib.subsystems.angleMech;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.SpectrumLib.subsystems.MotorSubsystem;

public abstract class AngleMechSubsystem extends MotorSubsystem {
    public AngleMechConfigs configs;

    public AngleMechSubsystem(AngleMechConfigs configs, WPI_TalonFX motorLeader) {
        super(configs, motorLeader);
        this.configs = configs;
    }

    public void setMMPosition(double position) {
        motorLeader.set(ControlMode.MotionMagic, position);
    }

    public void enableSoftLimits(boolean fwdEnable, boolean revEnable) {
        motorLeader.configForwardSoftLimitEnable(fwdEnable);
        motorLeader.configReverseSoftLimitEnable(revEnable);
    }
}
