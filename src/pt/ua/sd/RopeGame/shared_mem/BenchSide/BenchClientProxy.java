package pt.ua.sd.RopeGame.shared_mem.BenchSide;

import pt.ua.sd.RopeGame.comInfo.Message;
import pt.ua.sd.RopeGame.comInfo.MessageExcept;

/**
 * Created by ivosilva on 25/04/16.
 */
public class BenchClientProxy extends Thread{

    /**
     * Counter of released threads
     * @serialField nProxy
     */
    private static int nProxy;

    /**
     * Communication channel
     * @serialField sconi
     */
    private final ServerComm sconi;

    /**
     * Interface to Referee Site
     * @serialField refSiteInterface
     */
    private final BenchSideInterface benchSideInterface;

    /**
     * Instantiation of the ref site client proxy.
     * @param sconi communication channel
     * @param benchSideInterface interface to referee site
     */
    public BenchClientProxy(ServerComm sconi, BenchSideInterface benchSideInterface) {
        super("Ref_Site_Proxy_" + getProxyId());
        this.sconi = sconi;
        this.benchSideInterface = benchSideInterface;
    }

    /**
     * Life cycle of the agent service provider thread.
     */
    @Override
    public void run() {

        Message inMessage = null;   // In message
        Message outMessage = null;  // Out message

        // Read input message
        inMessage = (Message) sconi.readObject();

        // Process message
        try {
            outMessage = benchSideInterface.processAndReply(inMessage);
        } catch (MessageExcept e) {
            System.out.println("Thread " + getName() + ": " + e.getMessage() + "!");
            System.exit(1);
        }

        // Send answer to client and close communication
        sconi.writeObject(outMessage);
        sconi.close();
    }

    /**
     * Generation of instantiation identifier.
     * @return instantiation identifier
     */
    private static int getProxyId() {

        Class<pt.ua.sd.RopeGame.shared_mem.BenchSide.BenchClientProxy> cl = null;
        int proxyId;                            // intantiation identifier

        try {
            cl = (Class<pt.ua.sd.RopeGame.shared_mem.BenchSide.BenchClientProxy>) Class.forName("pt.ua.sd.RopeGame.shared_mem.BenchSide.BenchClientProxy");
        } catch (ClassNotFoundException e) {
            System.out.println("The data type BenchClientProxy wasn't found!");
            e.printStackTrace();
            System.exit(1);
        }

        synchronized (cl) {
            proxyId = nProxy;
            nProxy += 1;
        }

        return proxyId;
    }

}
