package mv;

import comm.DeviceDiscovery;
import comm.DeviceDiscoveryQuery;
import comm.DiscoveryListener;
import util.RemoteDevice;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Model implements DiscoveryListener {

    DeviceDiscoveryQuery discoveryQuery;
    DeviceDiscovery discovery;

    public void Model() {

    }

    public void discoverDevices() throws IOException {
        long discoveryTimeout = 60000;

        discovery = new DeviceDiscovery();
        discovery.start();
        discovery.addListener(this);

        discoveryQuery = new DeviceDiscoveryQuery();
        discoveryQuery.start();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Stopping discovery.");
                discoveryQuery.interrupt();
            }
        }, discoveryTimeout);
    }

    @Override
    public void remoteDeviceSaidHello() {
        System.out.println("Number of discovered devices: " + discovery.remoteDevices.size());
        System.out.println("Devices:");
        for(RemoteDevice device : discovery.remoteDevices) {
            System.out.println(device.getDeviceName());
            System.out.println(device.getIpAddress().toString());
            System.out.println(device.getMacAddress());
        }
        System.out.println();
    }


}
