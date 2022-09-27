package frc.SpectrumLib.telemetry;

import edu.wpi.first.wpilibj.Preferences;
import java.util.Vector;

public class SpectrumPreferences {
    private static SpectrumPreferences instance;
    private static boolean reset = false;

    private SpectrumPreferences() {}

    /**
     * Returns the preferences instance.
     *
     * @return the preferences instance
     */
    public static synchronized SpectrumPreferences getInstance() {
        if (instance == null) {
            instance = new SpectrumPreferences();
        }
        return instance;
    }

    private static boolean checkForKey(String key) {
        if (Preferences.containsKey(key) && reset == false) return true;
        else {
            return false;
        }
    }

    /**
     * adds the given string into the preferences table if it's not there already.
     *
     * @param key the key
     * @param value the value
     * @throws NullPointerException if value is null
     * @return the string
     */
    public static String addString(String key, String value) {
        if (!checkForKey(key)) {
            if (value == null) {
                throw new NullPointerException("Value is null");
            }
            Preferences.setString(key, value);
        }

        return Preferences.getString(key, value);
    }

    /**
     * Adds the given boolean into the preferences table if it's not there already.
     *
     * @param key the key
     * @param value the value
     * @return the boolean
     */
    public static boolean addBoolean(String key, boolean value) {
        if (!checkForKey(key)) {
            Preferences.setBoolean(key, value);
        }

        return Preferences.getBoolean(key, value);
    }

    /**
     * Adds the given double into the preferences table if it's not there already.
     *
     * @param key the key
     * @param value the value
     * @return the value
     */
    public static double addNumber(String key, double value) {
        if (!checkForKey(key)) {
            Preferences.setDouble(key, value);
        }

        return Preferences.getDouble(key, value);
    }

    /**
     * Returns the string at the given key. If this table does not have a value for that position,
     * then the given backup value will be returned.
     *
     * @param key the key
     * @param backup the value to return if none exists in the table
     * @return either the value in the table, or the backup
     */
    public static String getString(String key, String backup) {
        return addString(key, backup);
    }

    /**
     * Returns the number at the given key. If this table does not have a value for that position,
     * then the given backup value will be returned.
     *
     * @param key the key
     * @param backup the value to return if none exists in the table
     * @return either the value in the table, or the backup
     */
    public static double getNumber(String key, double backup) {
        return addNumber(key, backup);
    }

    /**
     * Returns the boolean at the given key. If this table does not have a value for that position,
     * then the given backup value will be returned.
     *
     * @param key the key
     * @param backup the value to return if none exists in the table
     * @return either the value in the table, or the backup
     */
    public static boolean getBoolean(String key, boolean backup) {
        return addBoolean(key, backup);
    }

    // Set the networktables value even if it already exists.
    public static void setNumber(String key, double value) {
        Preferences.setDouble(key, value);
    }

    /**
     * Remove a preference.
     *
     * @param key the key
     */
    public static void remove(String key) {
        Preferences.remove(key);
    }

    /**
     * Returns whether or not there is a key with the given name.
     *
     * @param key the key
     * @return if there is a value at the given key
     */
    public static boolean containsKey(String key) {
        return Preferences.containsKey(key);
    }

    /**
     * Gets the vector of keys.
     *
     * @return a vector of the keys
     */
    public static Vector<String> getKeys() {
        Vector<String> keys = new Vector<String>();
        keys = (Vector<String>) Preferences.getKeys();
        return keys;
    }
}
