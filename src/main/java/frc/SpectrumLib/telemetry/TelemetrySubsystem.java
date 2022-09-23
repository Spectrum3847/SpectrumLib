// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.SpectrumLib.telemetry;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.SpectrumLib.util.Alert;
import frc.SpectrumLib.util.Util;
import frc.SpectrumLib.util.Alert.AlertType;

public class TelemetrySubsystem extends SubsystemBase{
    private static final Notifier slowThread = new Notifier(new telemetrySlowThread());
	static final double FAST_DELAY = 0.04;
    static final double SLOW_DELAY = 0.5;
    static boolean dashboardBlink = true; //Used to slowly toggle a box on the smartdashboard, see if it's connected

    
    protected ShuffleboardTab mainTab;
    
    /***  Alerts ***/
    public static Alert batteryAlert = new Alert("Low Battery", AlertType.WARNING);
    public static Alert FMSConnectedAlert = new Alert("FMS Connected", Alert.AlertType.WARNING);

    public TelemetrySubsystem(){
        slowThread.startPeriodic(SLOW_DELAY);
        mainTab = Shuffleboard.getTab("Main");
    }

    @Override
    public void periodic(){
        checkFMS();
        checkBatteryWhenDisabled();
    }

    //Things that don't need to be sent out each cycle
    private static void updateTelemetrySlow(){
        dashboardBlink = !dashboardBlink;
        SmartDashboard.putBoolean("Blink", dashboardBlink);
    }

    public void checkFMS() {
        FMSConnectedAlert.set(DriverStation.isFMSAttached());
    }

    public void checkBatteryWhenDisabled() {
        if (DriverStation.isDisabled()){
            batteryAlert.set(Util.checkBattery(12.0));
        }
    }

    private static class telemetrySlowThread implements Runnable {    
		@Override
		public void run() {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
			updateTelemetrySlow();
		}
	}
}
    