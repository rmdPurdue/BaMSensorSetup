package comm;

import util.RemoteDevice;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;

public class DeviceDiscovery implements Runnable {

    private List<DiscoveryListener> listeners = new ArrayList<>();
    public Vector<RemoteDevice> remoteDevices = new Vector<>();
    private Thread worker;
    private AtomicBoolean running = new AtomicBoolean(false);
    private DatagramSocket socket = null;

    public DeviceDiscovery() throws SocketException {
        int receivePort = 8001;
        socket = new DatagramSocket(receivePort);
    }

    public void addListener(DiscoveryListener toAdd) {
        listeners.add(toAdd);
    }

    public void notifyListeners() {
        for (DiscoveryListener listener : listeners)
            listener.remoteDeviceSaidHello();
    }

    public void start() {
        System.out.println("Listening...");
        worker = new Thread(this);
        worker.start();
    }

    public void stop() {
        running.set(false);
    }

    public void interrupt() {
        running.set(false);
        worker.interrupt();
    }

    boolean isRunnning() {
        return running.get();
    }

    public void run() {
        running.set(true);
        while(running.get()) {
            try {
                byte[] buf = new byte[256];

                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                System.out.println("Message received.");

                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                buf = packet.getData();
                String data = new String(buf);
                String macAddress;
                String deviceName;

                if(data.contains("::")) {
                    String parts[] = data.split("::");
                    deviceName = parts[0];
                    macAddress = parts[1];
                    macAddress = macAddress.substring(0,12);
                } else {
                    throw new IllegalArgumentException("String " + data + " does not contain '::'.");
                }

                String message = "Found you!";
                byte[] acknowledgeMessage = message.getBytes();
                packet = new DatagramPacket(acknowledgeMessage, acknowledgeMessage.length, address, port);
                socket.send(packet);

                remoteDevices.addElement(new RemoteDevice(address, macAddress, deviceName));

                notifyListeners();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        socket.close();
    }



}
