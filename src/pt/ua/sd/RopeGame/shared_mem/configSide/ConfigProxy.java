package pt.ua.sd.RopeGame.shared_mem.ConfigSide;

import pt.ua.sd.RopeGame.comInfo.Message;
import pt.ua.sd.RopeGame.comInfo.MessageExcept;

/**
 * Configuration Proxy
 */
public class ConfigProxy extends Thread{

    /**
     * Communication channel
     * @serialField sc
     */
    private final ServerComChannel sc;


    /**
     * Count released threads
     * @serialField nInstances
     */
    private static int nInstances;

    /**
     * Interface to Configuration
     * @serialField configInterf
     */
    private final ConfigInterface configInterf;



    /**
     * Configuration proxy constructor method
     * @param sc communication channel
     * @param configInterf interface to Configuration
     */
    public ConfigProxy(ServerComChannel sc, ConfigInterface configInterf){
        super("Configuration_Proxy_" + getProxyId());
        this.sc=sc;
        this.configInterf = configInterf;
    }



    /**
     * Life cycle of the thread
     */
    @Override
    public void run(){

        Message inMessage = null;
        Message outMessage = null;

        /*  read input message  */
        inMessage = (Message) sc.readObject();

        /*  process and reply to the input message  */
        try {
            outMessage = configInterf.processAndReply(inMessage);
        } catch (MessageExcept e) {
            System.out.println("Thread " + getName() + ": " + e.getMessage() + "!");
            System.out.println(e.getMsgVal().toString());
            System.exit(1);
        }

        /*  send the reply message and close the communication channel  */
        sc.writeObject(outMessage);
        sc.close();


    }

    /**
     * Gets proxy id
     * @return Proxy id
     */
    private static int getProxyId() {

        Class<pt.ua.sd.RopeGame.shared_mem.ConfigSide.ConfigProxy> cl = null;
        int proxyId;

        try {
            cl = (Class<pt.ua.sd.RopeGame.shared_mem.ConfigSide.ConfigProxy>) Class.forName("pt.ua.sd.RopeGame.shared_mem.ConfigSide.ConfigProxy");
        } catch (ClassNotFoundException e) {
            System.out.println("The data type ConfigProxy wasn't found!");
            e.printStackTrace();
            System.exit(1);
        }

        synchronized (cl) {
            proxyId = nInstances;
            nInstances += 1;
        }

        return proxyId;
    }
}
