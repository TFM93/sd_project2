package pt.ua.sd.RopeGame.shared_mem.RefereeSiteSide;

import java.io.*;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ServerComm {

    /**
     * Listening socket
     *
     * @serialField listeningSocket
     */

    private ServerSocket listeningSocket = null;

    /**
     * Communication socket
     *
     * @serialField commSocket
     */
    private Socket commSocket = null;

    /**
     * Port number
     *
     * @serialField serverPortNumb
     */
    private int serverPortNumb;

    /**
     * Object Input Strema
     *
     * @serialField in
     */
    private ObjectInputStream in = null;

    /**
     * Object Output Stream
     *
     * @serialField out
     */
    private ObjectOutputStream out = null;

    /**
     * Communication channel instantiation
     *
     * @param portNumb port number
     */
    public ServerComm(int portNumb) {
        serverPortNumb = portNumb;
    }

    /**
     * Communication channel instantiation
     *
     * @param portNumb port number
     * @param lSocket listening socket
     */
    public ServerComm(int portNumb, ServerSocket lSocket) {
        serverPortNumb = portNumb;
        listeningSocket = lSocket;
    }

    /**
     * Binding to public listening ports and address association
     */
    public void start() {
        try {
            listeningSocket = new ServerSocket(serverPortNumb);
        } catch (BindException e){
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Close listening socket
     */
    public void end(){
        try {
            listeningSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Listening process
     *
     * @return communication channel
     */
    public ServerComm accept() {
        ServerComm scon;

        scon = new ServerComm(serverPortNumb, listeningSocket);
        try {
            scon.commSocket = listeningSocket.accept();
        } catch (SocketException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            scon.in = new ObjectInputStream(scon.commSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            scon.out = new ObjectOutputStream(scon.commSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return scon;
    }

    /**
     * Close the communication channel
     */
    public void close() {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            commSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Read object from communication channel
     *
     * @return read object
     */
    public Object readObject() {
        Object fromClient = null;

        try {
            fromClient = in.readObject();
        } catch (InvalidClassException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return fromClient;
    }

    /**
     * Write object in communication channel
     *
     * @param toClient object to be written
     */
    public void writeObject(Object toClient) {
        try {
            out.writeObject(toClient);
        } catch (InvalidClassException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (NotSerializableException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
