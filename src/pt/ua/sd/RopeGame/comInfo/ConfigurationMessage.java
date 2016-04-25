package pt.ua.sd.RopeGame.comInfo;

public class ConfigurationMessage extends Message {


    /**
     * Serialization key
     * @serialField serialVersionUID
     */
    private static final long serialVersionUID = 200416L;

    /* Messages types */

    /**
     * obter dados do repositorio-pedido
     */
    public static final int GETREP=1;

    /**
     * obter dados do repositorio-resposta
     */
    public static final int GETREP_ANSWER=2;

    public static final int GETREF_SITE=3;

    public static final int GETREF_SITE_ANSWER=4;

    public static final int GETBENCH=5;

    public static final int GETBENCH_ANSWER=6;

    public static final int GETPLAYGROUND=7;

    public static final int GETPLAYGROUND_ANSWER=8;

    public static final int GETREP_DOM=9;

    public static final int GETREP_ANS_DOM=10;

    public static final int GETREF_SITE_DOM=11;

    public static final int GETREF_SITE_ANS_DOM=12;

    public static final int GETBENCH_DOM=13;

    public static final int GETBENCH_ANS_DOM=14;

    public static final int GETPLAYG_DOM=15;

    public static final int GETPLAYG_ANS_DOM=16;

    public static final int GET_NPLAYERS=17;

    public static final int GET_NPLAYERS_ANS=18;

    public static final int GET_NTRIALS=19;

    public static final int GET_NTRIALS_ANS=20;

    public static final int GET_NPLAYERS_PUSHING=21;

    public static final int GET_NPLAYERS_PUSHING_ANSWER=22;

    public static final int GET_NGAMES=27;

    public static final int GET_NGAMES_ANS=28;

    public static final int GET_KNOCK_DIF=23;

    public static final int GET_KNOCK_DIF_ANS=24;

    public static final int GET_LOG_FILE_NAME=25;

    public static final int GET_LOG_FILE_NAME_ANS=26;

    public static final int GET_REF_SITE = 29;

    public static final int GET_REF_SITE_ANS=30;

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

    public ConfigurationMessage(int type, String arg){
        super(type);
        this.logFileName=arg;
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