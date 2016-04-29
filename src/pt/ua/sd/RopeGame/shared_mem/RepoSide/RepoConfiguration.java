package pt.ua.sd.RopeGame.shared_mem.RepoSide;

/**
 * Central Information Repository configuration class
 */
public class RepoConfiguration {
    /**
     * Server host name
     */
    private final String hostName;
    /**
     * Server port number
     */
    private final int portNumber;
    /**
     * Point differential needed to win by knockout
     */
    private final int knockdif;
    /**
     * Number of games in a match
     */
    private final int ngames;
    /**
     * Number of trials in a game
     */
    private final int ntrials;
    /**
     * Number of players pushing in each trial
     */
    private final int players_pushing;
    /**
     * Number of players in each team
     */
    private final int nplayers;
    /**
     * Number of entities of the repository
     */
    private final int nEntities;

    /**
     * Central Information Repository configuration
     * @param hostName Server host name
     * @param portNumb Server port number
     * @param nplayers Number of players in each team
     * @param players_pushing Number of players pushing in each trial
     * @param n_trials Number of trials in a game
     * @param n_games Number of games in a match
     * @param knock_dif Point differential needed to win by knockout
     */
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

    /**
     * Get host name
     * @return host name
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * Get port number
     * @return port number
     */
    public int getPortNumber() {
        return portNumber;
    }

    /**
     * Get knockout difference
     * @return knockout difference
     */
    public int getKnockdif() {
        return knockdif;
    }

    /**
     * Get number of games
     * @return number of games
     */
    public int getNgames() {
        return ngames;
    }

    /**
     * Get number of trials
     * @return number of trials
     */
    public int getNtrials() {
        return ntrials;
    }

    /**
     * Get number of players pushing the rope
     * @return number of players pushing the rope
     */
    public int getPlayers_pushing() {
        return players_pushing;
    }

    /**
     * Get number of players
     * @return number of players
     */
    public int getNplayers() {
        return nplayers;
    }

    /**
     * Get number of entities
     * @return number of entities
     */
    public int getnEntities() {
        return nEntities;
    }
}
