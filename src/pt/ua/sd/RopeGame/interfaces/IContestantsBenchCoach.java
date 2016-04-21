package pt.ua.sd.RopeGame.interfaces;

public interface IContestantsBenchCoach {
    boolean callContestants(int team_id,int[] selected_contestants, int n_players);

    void informReferee();
}
