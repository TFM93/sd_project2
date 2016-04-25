package pt.ua.sd.RopeGame.comInfo;

public class ConfigurationMessage extends Message {


    /**
     * Serialization key
     * @serialField serialVersionUID
     */
    private static final long serialVersionUID = 200416L;

    /* Messages types */

    /**
     * Get number of players in team
     */
    public static final int N_PLAYERS_TEAM = 1;

    /**
     * Get number of players in team answer
     */
    public static final int N_PLAYERS_TEAM_ANS = 2;

    /**
     * Get number of players pushing the rope
     */
    public static final int N_PLAYERS_PUSHING = 3;

    /**
     * Get number of players pushing the rope answer
     */
    public static final int N_PLAYERS_PUSHING_ANS = 4;

    /**
     * Get number of trials
     */
    public static final int N_TRIALS = 5;

    /**
     * Get number of trials answer
     */
    public static final int N_TRIALS_ANS = 6;

    /**
     * Get number of games
     */
    public static final int N_GAMES = 7;

    /**
     * Get number of games answer
     */
    public static final int N_GAMES_ANS = 8;

    /**
     * Get point difference for knockout win
     */
    public static final int KNOCKOUT_DIF = 9;

    /**
     * Get point difference for knockout win answer
     */
    public static final int KNOCKOUT_DIF_ANS = 10;

    /**
     * Get config host name
     */
    public static final int CONFIG_HOST_NAME = 11;

    /**
     * Get config host name answer
     */
    public static final int CONFIG_HOST_NAME_ANS = 12;

    /**
     * Get repository host name
     */
    public static final int REPO_HOST_NAME = 13;

    /**
     * Get repository host name answer
     */
    public static final int REPO_HOST_NAME_ANS = 14;

    /**
     * Get repository port number
     */
    public static final int REPO_PORT_NUM = 15;

    /**
     * Get repository port number answer
     */
    public static final int REPO_PORT_NUM_ANS = 16;

    /**
     * Get referee site host name
     */
    public static final int REFSITE_HOST_NAME = 17;

    /**
     * Get referee site host name answer
     */
    public static final int REFSITE_HOST_NAME_ANS = 18;

    /**
     * Get referee site port number
     */
    public static final int REFSITE_PORT_NUM = 19;

    /**
     * Get referee site port number answer
     */
    public static final int REFSITE_PORT_NUM_ANS = 20;

    /**
     * Get bench host name
     */
    public static final int BENCH_HOST_NAME = 21;

    /**
     * Get bench host name answer
     */
    public static final int BENCH_HOST_NAME_ANS = 22;

    /**
     * Get bench port number
     */
    public static final int BENCH_PORT_NUM = 23;

    /**
     * Get bench port number answer
     */
    public static final int BENCH_PORT_NUM_ANS = 24;

    /**
     * Get playground host name
     */
    public static final int PLAYG_HOST_NAME = 25;

    /**
     * Get playground host name answer
     */
    public static final int PLAYG_HOST_NAME_ANS = 26;

    /**
     * Get playground port number
     */
    public static final int PLAYG_PORT_NUM = 27;

    /**
     * Get playground port number
     */
    public static final int PLAYG_PORT_NUM_ANS = 28;

    /**
     * Get referee host name
     */
    public static final int REFEREE_HOST_NAME = 29;

    /**
     * Get referee host name answer
     */
    public static final int REFEREE_HOST_NAME_ANS = 30;

    /**
     * Get coach host name
     */
    public static final int COACH_HOST_NAME = 31;

    /**
     * Get coach host name answer
     */
    public static final int COACH_HOST_NAME_ANS = 32;

    /**
     * Get contestant host name
     */
    public static final int CONTESTANT_HOST_NAME = 33;

    /**
     * Get contestant host name answer
     */
    public static final int CONTESTANT_HOST_NAME_ANS = 34;

    /**
     * Get log file name
     */
    public static final int LOG_FILE_NAME = 35;

    /**
     * Get log file name answer
     */
    public static final int LOG_FILE_NAME_ANS = 36;

    /**
     * Get referee site
     */
    public static final int GET_REF_SITE = 37;

    /**
     * Get referee site answer
     */
    public static final int GET_REF_SITE_ANS = 38;

    /* Messages Fields */

    /**
     * Host name
     */
    private String hostName = null;

    /**
     * Port number
     */
    private int portNumb = -1;

    /**
     * Number of players in each team
     */
    private int arg1 = -1;

    /**
     * Number of players pushing in each trial
     */
    private int arg2 = -1;

    /**
     * Number of trials
     */
    private int arg3 = -1;

    /**
     * Number of games in each match
     */
    private int arg4 = -1;

    /**
     * Difference in strength needed to knockout
     */
    private int arg5 = -1;

    /**
     * Log file name
     */
    private String logFileName = null;

    /**
     * Messages Instantiation.
     *
     * @param type message type
     */
    public ConfigurationMessage(int type) {
        super(type);
    }

    /**
     * Messages Instantiation.
     *
     * @param type message type
     * @param arg argument
     */
    public ConfigurationMessage(int type, int arg) {
        super(type);
        this.arg1 = arg;
    }

    /**
     * Messages Instantiation (Domain).
     *
     * @param type message type
     * @param hostName host name
     * @param portNumb port number
     */
    public ConfigurationMessage(int type, String hostName, int portNumb) {
        super(type);
        this.hostName = hostName;
        this.portNumb = portNumb;
    }

    /**
     * Messages Instantiation.
     *
     * @param type message type
     * @param hostName host name
     * @param portNumb port number
     * @param n_players_team number of players in team
     * @param n_players_pushing number of players pushing the rope
     * @param n_trials number of trials
     * @param n_games number of games
     * @param knockout_dif difference needed for knockout win
     * @param logFileName log file name
     */
    public ConfigurationMessage(int type, String hostName, int portNumb, int n_players_team,
                                int n_players_pushing, int n_trials, int n_games, int knockout_dif, String logFileName) {
        super(type);
        this.hostName = hostName;
        this.portNumb = portNumb;
        this.arg1 = n_players_team;
        this.arg2 = n_players_pushing;
        this.arg3 = n_trials;
        this.arg4 = n_games;
        this.arg5 = knockout_dif;
        this.logFileName = logFileName;
    }

    /**
     * Get host name.
     * @return host name
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * Get host port number.
     * @return port number
     */
    public int getPortNumb() {
        return portNumb;
    }

    /**
     * Get arg1 (number of players in team).
     * @return number of players in team
     */
    public int getArg1() {
        return arg1;
    }

    /**
     * Get arg2 (number of players pushing the rope).
     * @return number of players pushing the rope
     */
    public int getArg2() {
        return arg2;
    }

    /**
     * Get arg3 (number of trials).
     * @return number of trials
     */
    public int getArg3() {
        return arg3;
    }

    /**
     * Get arg4 (number of games).
     * @return number of games
     */
    public int getArg4() {
        return arg4;
    }

    /**
     * Get arg4 (difference needed for knockout win).
     * @return difference needed for knockout win
     */
    public int getArg5() {
        return arg5;
    }

    /**
     * Get log file name.
     * @return log file name
     */
    public String getLogFileName() {
        return logFileName;
    }


}