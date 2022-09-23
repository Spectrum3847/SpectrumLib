package frc.SpectrumLib.telemetry;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.shuffleboard.EventImportance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

/**
 * Based on Team 1114 code from 2015
 * 
 * You can turn off all debug messages easily. You can also set different Flag levels.
 */
public class Logger {

	public static final int low1 = 1;
	public static final int normal2 = 2;
	public static final int high3 = 3;
	public static final int critical4 = 4;
	public static final int silent5 = 5; //Nothing is printed
	
	private static ArrayList<String> currFlags; 
    private static boolean defaultOn;
    private static int currLevel;
    
    static {
        currFlags = new ArrayList<>();
        currLevel = 1;
        defaultOn = false;
    }
    
    public static void println(String msg) {
        if(defaultOn) {
            System.out.println("[DEBUG] " + msg);
        }
    }
    
    /**
     * Check if we should print the message based on flag and level
     * Print the level and flag as well
     * If the level is error or above also send it to DS output
     * @param msg
     * @param flag
     * @param level
     */
    public static void println(String msg, String flag, int level) {
        //Log events to both system.out and Shuffleboard Event Markers
        if(meetsCurrRequirements(flag, level)) {
            System.out.println(level + ": [" + flag + "] " + msg);
            Shuffleboard.addEventMarker(flag + ":  " + msg, logLevelToEventImportance(level));
        }
        //Critical are sent to the Driver station no matter what the logger level is set to
        if (level == critical4){
            DriverStation.reportWarning(flag + ":  " + msg, false);
        }
    }
    
    public static void println(String msg, String flag) {
        println(msg, flag, 0);
    }
    
    public static void println(int msg) {
        println("" + msg);
    }
    
    public static void println(int msg, String flag) {
        println("" + msg, flag);
    }
    
    public static void println(int msg, String flag, int level) {
        println("" + msg, flag, level);
    }
    
    public static void println(double msg) {
        println("" + msg);
    }
    
    public static void println(double msg, String flag) {
        println("" + msg, flag);
    }
    
    public static void println(double msg, String flag, int level) {
        println("" + msg, flag, level);
    }
    
    public static void println(float msg) {
        println("" + msg);
    }
    
    public static void println(float msg, String flag) {
        println("" + msg, flag);
    }
    
    public static void println(float msg, String flag, int level) {
        println("" + msg, flag, level);
    }
    
    public static void println(long msg) {
        println("" + msg);
    }
    
    public static void println(long msg, String flag) {
        println("" + msg, flag);
    }
    
    public static void println(long msg, String flag, int level) {
        println("" + msg, flag, level);
    }
    
    public static void println(boolean msg) {
        println("" + msg);
    }
    
    public static void println(boolean msg, String flag) {
        println("" + msg, flag);
    }
    
    public static void println(boolean msg, String flag, int level) {
        println("" + msg, flag, level);
    }
    
    public static void println(Object msg) {
        println(msg.toString());
    }
    
    public static void println(Object msg, String flag) {
        println(msg.toString(), flag);
    }
    
    public static void println(Object msg, String flag, int level) {
        println(msg.toString(), flag, level);
    }
    
    public static void println(byte msg) {
        println("" + msg);
    }
    
    public static void println(byte msg, String flag) {
        println("" + msg, flag);
    }
    
    public static void println(byte msg, String flag, int level) {
        println("" + msg, flag, level);
    }
    
    public static void println(char msg) {
        println("" + msg);
    }
    
    public static void println(char msg, String flag) {
        println("" + msg, flag);
    }
    
    public static void println(char msg, String flag, int level) {
        println("" + msg, flag, level);
    }
    
    public static void println(char[] msg) {
        println(new String(msg));
    }
    
    public static void println(char[] msg, String flag) {
        println(new String(msg), flag);
    }
    
    public static void println(char[] msg, String flag, int level) {
        println(new String(msg), flag, level);
    }
    
    public static void flagOn(String flag) {
        if(!currFlags.contains(flag)) {
            currFlags.add(flag);
        }
    }
    
    public static void flagOff(String flag) {
        currFlags.remove(flag);
    }
    
    public static void allFlagsOff() {
        currFlags.clear();
    }
    
    public static void defaultOn() {
        defaultOn = true;
    }
    
    public static void defaultOff() {
        defaultOn = false;
    }
    
    public static void setLevel(int level) {
        currLevel = level;
    }  
    
    //Check if the flag is set and if the level is high enough to print
    private static boolean meetsCurrRequirements(String flag, int level) {
        for(int i = 0; i < currFlags.size(); i++) {
            if(((String) currFlags.get(i)).equals(flag) && level >= currLevel) {
                return true;
            }
        }
        return false;
    }

    //Log Level to Shuffleboard Event Importance converter
    private static EventImportance logLevelToEventImportance(int level){
        switch(level){
            case 1: return EventImportance.kLow;
            case 2: return EventImportance.kNormal;
            case 3: return EventImportance.kHigh;
            case 4: return EventImportance.kCritical;
            default: return EventImportance.kTrivial;
        }
    }
    
}
