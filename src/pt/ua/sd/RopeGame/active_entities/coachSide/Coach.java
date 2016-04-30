package pt.ua.sd.RopeGame.active_entities.coachSide;

import pt.ua.sd.RopeGame.enums.CoachState;


/**
 * Coach thread<br>
 *     This class represents the thread of the coach, her life cycle ends when
 *     the internal flag match_not_over takes the false notation.
 *     Notes:
 *     - the access to the shared memories is limited by the interfaces present in the interfaces package.
 *     - the default selected team to play is the first 3 contestants (id: 0,1 and 2).
 *     - the default state is WAIT_FOR_REFEREE_COMMAND
 *
 * @author Ivo Silva (<a href="mailto:ivosilva@ua.pt">ivosilva@ua.pt</a>)
 * @author Tiago Magalhaes (<a href="mailto:tiagoferreiramagalhaes@ua.pt">tiagoferreiramagalhaes@ua.pt</a>)
 */

 class Coach extends Thread {

    /**
     * Internal Data
     */
    private int id;//represents the id of the coach
    private int team_id;//represents the id of the team
    private int team_selected_contestants[];//each coach has 3 selected contestants to play
    private CoachBenchBroker contestants_bench;//represents the bench shared memory
    private CoachPlaygroundBroker playground;//represents the playground shared memory
    private CoachRepoBroker repo;//represents the general info repository of shared memory
    private int n_players;//number of players in each team, defined in rg.config
    private int n_players_pushing;//number of players in each team pushing at any given trial, defined in rg.config



    /**
     * Constructor
     * @param id current coach id
     * @param team_id current team id
     * @param playground playground shared memory instancy
     * @param contestants_bench contestants bench shared memory instancy
     * @param repo general info repository shared memory instancy
     * @param n_players number of players per team
     * @param n_players_pushing number of players pushing the rope
     */
     Coach(int id, int team_id, CoachPlaygroundBroker playground,
                 CoachBenchBroker contestants_bench, CoachRepoBroker repo, int n_players, int n_players_pushing) {
        this.id = id;
        this.team_id = team_id;
        this.playground = playground;
        this.contestants_bench = contestants_bench;
        this.repo = repo;
        this.n_players = n_players;
        this.n_players_pushing = n_players_pushing;
    }

    /**
     * Thread life cycle
     */
    public void run() {

        CoachState state = CoachState.WAIT_FOR_REFEREE_COMMAND;//initial state

        repo.coachLog(this.team_id, state);//update repo
        boolean match_not_over = true;


        while (match_not_over){//this value can change when coach is in the begining of his cycle(WRC) by callContestants
            switch (state){
                case WAIT_FOR_REFEREE_COMMAND:
                    if (team_selected_contestants == null){
                        team_selected_contestants = new int[n_players_pushing];
                        for(int i = 0; i < n_players_pushing; i++){
                            team_selected_contestants[i] = i;
                        }
                    }
                    match_not_over = this.contestants_bench.callContestants(this.team_id,this.team_selected_contestants, n_players);
                    state = CoachState.ASSEMBLE_TEAM;//change state
                    repo.coachLog(this.team_id, state);//update central info repository
                    break;
                case ASSEMBLE_TEAM:
                    this.contestants_bench.informReferee();
                    state = CoachState.WATCH_TRIAL;
                    repo.coachLog(this.team_id, state);//update central info repository
                    break;
                case WATCH_TRIAL:
                    this.team_selected_contestants = this.playground.reviewNotes(this.team_selected_contestants, n_players, n_players_pushing);
                    state = CoachState.WAIT_FOR_REFEREE_COMMAND;
                    repo.coachLog(this.team_id, state);//update central info repository
                    break;
                default:
                    state= CoachState.WAIT_FOR_REFEREE_COMMAND;//default state
                    repo.coachLog(this.team_id, state);//update central info repository
                    break;
            }
        }

        System.out.println("Coach " + this.id + " finished execution");
    }


    /**
     *
     * @return the {@link Integer} representation of the team id
     */
    public int getTeam_id() {
        return team_id;
    }



}
