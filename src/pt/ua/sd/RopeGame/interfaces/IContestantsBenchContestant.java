package pt.ua.sd.RopeGame.interfaces;

public interface IContestantsBenchContestant {

    boolean[] followCoachAdvice(int contestant_id,int strength, int team_id, int n_players, int n_players_pushing);

    void getReady(int n_players_pushing);


}
