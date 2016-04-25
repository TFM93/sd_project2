package pt.ua.sd.RopeGame.shared_mem.configSide;

import pt.ua.sd.RopeGame.comInfo.Message;
import pt.ua.sd.RopeGame.comInfo.MessageExcept;

/**
 * Created by tiagomagalhaes on 23/04/16.
 */
public class ConfigProxy extends Thread{

    /**
     * canal de comunicação
     * @serialField sc
     */
    private final ServerCom sc;


    /**
     * numero de threads lançadas
     * @serialField nInstances
     */
    private static int nInstances;

    public ConfigProxy(ServerCom sc){
        this.sc=sc;
    }

    /**
     * Interface to Configuration
     * @serialField configInterf
     */
    private configInterface configInterf;

    /**
     * Instantiation of the configuration client proxy.
     * @param sc communication channel
     * @param confint interface to Configuration
     */
    public ConfigProxy(ServerCom sc, configInterface confint) {
        super("Configuration_Proxy_" + getProxyId());
        this.sc = sc;
        this.configInterf = confint;
    }



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

        Class<pt.ua.sd.RopeGame.shared_mem.configSide.ConfigProxy> cl = null;
        int proxyId;                            // intantiation identifier

        try {
            cl = (Class<pt.ua.sd.RopeGame.shared_mem.configSide.ConfigProxy>) Class.forName("pt.ua.sd.RopeGame.shared_mem.configSide.ConfigProxy");
        } catch (ClassNotFoundException e) {
            System.out.println("The data type ConfigurationClientProxy wasn't found!");
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
