import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Date;

public class MulticastServerThread extends QuoteServerThread {

    private long FIVE_SECONDS = 5000;

    public MulticastServerThread() throws IOException {
        super("MulticastServerThread");
    }

    public void run() {
        while(moreQuotes) {
            try {
                byte[] buf;

                String dString = null;
                if (in == null)
                    dString = new Date().toString();
                else
                    dString = getNextQuote();
                buf = dString.getBytes();

                InetAddress group = InetAddress.getByName("239.0.0.57");
                InetAddress receiver = InetAddress.getByName("10.101.1.6");
                DatagramPacket packet = new DatagramPacket(buf, buf.length, group, 9001);
                socket.send(packet);

                try {
                    sleep((long) (Math.random() * FIVE_SECONDS));
                } catch (InterruptedException e) {
                }

                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                for (byte number : packet.getData()) {
                    System.out.print(number);
                    System.out.print(":");
                }
                System.out.println();

            } catch (IOException e) {
                e.printStackTrace();
                moreQuotes = false;
            }
        }
        socket.close();
    }
}
