package pt.ua.sd.RopeGame.shared_mem.PlaygroundSide;

import java.io.*;
import java.net.*;

/**
 * Client communication channel
 */
public class ClientComm {


        /**
         *  Communication socket
         *    @serialField commSocket
         */

        private Socket commSocket = null;

        /**
         *  Server Host Name
         *    @serialField serverHostName
         */

        private String serverHostName = null;

        /**
         *  Port Number
         *    @serialField serverPortNumb
         */

        private int serverPortNumb;

        /**
         *  Data Input Stream
         *    @serialField in
         */

        private ObjectInputStream in = null;

        /**
         *  Data Output Stream
         *    @serialField out
         */

        private ObjectOutputStream out = null;

        /**
         *  Instantiation
         *
         *    @param hostName host name
         *    @param portNumb port number
         */

        public ClientComm(String hostName, int portNumb){
            serverHostName = hostName;
            serverPortNumb = portNumb;
        }

        /**
         *  Opening the communication channel.
         *  Socket instantiation and association.
         *  Open streams
         *
         *    @return channel open or not
         */

        public boolean open (){
            boolean success = true;
            SocketAddress serverAddress = new InetSocketAddress(serverHostName, serverPortNumb);

            try{
                commSocket = new Socket();
                commSocket.connect (serverAddress);
            }

            catch (UnknownHostException | NoRouteToHostException e){
                e.printStackTrace ();
                System.exit (1);
            } catch (ConnectException e) {
                if (e.getMessage ().equals ("Connection refused"))
                    success = false;
                else {
                    System.out.println (e.getMessage () + "!");
                    e.printStackTrace ();
                    System.exit (1);
                }
            }
            catch (SocketTimeoutException e) {
                success = false;
            }
            catch (IOException e){
                e.printStackTrace ();
                System.exit (1);
            }

            if (!success) return (false);

            try {
                out = new ObjectOutputStream (commSocket.getOutputStream ());
            }
            catch (IOException e) {
                e.printStackTrace ();
                System.exit (1);
            }

            try {
                in = new ObjectInputStream (commSocket.getInputStream ());
            }
            catch (IOException e) {
                e.printStackTrace ();
                System.exit (1);
            }

            return (true);
        }

    /**
     *  close communication channels
     */

        public void close (){
            try{
                in.close();
            }
            catch (IOException e) {
                e.printStackTrace ();
                System.exit (1);
            }

            try{
                out.close();
            }
            catch (IOException e){
                e.printStackTrace ();
                System.exit (1);
            }

            try{
                commSocket.close();
            }
            catch (IOException e) {
                e.printStackTrace ();
                System.exit (1);
            }
        }

        /**
         *  Read object from comm channel.
         *
         *    @return read object
         */

        public Object readObject (){
            Object fromServer = null;

            try{
                fromServer = in.readObject ();
            } catch (IOException | ClassNotFoundException e){
                e.printStackTrace ();
                System.exit (1);
            }

            return fromServer;
        }

        /**
         *  Object write in comm channel.
         *
         *    @param toServer object to be written
         */

        public void writeObject (Object toServer){

            try{
                out.writeObject (toServer);
            }
            catch (InvalidClassException | NotSerializableException e) {
                e.printStackTrace ();
                System.exit (1);
            } catch (IOException e) {
                e.printStackTrace ();
                System.exit (1);
            }
        }

}
