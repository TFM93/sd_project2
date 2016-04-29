package pt.ua.sd.RopeGame.shared_mem.RefereeSiteSide;

import pt.ua.sd.RopeGame.comInfo.Message;
import pt.ua.sd.RopeGame.comInfo.MessageExcept;
import pt.ua.sd.RopeGame.shared_mem.ConfigSide.ServerComChannel;

/**
 * Referee Site Proxy
 */
public class RefereeSiteClientProxy extends Thread {

    /**
     * Count released threads
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
    private final RefereeSiteSideInterface refSiteInterface;

    /**
     * Referee Site proxy constructor method
     * @param sconi communication channel
     * @param refSiteInterface interface to referee site
     */
    public RefereeSiteClientProxy(ServerComm sconi, RefereeSiteSideInterface refSiteInterface) {
        super("Ref_Site_Proxy_" + getProxyId());
        this.sconi = sconi;
        this.refSiteInterface = refSiteInterface;
    }

    /**
     * Life cycle of the thread
     */
    @Override
    public void run() {

        Message inMessage = null;   // In message
        Message outMessage = null;  // Out message

        /*  read input message  */
        inMessage = (Message) sconi.readObject();

        /*  process and reply to the input message  */
        try {
            outMessage = refSiteInterface.processAndReply(inMessage);
        } catch (MessageExcept e) {
            System.out.println("Thread " + getName() + ": " + e.getMessage() + "!");
            System.exit(1);
        }

        /*  send the reply message and close the communication channel  */
        sconi.writeObject(outMessage);
        sconi.close();
    }

    /**
     * Gets proxy id
     * @return Proxy id
     */
    private static int getProxyId() {

        Class<pt.ua.sd.RopeGame.shared_mem.RefereeSiteSide.RefereeSiteClientProxy> cl = null;
        int proxyId;

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
