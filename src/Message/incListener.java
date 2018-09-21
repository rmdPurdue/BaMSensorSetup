package Message;

import com.illposed.osc.*;

import java.util.ArrayList;
import util.ThreadListener;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * The <code>incListener</code> class listens for incoming OSCMessages and stores them in a queue
 * for the mv.Model to unpack and reformat for retransmission.
 *
 * This class is run on a thread within the mv.Model.
 *
 * @author Puja Mittal (puja@purdue.edu)
 *
 */
public class incListener implements Runnable {

    /**
     * Array to store incoming OSC messages that are broadcasted from the dancer.
     */

    private volatile boolean paused = false;
    private OSCPortIn receiver;
    private BlockingQueue queue = null;

    public incListener(BlockingQueue queue) {
        this.queue = queue;
    }

    /**
     * List of ThreadListeners which gets updated whenever a new message enters the incoming queue
     * in the run() method in this class.
     */
    private List<ThreadListener> listeners = new ArrayList<>();

    /**
     * Adds a listener to the ArrayList of listeners so there is a list of all the listeners to loop through.
     *
     * @param toAdd An instance of <code>#util.ThreadListener</code>
     * which is interface which has a method that is triggered whenever data is added to the incomingQueue
     */
    public void addListener(ThreadListener toAdd) {
        listeners.add(toAdd);
        System.out.println("Listener added.");
    }

    @Override
    public void run() {
        try {
            this.receiver = new OSCPortIn(8000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        OSCListener listener = (time, message) -> {
            try {
                if (!paused) {
                    //If queue is full clear it.
                    if(queue.remainingCapacity() == 0) queue.clear();
                    queue.put(message);
                    System.out.println("Incoming: " + message.getAddress() + " " + message.getArguments());
                    listeners.forEach(ThreadListener::incomingDataUpdated);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        String THIS_ADDRESS = "/hub/*";
        receiver.addListener(THIS_ADDRESS, listener);
        receiver.startListening();
    }


    public void stop() {
        if(this.receiver.isListening()) {
            this.receiver.stopListening();
        }
        this.paused = true;
    }

    public void resume() {
        if(!this.receiver.isListening()) {
            this.receiver.startListening();
        }
        this.paused = false;
    }
}
