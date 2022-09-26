package frc.SpectrumLib.subsystems.angleMech;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.SpectrumLib.subsystems.MotorSubsystem;

public abstract class AngleMechSubsystem extends MotorSubsystem {
    public AngleMechConfig config;

    public AngleMechSubsystem(AngleMechConfig config, WPI_TalonFX motorLeader) {
        super(config, motorLeader);
        this.config = config;
    }

    public void setMMPosition(double position) {
        motorLeader.set(ControlMode.MotionMagic, position);
    }

    public void enableSoftLimits(boolean fwdEnable, boolean revEnable) {
        motorLeader.configForwardSoftLimitEnable(fwdEnable);
        motorLeader.configReverseSoftLimitEnable(revEnable);
    }
}
