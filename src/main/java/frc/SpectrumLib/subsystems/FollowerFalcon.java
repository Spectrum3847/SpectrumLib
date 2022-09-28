package frc.SpectrumLib.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.SpectrumLib.motorControllers.TalonFXSetup;

/** Add your docs here. */
public class FollowerFalcon extends SubsystemBase {
    protected WPI_TalonFX motorFollower;
    protected int leaderID;

    public FollowerFalcon(
            int followerID, TalonFXConfiguration config, boolean inverted, int leaderID) {
        motorFollower = new WPI_TalonFX(followerID);
        TalonFXSetup.configFollowerSetup(motorFollower, config);
        motorFollower.setInverted(inverted);
        this.leaderID = leaderID;
    }

    @Override
    public void periodic() {
        motorFollower.set(ControlMode.Follower, leaderID);
    }
}
