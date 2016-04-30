package pt.ua.sd.RopeGame.shared_mem.RepoSide;

import pt.ua.sd.RopeGame.comInfo.Message;
import pt.ua.sd.RopeGame.comInfo.MessageExcept;

/**
 * Central Information Repository Proxy
 */
 class RepoProxy extends Thread {

    /**
     * Count released threads
     * @serialField nInstance
     */
    private static int nInstance;

    /**
     * Communication channel
     * @serialField sc
     */
    private final ServerComm sc;

    /**
     * Interface to central information repository
     * @serialField repoInterface
     */
    private final RepoInterface repoInterface;

    /**
     * Central information repository constructor method
     * @param sc Server communication channel
     * @param repoInterface Interface to central repository
     */
     RepoProxy(ServerComm sc, RepoInterface repoInterface){
        super("Central_repo_Proxy_" + getProxyId());
        this.sc = sc;
        this.repoInterface = repoInterface;

    }

    /**
     * Gets proxy id
     * @return Proxy id
     */
    private static int getProxyId() {

        Class<pt.ua.sd.RopeGame.shared_mem.RepoSide.RepoProxy> cl = null;
        int proxyId;

        try {
            cl = (Class<pt.ua.sd.RopeGame.shared_mem.RepoSide.RepoProxy>)
                    Class.forName("pt.ua.sd.RopeGame.shared_mem.RepoSide.RepoProxy");
        } catch (ClassNotFoundException e) {
            System.out.println("The data type RepoProxy wasn't found!");
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
     * Life cycle of the thread
     */
    @Override
    public void run() {

        Message inMessage;
        Message outMessage = null;

        /*  read input message  */
        inMessage = (Message) sc.readObject();

        /*  process and reply to the input message  */
        try {
            outMessage = repoInterface.processAndReply(inMessage);
        } catch (MessageExcept e) {
            System.out.println("Thread " + getName() + ": " + e.getMessage() + "!");
            System.exit(1);
        }

        /*  send the reply message and close the communication channel  */
        sc.writeObject(outMessage);
        sc.close();
    }
}
