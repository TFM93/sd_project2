package pt.ua.sd.RopeGame.shared_mem.RefereeSiteSide;

import pt.ua.sd.RopeGame.comInfo.Message;
import pt.ua.sd.RopeGame.comInfo.MessageExcept;
import pt.ua.sd.RopeGame.shared_mem.configSide.ServerCom;

/**
 * Created by ivosilva on 24/04/16.
 */
public class RefereeSiteClientProxy extends Thread {

    /**
     * Counter of released threads
     * @serialField nProxy
     */
    private static int nProxy;

    /**
     * Communication channel
     * @serialField sconi
     */
    private final ServerCom sconi;

    /**
     * Interface to Referee Site
     * @serialField refSiteInterface
     */
    private final RefereeSiteSideInterface refSiteInterface;

    /**
     * Instantiation of the ref site client proxy.
     * @param sconi communication channel
     * @param refSiteInterface interface to referee site
     */
    public RefereeSiteClientProxy(ServerCom sconi, RefereeSiteSideInterface refSiteInterface) {
        super("Ref_Site_Proxy_" + getProxyId());
        this.sconi = sconi;
        this.refSiteInterface = refSiteInterface;
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
            outMessage = refSiteInterface.processAndReply(inMessage);
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

        Class<pt.ua.sd.RopeGame.shared_mem.RefereeSiteSide.RefereeSiteClientProxy> cl = null;
        int proxyId;                            // intantiation identifier

        try {
            cl = (Class<pt.ua.sd.RopeGame.shared_mem.RefereeSiteSide.RefereeSiteClientProxy>) Class.forName("pt.ua.sd.RopeGame.shared_mem.RefereeSiteSide.RefereeSiteClientProxy");
        } catch (ClassNotFoundException e) {
            System.out.println("The data type RefereeSiteClientProxy wasn't found!");
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
