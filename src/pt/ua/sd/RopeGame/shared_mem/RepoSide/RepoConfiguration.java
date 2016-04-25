package pt.ua.sd.RopeGame.shared_mem.RepoSide;

/**
 * Created by tiagomagalhaes on 25/04/16.
 */
public class RepoConfiguration {
    private final String hostName;
    private final int portNumber;


    public RepoConfiguration(String hostName, int portNumb){

        this.hostName=hostName;
        this.portNumber=portNumb;

    }

    public String getHostName() {
        return hostName;
    }

    public int getPortNumber() {
        return portNumber;
    }
}
