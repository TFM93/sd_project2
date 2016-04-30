package pt.ua.sd.RopeGame.comInfo;

/**
 * Configuration Message class
 *
 * Specifies the messages supported for configuration communication
 */
public class ConfigurationMessage extends Message {


    /**
     * Serialization key
     * @serialField serialVersionUID
     */
    private static final long serialVersionUID = 200416L;

    /* Messages types */

    /**
     * get repository
     */
    public static final int GETREP=1;

    /**
     * get repository answer
     */
    public static final int GETREP_ANSWER=2;

    /**
     * get referee site
     */
    public static final int GETREF_SITE=3;

    /**
     * get referee site answer
     */
    public static final int GETREF_SITE_ANSWER=4;

    /**
     * get bench
     */
    public static final int GETBENCH=5;

    /**
     * get bench answer
     */
    public static final int GETBENCH_ANSWER=6;

    /**
     * get playground
     */
    public static final int GETPLAYGROUND=7;

    /**
     * get playground answer
     */
    public static final int GETPLAYGROUND_ANSWER=8;

    /**
     * get repository domain
     */
    public static final int GETREP_DOM=9;

    /**
     * get repository domain answer
     */
    public static final int GETREP_ANS_DOM=10;

    /**
     * get referee site domain
     */
    public static final int GETREF_SITE_DOM=11;

    /**
     * get referee site domain answer
     */
    public static final int GETREF_SITE_ANS_DOM=12;

    /**
     * get bench domain
     */
    public static final int GETBENCH_DOM=13;

    /**
     * get bench domain answer
     */
    public static final int GETBENCH_ANS_DOM=14;

    /**
     * get playground domain
     */
    public static final int GETPLAYG_DOM=15;

    /**
     * get playground domain answer
     */
    public static final int GETPLAYG_ANS_DOM=16;

    /**
     * get number of players
     */
    public static final int GET_NPLAYERS=17;

    /**
     * get number of players answer
     */
    public static final int GET_NPLAYERS_ANS=18;

    /**
     * get number of trials
     */
    public static final int GET_NTRIALS=19;

    /**
     * get number of trials answer
     */
    public static final int GET_NTRIALS_ANS=20;

    /**
     * get number of players pushing
     */
    public static final int GET_NPLAYERS_PUSHING=21;

    /**
     * get number of players pushing answer
     */
    public static final int GET_NPLAYERS_PUSHING_ANSWER=22;

    /**
     * get number of games
     */
    public static final int GET_NGAMES=27;

    /**
     * get number of games answers
     */
    public static final int GET_NGAMES_ANS=28;

    /**
     * get knockout differential
     */
    public static final int GET_KNOCK_DIF=23;

    /**
     * get knockout differential answer
     */
    public static final int GET_KNOCK_DIF_ANS=24;

    /**
     * get log file name
     */
    public static final int GET_LOG_FILE_NAME=25;

    /**
     * get log file name answer
     */
    public static final int GET_LOG_FILE_NAME_ANS=26;


    /**
     * Get referee site
     */
    public static final int GET_PLAYGROUND = 39;

    /**
     * Get referee site answer
     */
    public static final int GET_PLAYGROUND_ANS = 40;

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
        if(type == GET_NPLAYERS_PUSHING_ANSWER){
            this.arg2 = arg;
        }
        else if(type == GET_NTRIALS_ANS){
            this.arg3 = arg;
        }
        else if(type == GET_NGAMES_ANS){
            this.arg4=arg;
        }
        else if(type == GET_KNOCK_DIF_ANS){
            this.arg5=arg;
        }
        else
            this.arg1 = arg;
    }

    /**
     * Messages instantiation (log file).
     * @param type message type
     * @param arg log file name
     */
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