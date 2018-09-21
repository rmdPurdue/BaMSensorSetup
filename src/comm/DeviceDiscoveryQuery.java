package comm;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicBoolean;

public class DeviceDiscoveryQuery implements Runnable {

    private Thread worker;
    private AtomicBoolean running = new AtomicBoolean(false);
    private InetAddress multicastIP = InetAddress.getByName("239.0.0.57");
    private DatagramSocket socket = null;
    private int multicastPort = 9001;
    private boolean newDiscovery = true;


    public DeviceDiscoveryQuery() throws UnknownHostException {
    }

    public void start() {
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

    public void unsetNewDiscovery() {
        newDiscovery = false;
    }

    public void resetNewDiscovery() {
        newDiscovery = true;
    }

    public void run() {
        running.set(true);
        while(running.get()) {
            try {
                socket =new DatagramSocket(multicastPort);
                String message = (newDiscovery) ? "New Hello?" : "Hello?";
                byte[] multicastMsg = message.getBytes();
                DatagramPacket packet = new DatagramPacket(multicastMsg, multicastMsg.length, multicastIP, multicastPort);
                socket.send(packet);
                if (newDiscovery) unsetNewDiscovery();

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("Discovery interrupted.");
                    Thread.currentThread().interrupt();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            socket.close();
        }
    }

}
