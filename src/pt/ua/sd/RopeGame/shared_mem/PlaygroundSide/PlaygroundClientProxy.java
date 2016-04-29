package pt.ua.sd.RopeGame.shared_mem.PlaygroundSide;

import pt.ua.sd.RopeGame.comInfo.Message;
import pt.ua.sd.RopeGame.comInfo.MessageExcept;

/**
 * Playground Proxy
 */
public class PlaygroundClientProxy extends Thread{

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
     * Interface to Playground
     * @serialField refSiteInterface
     */
    private final PlaygroundSideInterface playgroundInterface;

    /**
     * Playground proxy constructor method
     * @param sconi communication channel
     * @param playgroundInterface interface to referee site
     */
    public PlaygroundClientProxy(ServerComm sconi, PlaygroundSideInterface playgroundInterface) {
        super("Playground_Proxy_" + getProxyId());
        this.sconi = sconi;
        this.playgroundInterface = playgroundInterface;
    }

    /**
     * Life cycle of the thread
     */
    @Override
    public void run() {

        Message inMessage = null;
        Message outMessage = null;

        /*  read input message  */
        inMessage = (Message) sconi.readObject();

        /*  process and reply to the input message  */
        try {
            outMessage = playgroundInterface.processAndReply(inMessage);
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

        Class<pt.ua.sd.RopeGame.shared_mem.PlaygroundSide.PlaygroundClientProxy> cl = null;
        int proxyId;

        try {
            cl = (Class<pt.ua.sd.RopeGame.shared_mem.PlaygroundSide.PlaygroundClientProxy>) Class.forName("pt.ua.sd.RopeGame.shared_mem.PlaygroundSide.PlaygroundClientProxy");
        } catch (ClassNotFoundException e) {
            System.out.println("The data type PlaygroundClientProxy wasn't found!");
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
