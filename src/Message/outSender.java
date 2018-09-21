package Message;

import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCPortOut;
import comm.OutgoingData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * The <code>incListener</code> class listens for incoming OSCMessages and stores them in a queue
 * for the Model to unpack and reformat for retransmission.
 *
 * This class is run on a thread within the Model.
 *
 * @author Puja Mittal (puja@purdue.edu)
 *
 */
public class outSender implements Runnable {

    private BlockingQueue<OutgoingData> queue = null;


    public outSender(BlockingQueue<OutgoingData> queue) {
        this.queue = queue;
    }

    public void run() {
        while(true) {
            try {
                OutgoingData temp = queue.take();
                List<Object> args = new ArrayList<>();
                args.add(temp.getData());
                OSCMessage msg = new OSCMessage(temp.getURL(), args);
                try {
                    OSCPortOut sender = new OSCPortOut(temp.getIpAddress(), temp.getPortNumber());
                    System.out.println(temp.getURL() + ": " + args);
                    sender.send(msg);
                    sender.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
