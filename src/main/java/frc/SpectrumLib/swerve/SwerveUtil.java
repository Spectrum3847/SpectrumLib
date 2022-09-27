package frc.SpectrumLib.swerve;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.Timer;

/** Impliments a static version of 3476's 2022 limitAcceleration swerve function */
public class SwerveUtil {

    private static Translation2d lastRequestedVelocity = new Translation2d();
    private static double lastRequestedRotation = 0;
    private static double lastLoopTime = 0;

    /**
     * Puts a limit on the acceleration. This method should be called before setting a chassis
     * speeds to the robot drivebase.
     *
     * <p>Limits the acceleration by ensuring that the difference between the command and previous
     * velocity doesn't exceed a set value
     *
     * @param commandedVelocity Desired velocity (The chassis speeds is mutated to the limited
     *     acceleration) (robot centric)
     * @param latencyCompedChassisSpeeds The chassis speeds that the robot is currently moving at
     *     (robot centric)
     * @param accelerationLimit The maximum acceleration in m/s^2
     * @param maxAngularAccel The maximum angular acceleration in rad/s^2
     * @param gyroAngle The current angle of the robot
     * @return The requested acceleration of the robot (field centric)
     */
    public static Translation2d limitAcceleration(
            ChassisSpeeds commandedVelocity,
            ChassisSpeeds latencyCompedChassisSpeeds,
            Double accelerationLimit,
            Double maxAngularAccel,
            Rotation2d gyroAngle) {
        double dt;
        if ((Timer.getFPGATimestamp() - lastLoopTime) > ((double) 20 / 1000) * 20) {
            // If the dt is a lot greater than our nominal dt reset the acceleration limiting
            // (ex. we've been disabled for a while)
            ChassisSpeeds currentChassisSpeeds = latencyCompedChassisSpeeds;
            lastRequestedVelocity =
                    new Translation2d(
                            currentChassisSpeeds.vxMetersPerSecond,
                            currentChassisSpeeds.vyMetersPerSecond);

            lastRequestedRotation = currentChassisSpeeds.omegaRadiansPerSecond;
            dt = (double) 20 / 1000;
        } else {
            dt = Timer.getFPGATimestamp() - lastLoopTime;
        }
        lastLoopTime = Timer.getFPGATimestamp();

        double maxVelocityChange = accelerationLimit * dt;
        double maxAngularVelocityChange = maxAngularAccel * dt;

        // field relative
        Translation2d velocityCommand =
                toFieldRelative(
                        new Translation2d(
                                commandedVelocity.vxMetersPerSecond,
                                commandedVelocity.vyMetersPerSecond),
                        gyroAngle);

        Translation2d velocityChange = velocityCommand.minus(lastRequestedVelocity);
        double velocityChangeAngle =
                Math.atan2(velocityChange.getY(), velocityChange.getX()); // Radians

        Translation2d limitedVelocityVectorChange = velocityChange;
        Translation2d limitedVelocityVector = velocityCommand;
        // Check if velocity change exceeds max limit
        if (velocityChange.getNorm() > maxVelocityChange) {
            // Get limited velocity vector difference in cartesian coordinate system
            limitedVelocityVectorChange =
                    new Translation2d(maxVelocityChange, new Rotation2d(velocityChangeAngle));
            limitedVelocityVector = lastRequestedVelocity.plus(limitedVelocityVectorChange);

            // robot relative
            Translation2d limitedVelocityVectorRotated =
                    toRobotRelative(limitedVelocityVector, gyroAngle);

            commandedVelocity.vxMetersPerSecond = limitedVelocityVectorRotated.getX();
            commandedVelocity.vyMetersPerSecond = limitedVelocityVectorRotated.getY();
        }

        // Checks if requested change in Angular Velocity is greater than allowed
        if (Math.abs(commandedVelocity.omegaRadiansPerSecond - lastRequestedRotation)
                > maxAngularVelocityChange) {
            // Add the lastCommandVelocity and the maxAngularVelocityChange (changed to have the
            // same sign as the actual change)
            commandedVelocity.omegaRadiansPerSecond =
                    lastRequestedRotation
                            + Math.copySign(
                                    maxAngularVelocityChange,
                                    commandedVelocity.omegaRadiansPerSecond
                                            - lastRequestedRotation);
        }

        // save our current commanded velocity to be used in next iteration
        lastRequestedRotation = commandedVelocity.omegaRadiansPerSecond;
        lastRequestedVelocity = limitedVelocityVector; // field

        return limitedVelocityVectorChange; // field
    }

    /**
     * Converts a field relative velocity to be robot relative
     *
     * @param fieldRelativeTranslation a field relative velocity
     * @return a robot relative velocity
     */
    private static Translation2d toRobotRelative(
            Translation2d fieldRelativeTranslation, Rotation2d gyroAngle) {
        return fieldRelativeTranslation.rotateBy(gyroAngle.unaryMinus());
    }

    /**
     * Converts a robot relative velocity to be field relative
     *
     * @param robotRelativeTranslation a field relative velocity
     * @return a field relative velocity
     */
    private static Translation2d toFieldRelative(
            Translation2d robotRelativeTranslation, Rotation2d gyroAngle) {
        return robotRelativeTranslation.rotateBy(gyroAngle);
    }
}
