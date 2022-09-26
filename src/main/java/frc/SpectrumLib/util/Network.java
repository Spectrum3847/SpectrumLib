package frc.SpectrumLib.util;

import java.net.*;

/** Common Network Utilties */
public class Network {

    /**
     * Gets the MAC address of the robot
     *
     * @return the MAC address of the robot
     */
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
                System.out.println("MAC address: " + MAC);
                return MAC;
            } catch (UnknownHostException | SocketException | NullPointerException e) {
                MAC = "UNKNOWN";
            }
        }
        return MAC;
    }

    /**
     * Gets the IP address of the robot
     *
     * @return the IP address of the robot
     */
    public static String getIPaddress() {
        InetAddress localHost;
        String IP = "";
        while (IP.equals("")) {
            try {
                localHost = InetAddress.getLocalHost();
                IP = localHost.getHostAddress();
                System.out.println("IP address: " + IP);
                return IP;
            } catch (UnknownHostException e) {
                IP = "UNKNOWN";
            }
        }
        return IP;
    }

    /**
     * Gets the IP Address of the device at the address such as "limelight.local"
     *
     * @return the IP Address of the device
     */
    public static String getIPaddress(String deviceNameAddress) {
        InetAddress localHost;
        String IP = "";
        while (IP.equals("")) {
            try {
                localHost = InetAddress.getByName(deviceNameAddress);
                IP = localHost.getHostAddress();
                System.out.println("IP address: " + IP);
                return IP;
            } catch (UnknownHostException e) {
                IP = "UNKNOWN";
            }
        }
        return IP;
    }
}
