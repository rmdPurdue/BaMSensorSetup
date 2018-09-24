package util;

import java.net.InetAddress;
import java.util.ArrayList;

public class RemoteDevice {

    private InetAddress ipAddress;
    private String macAddress;
    private String deviceName;
    private ArrayList<AnalogInput> analogInputs;

    public RemoteDevice() {
        this.analogInputs = new ArrayList<>();
    }

    public RemoteDevice(InetAddress ipAddress, String macAddress, String deviceName) {
        this.ipAddress = ipAddress;
        this.macAddress = macAddress;
        this.deviceName = deviceName;
        this.analogInputs = new ArrayList<>();
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

    public ArrayList<AnalogInput> getAnalogInputs() {
        return analogInputs;
    }

    public void setAnalogInputs(ArrayList<AnalogInput> analogInputs) {
        this.analogInputs = analogInputs;
    }

    public int getNumberOfInputs() {
        return analogInputs.size();
    }

    public void addAnalogInput(int inputNumber, int minValue, int maxValue, int filterWeight) {
        analogInputs.add(new AnalogInput(inputNumber, minValue, maxValue, filterWeight));
    }

    public AnalogInput getAnalogInput(int index) {
        return analogInputs.get(index);
    }
}
