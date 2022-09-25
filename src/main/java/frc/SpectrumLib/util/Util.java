// Created by Spectrum3847 based on FRC#254 Util code
package frc.SpectrumLib.util;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.Timer;
import java.net.*;
import java.util.List;

/** Contains basic functions that are used often. */
public class Util {
    // Prevent this class from being instantiated.
    private Util() {}

    /**
     * Limits the given input to the given magnitude.
     *
     * @param v The input value.
     * @param limit The limit magnitude.
     * @return The limited value.
     */
    public static double limit(double v, double limit) {
        return limit(v, limit, -limit);
        // return (Math.abs(v) < limit) ? v : limit * (v < 0 ? -1 : 1);
    }

    /**
     * limits input to the max and min
     *
     * @param v - input
     * @param max - max value
     * @param min - min value
     * @return - limited value
     */
    public static double limit(double v, double max, double min) {
        return (v > max) ? max : ((v < min) ? min : v);
    }

    public static String joinStrings(String delim, List<?> strings) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.size(); ++i) {
            sb.append(strings.get(i).toString());
            if (i < strings.size() - 1) {
                sb.append(delim);
            }
        }
        return sb.toString();
    }

    // In seconds
    public static double getTime() {
        return Timer.getFPGATimestamp();
    }

    public static boolean checkBattery(double minVoltage) {
        return (RobotController.getInputVoltage() < minVoltage);
    }

    public static boolean closeTo(double a, double b, double epsilon) {
        return epsilonEquals(a, b, epsilon);
    }

    public static boolean epsilonEquals(double a, double b, double epsilon) {
        return (a - epsilon <= b) && (a + epsilon >= b);
    }

    public static boolean allCloseTo(List<Double> list, double value, double epsilon) {
        boolean result = true;
        for (Double value_in : list) {
            result &= epsilonEquals(value_in, value, epsilon);
        }
        return result;
    }

    public static double standardDeviation(double[] arr) {
        double mean = 0.0;
        double[] temp = new double[arr.length];

        mean = mean(arr);

        for (int i = 0; i < temp.length; i++) {
            temp[i] = Math.pow((arr[i] - mean), 2);
        }

        return Math.sqrt(mean(temp));
    }

    public static double mean(double[] arr) {
        double sum = 0.0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        return sum / arr.length;
    }

    public static double max(double[] arr) {
        double max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        return max;
    }

    public static double powKeepSign(double v, double p) {
        return Math.signum(v) * Math.abs(Math.pow(v, p));
    }

    public static String getMACaddress() {
        InetAddress localHost;
        NetworkInterface ni;
        byte[] hardwareAddress;
        String MAC = "";
        while (MAC.equals("")) {
            try {
                localHost = InetAddress.getLocalHost();
                ni = NetworkInterface.getByInetAddress(localHost);
                hardwareAddress = ni.getHardwareAddress();
                String[] hexadecimal = new String[hardwareAddress.length];
                for (int i = 0; i < hardwareAddress.length; i++) {
                    hexadecimal[i] = String.format("%02X", hardwareAddress[i]);
                }
                MAC = String.join(":", hexadecimal);
                return MAC;
            } catch (UnknownHostException | SocketException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
