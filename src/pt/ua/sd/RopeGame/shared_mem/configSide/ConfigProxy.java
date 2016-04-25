package pt.ua.sd.RopeGame.shared_mem.ConfigSide;

import pt.ua.sd.RopeGame.comInfo.Message;
import pt.ua.sd.RopeGame.comInfo.MessageExcept;

public class ConfigProxy extends Thread{

    /**
     * canal de comunicação
     * @serialField sc
     */
    private final ServerComChannel sc;


    /**
     * numero de threads lançadas
     * @serialField nInstances
     */
    private static int nInstances;

    /**
     * Instantiation of the configuration client proxy.
     * @param sc communication channel
     * @param configInterf interface to Configuration
     */
    public ConfigProxy(ServerComChannel sc, ConfigInterface configInterf){
        super("Configuration_Proxy_" + getProxyId());
        this.sc=sc;
        this.configInterf = configInterf;
    }

    /**
     * Interface to Configuration
     * @serialField configInterf
     */
    private final ConfigInterface configInterf;





    /**
     * ciclo de vida da thread
     */
    @Override
    public void run(){

        Message entrada;
        Message saida = null;

        entrada = (Message) sc.readObject();


        // Process message
        try {
            saida = configInterf.processAndReply(entrada);
        } catch (MessageExcept e) {
            System.out.println("Thread " + getName() + ": " + e.getMessage() + "!");
            System.out.println(e.getMsgVal().toString());
            System.exit(1);
        }

        // Send answer to client and close communication
        sc.writeObject(saida);
        sc.close();


    }

    /**
     * Generation of instantiation identifier.
     * @return instantiation identifier
     */
    private static int getProxyId() {

        Class<pt.ua.sd.RopeGame.shared_mem.ConfigSide.ConfigProxy> cl = null;
        int proxyId;                            // intantiation identifier

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
