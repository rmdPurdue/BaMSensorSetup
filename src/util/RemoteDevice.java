package util;

import java.net.InetAddress;

public class RemoteDevice {

    private InetAddress ipAddress;
    private String macAddress;
    private String deviceName;
    private int numberOfInputs;

    public RemoteDevice() {
    }

    public RemoteDevice(InetAddress ipAddress, String macAddress, String deviceName) {
        this.ipAddress = ipAddress;
        this.macAddress = macAddress;
        this.deviceName = deviceName;
    }

    public InetAddress getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(InetAddress ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
