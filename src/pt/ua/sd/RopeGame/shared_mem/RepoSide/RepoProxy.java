package pt.ua.sd.RopeGame.shared_mem.RepoSide;

import pt.ua.sd.RopeGame.comInfo.Message;
import pt.ua.sd.RopeGame.comInfo.MessageExcept;

public class RepoProxy extends Thread {

    /**
     * counter of released threads
     * @serialField nInstance
     */
    private static int nInstance;

    /**
     * Communication channel
     * @serialField sc
     */
    private final ServerComm sc;

    /**
     * Interface to Central Repo Site
     * @serialField repoInterface
     */
    private final RepoInterface repoInterface;

    /**
     * Instantiation of the central info repo client proxy
     * @param sc server communication channel
     * @param repoInterface interface to central repository
     */
    public RepoProxy(ServerComm sc, RepoInterface repoInterface){
        super("Central_repo_Proxy_" + getProxyId());
        this.sc =sc;
        this.repoInterface=repoInterface;

    }

    /**
     * Generation of instantiation identifier.
     * @return instantiation identifier
     */
    private static int getProxyId() {

        Class<pt.ua.sd.RopeGame.shared_mem.RepoSide.RepoProxy> cl = null;
        int proxyId;                            // intantiation identifier

        try {
            cl = (Class<pt.ua.sd.RopeGame.shared_mem.RepoSide.RepoProxy>) Class.forName("pt.ua.sd.RopeGame.shared_mem.RepoSide.RepoProxy");
        } catch (ClassNotFoundException e) {
            System.out.println("The data type PlaygroundClientProxy wasn't found!");
            e.printStackTrace();
            System.exit(1);
        }

        synchronized (cl) {
            proxyId = nInstance;
            nInstance += 1;
        }

        return proxyId;
    }

    /**
     * Life cycle of the agent service provider thread.
     */
    @Override
    public void run() {

        Message inMessage;   // In message
        Message outMessage = null;  // Out message

        // Read input message
        inMessage = (Message) sc.readObject();

        // Process message
        try {
            outMessage = repoInterface.processAndReply(inMessage);
        } catch (MessageExcept e) {
            System.out.println("Thread " + getName() + ": " + e.getMessage() + "!");
            System.exit(1);
        }

        // Send answer to client and close communication
        sc.writeObject(outMessage);
        sc.close();
    }
}
