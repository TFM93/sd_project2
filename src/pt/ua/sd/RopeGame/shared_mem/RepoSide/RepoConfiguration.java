package pt.ua.sd.RopeGame.shared_mem.RepoSide;

public class RepoConfiguration {
    private final String hostName;
    private final int portNumber;
    private final int knockdif;
    private final int ngames;
    private final int ntrials;
    private final int players_pushing;
    private final int nplayers;
    private final int nEntities;


    public RepoConfiguration(String hostName, int portNumb, int nplayers, int players_pushing, int n_trials, int n_games, int knock_dif){

        this.hostName=hostName;
        this.portNumber=portNumb;
        this.nplayers=nplayers;
        this.players_pushing = players_pushing;
        this.ntrials = n_trials;
        this.ngames = n_games;
        this.knockdif= knock_dif;


        this.nEntities=1;

    }

    public String getHostName() {
        return hostName;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public int getKnockdif() {
        return knockdif;
    }

    public int getNgames() {
        return ngames;
    }

    public int getNtrials() {
        return ntrials;
    }

    public int getPlayers_pushing() {
        return players_pushing;
    }

    public int getNplayers() {
        return nplayers;
    }

    public int getnEntities() {
        return nEntities;
    }
}
