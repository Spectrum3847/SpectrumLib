// Created by Spectrum3847

package frc.SpectrumLib.subsystems.rollerMech;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import frc.SpectrumLib.motorControllers.TalonFXSetup;
import frc.SpectrumLib.subsystems.MotorSubsystemConfig;

public abstract class RollerMechConfig extends MotorSubsystemConfig {
    // Physical Constants
    public double diameterInches = 2;
    public double diameterMeters = diameterInches * 0.0254;

    public double gearRatio = 1;

    public double wheelCircumferenceMeters = diameterMeters * Math.PI;
    public double wheelCircumferenceInches = diameterInches * Math.PI;

    public double maxRPM = 6000;

    /* Motor Characterization Values */
    public double kS = 0;
    public double kV = 0;
    public double kA = 0;

    public RollerMechConfig(String name) {
        super(name);
        updateTalonFXConfig();
    }

    public void setupFalconLeader(TalonFX motor) {
        TalonFXSetup.configAllSetup(motor, TalonFXConfig);
        motor.setInverted(kInverted);
        motor.setNeutralMode(kNeutralMode);
    }

    public void setupFalconFollower(TalonFX motorFollower, TalonFX motorLeader) {
        TalonFXSetup.configFollowerSetup(motorFollower, TalonFXConfig);
        motorFollower.setInverted(kFollowerInverted);
        motorFollower.setNeutralMode(kNeutralMode);
        motorFollower.follow(motorLeader);
    }
}
