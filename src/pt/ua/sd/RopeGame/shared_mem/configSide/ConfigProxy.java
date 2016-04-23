package pt.ua.sd.RopeGame.shared_mem.configSide;

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
     * ciclo de vida da thread
     */
    @Override
    public void run(){

    }
}
