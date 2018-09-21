package comm;

import util.*;

import java.net.InetAddress;

import static java.lang.Math.abs;

/**
 * The <code>OutgoingData</code> class builds the outgoing data packet for the
 * application to broadcast.
 *
 * This class is run when a listener on the incomingQueue is triggered within the mv.Model.
 *
 * @author Puja Mittal (puja@purdue.edu)
 *
 */
public class OutgoingData {

    private InetAddress ipAddress;
    private int portNumber;
    private String URL;
    private float data;

    /**
     * Constructor for <code>OutgoingData</code> assigns parameters URL, alg_name, and rawData passed from
     * startProcessing() in <code>mv.Model</code>. This constructor builds the outgoing data message to be
     * passed to the outgoingQueue.
     *
     * It takes in the URL to package it with, the algorithm name which it uses to find the correct algorithm
     * then runs it on the inputted data.
     *
     * @param URL
     * @param alg_name
     * @param rawData
     */
    public OutgoingData(InetAddress ipAddress, int portNumber, String URL, int rawData) {
        this.ipAddress = ipAddress;
        this.portNumber = portNumber;
        this.URL = URL;

        System.out.println(this.data);
    }

    public InetAddress getIpAddress () {
        return ipAddress;
    }

    public int getPortNumber() {
        return portNumber;
    }

    /**
     * @return String that is the URL the data will be broadcasted to
     */
    public String getURL() {
        return URL;
    }

    /**
     * @return int that is the raw data sent by the dancer
     */
    public float getData() { return data; }
}
