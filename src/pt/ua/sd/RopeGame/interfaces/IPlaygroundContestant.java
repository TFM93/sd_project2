package pt.ua.sd.RopeGame.interfaces;


public interface IPlaygroundContestant {

    void pullTheRope(int team_id, int strenght, int contestant_id, int n_players_pushing, int n_players);

    void iAmDone(int n_players_pushing);

    void seatDown(int n_players_pushing);
}
