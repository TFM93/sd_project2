package pt.ua.sd.RopeGame.shared_mem.BenchSide;

import pt.ua.sd.RopeGame.comInfo.Message;
import pt.ua.sd.RopeGame.comInfo.MessageExcept;

/**
 * Central Information Repository Proxy
 */
public class BenchClientProxy extends Thread{

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
     * Interface to Bench
     * @serialField benchSideInterface
     */
    private final BenchSideInterface benchSideInterface;

    /**
     * Bench Proxy constructor method
     * @param sconi Server communication channel
     * @param benchSideInterface Interface to central repository
     */
    public BenchClientProxy(ServerComm sconi, BenchSideInterface benchSideInterface) {
        super("Bench_Proxy_" + getProxyId());
        this.sconi = sconi;
        this.benchSideInterface = benchSideInterface;
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
            outMessage = benchSideInterface.processAndReply(inMessage);
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

        Class<pt.ua.sd.RopeGame.shared_mem.BenchSide.BenchClientProxy> cl = null;
        int proxyId;

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
