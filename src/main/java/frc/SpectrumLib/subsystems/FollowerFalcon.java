package frc.SpectrumLib.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.SpectrumLib.motorControllers.TalonFXSetup;

/** Creates a FollowerFalcon for a subsystem */
public class FollowerFalcon extends SubsystemBase {
    protected WPI_TalonFX motorFollower;
    protected WPI_TalonFX leader;

    public FollowerFalcon(
            int followerID,
            String canBus,
            TalonFXConfiguration config,
            boolean inverted,
            WPI_TalonFX leader) {
        motorFollower = new WPI_TalonFX(followerID, canBus);
        TalonFXSetup.configFollowerSetup(motorFollower, config);
        motorFollower.setInverted(inverted);
        this.leader = leader;
    }

    public FollowerFalcon(
            int followerID, TalonFXConfiguration config, boolean inverted, WPI_TalonFX leader) {
        this(followerID, "rio", config, inverted, leader);
    }

    @Override
    public void periodic() {
        motorFollower.follow(leader);
    }

    public WPI_TalonFX get() {
        return motorFollower;
    }
}
