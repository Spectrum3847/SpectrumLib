// Created by Spectrum3847

package frc.SpectrumLib.motorControllers;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;

public class TalonFXSetup {

    /**
     * Sets up the TalonFX with the given configuration, enable voltage compensation at 12 volts Use
     * defaultStatusFrames
     *
     * @param motor TalonFX to be configured
     * @param isInverted Whether the TalonFX is inverted
     * @param currentLimit Current Limit of the TalonFX
     */
    public static void defaultSetup(TalonFX motor, boolean isInverted, double currentLimit) {
        motor.configFactoryDefault();
        motor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
        motor.configVoltageCompSaturation(12); // default 12v voltage compensation for motors
        motor.enableVoltageCompensation(true); // enable voltage compensation
        simpleCurrentLimit(motor, currentLimit);
        motor.setInverted(isInverted);
        defaultStatusFrames(motor);
    }

    /**
     * Sets up the TalonFX to use the the config and status frames
     *
     * @param motor TalonFX to set up
     * @param config TalonFXConfiguration to use
     */
    public static void configAllSetup(TalonFX motor, TalonFXConfiguration config) {
        motor.configFactoryDefault();
        motor.configAllSettings(config);
        pidStatusFrames(motor);
    }

    /**
     * Sets up the status frames for the motor controller
     *
     * @param motor the motor controller to set up
     * @param config the configuration to use
     */
    public static void configFollowerSetup(TalonFX motor, TalonFXConfiguration config) {
        motor.configFactoryDefault();
        motor.configAllSettings(config);
        defaultStatusFrames(motor);
    }

    /**
     * Set a simple current limit for a falcon
     *
     * @param motor TalonFX motor
     * @param limit Limit in Amps
     */
    public static void simpleCurrentLimit(TalonFX motor, double limit) {
        SupplyCurrentLimitConfiguration supplyCurrentLimit =
                new SupplyCurrentLimitConfiguration(true, limit, limit, 0.5);
        motor.configSupplyCurrentLimit(supplyCurrentLimit);
    }

    /**
     * Sets up the status frames for a simple motor Default Status Rates are listed here:
     * https://docs.ctre-phoenix.com/en/stable/ch18_CommonAPI.html
     *
     * @param motor the motor controller to set up
     */
    public static void defaultStatusFrames(TalonFX motor) {
        int slowTime = 500;
        motor.setStatusFramePeriod(StatusFrame.Status_1_General, 10);
        motor.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 50);
        motor.setStatusFramePeriod(StatusFrame.Status_4_AinTempVbat, slowTime);
        motor.setStatusFramePeriod(StatusFrame.Status_10_MotionMagic, slowTime);
        motor.setStatusFramePeriod(StatusFrame.Status_12_Feedback1, slowTime);
        motor.setStatusFramePeriod(StatusFrame.Status_13_Base_PIDF0, slowTime);
        motor.setStatusFramePeriod(StatusFrame.Status_14_Turn_PIDF1, slowTime);
        motor.setStatusFramePeriod(StatusFrame.Status_17_Targets1, slowTime);
    }

    /**
     * Sets up the status frames for the motor controller that is using PID,MM, etc Default Status
     * Rates are listed here: https://docs.ctre-phoenix.com/en/stable/ch18_CommonAPI.html
     *
     * @param motor the motor controller to set up
     */
    public static void pidStatusFrames(TalonFX motor) {
        // Default Status Rates are listed here:
        // https://docs.ctre-phoenix.com/en/stable/ch18_CommonAPI.html
        int fastTime = 100;
        int slowTime = 500;
        motor.setStatusFramePeriod(StatusFrame.Status_1_General, 10);
        motor.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 20);
        motor.setStatusFramePeriod(StatusFrame.Status_4_AinTempVbat, fastTime);
        motor.setStatusFramePeriod(StatusFrame.Status_10_MotionMagic, fastTime);
        motor.setStatusFramePeriod(StatusFrame.Status_12_Feedback1, slowTime);
        motor.setStatusFramePeriod(StatusFrame.Status_13_Base_PIDF0, fastTime);
        motor.setStatusFramePeriod(StatusFrame.Status_14_Turn_PIDF1, slowTime);
        motor.setStatusFramePeriod(StatusFrame.Status_17_Targets1, slowTime);
    }
}
