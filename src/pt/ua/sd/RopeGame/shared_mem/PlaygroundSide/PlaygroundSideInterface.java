package pt.ua.sd.RopeGame.shared_mem.PlaygroundSide;
import pt.ua.sd.RopeGame.comInfo.*;
import pt.ua.sd.RopeGame.structures.TrialStat;

/**
 * Interface to the Central Information Repository
 */
public class PlaygroundSideInterface {

    /**
     * Playground
     * @serialField playground
     */
    private final MPlayground playground;

    /**
     * Number of TERMINATE messages received
     * @serialField n_terminates
     */
    private int n_terminates;

    /**
     * Number of TERMINATE messages left
     * @serialField n_terminates_to_end
     */
    private final int n_terminates_to_end;

    /**
     * Playground interface constructor method
     * @param playground playground
     * @param n_terminates_to_end number of TERMINATE messages left
     */
    public PlaygroundSideInterface(MPlayground playground, int n_terminates_to_end) {
        this.playground = playground;
        this.n_terminates = 0;
        this.n_terminates_to_end = n_terminates_to_end;
    }

    /**
     * Process and reply the incoming messages
     * @param inMessage Incoming message
     * @return Outgoing message
     * @throws MessageExcept Exception that shows that the incoming message is not valid
     */
    public Message processAndReply (Message inMessage) throws MessageExcept {

        Message outMessage;

        /*  Playground receives messages from coaches, contestants and the referee  */
        if (inMessage instanceof CoachPlaygroundMessage) {
            outMessage = processAndReplyCoachMessage((CoachPlaygroundMessage) inMessage);
        }
        else if (inMessage instanceof ContestantPlaygroundMessage) {
            outMessage = processAndReplyContestantMessage((ContestantPlaygroundMessage) inMessage);
        }
        else if (inMessage instanceof RefereePlaygroundMessage) {
            outMessage = processAndReplyRefereeMessage((RefereePlaygroundMessage) inMessage);
        }
        else {
            throw new MessageExcept ("Invalid message instance.", inMessage);
        }

        return outMessage;
    }

    /**
     * Process and reply the incoming messages from the coaches
     * @param inMessage Incoming message
     * @return Outgoing message
     * @throws MessageExcept Exception that shows that the incoming message is not valid
     */
    private Message processAndReplyCoachMessage(CoachPlaygroundMessage inMessage) throws MessageExcept {

        CoachPlaygroundMessage outMessage = null;

        /*  validate incoming messages  */
        switch (inMessage.getMsgType()) {
            case CoachPlaygroundMessage.REVIEWNOTES:
                if (inMessage.getN_players() < 0) {
                    throw new MessageExcept ("Invalid number of players!", inMessage);
                }
                if (inMessage.getN_players_pushing() < 0) {
                    throw new MessageExcept ("Invalid number of players pushing!", inMessage);
                }
                if (inMessage.getSelected_contestants() == null) {
                    throw new MessageExcept ("Invalid selected contestants array!", inMessage);
                }
                break;
            case CoachPlaygroundMessage.TERMINATE:
                break;
            default:
                throw new MessageExcept ("Invalid type!", inMessage);
        }

        /*  process and reply to the messages  */
        switch (inMessage.getMsgType()) {
            case CoachPlaygroundMessage.REVIEWNOTES:
                int[] chosen_players = playground.reviewNotes(inMessage.getSelected_contestants(),
                        inMessage.getN_players(),
                        inMessage.getN_players_pushing());
                outMessage = new CoachPlaygroundMessage(CoachPlaygroundMessage.REVIEWNOTES_ANS, chosen_players);
                break;
            case CoachPlaygroundMessage.TERMINATE:
                n_terminates++;
                if (n_terminates == n_terminates_to_end) {
                    System.out.println("Playground terminated!");
                    System.exit(0);
                }
                break;
        }

        return outMessage;
    }


    /**
     * Process and reply the incoming messages from the contestants
     * @param inMessage Incoming message
     * @return Outgoing message
     * @throws MessageExcept Exception that shows that the incoming message is not valid
     */
    private Message processAndReplyContestantMessage(ContestantPlaygroundMessage inMessage) throws MessageExcept {

        ContestantPlaygroundMessage outMessage = null;

        /*  validate incoming messages  */
        switch (inMessage.getMsgType()) {
            case ContestantPlaygroundMessage.PULLROPE:
                if (inMessage.getTeam_id() < 0) {
                    throw new MessageExcept ("Invalid team id! on pull the rope. team id="+inMessage.getTeam_id(), inMessage);
                }
                if (inMessage.getStrength() < 0) {
                    throw new MessageExcept ("Invalid strength!", inMessage);
                }
                if (inMessage.getContestant_id() < 0) {
                    throw new MessageExcept ("Invalid contestant id!", inMessage);
                }
                if (inMessage.getN_players_pushing() < 0) {
                    throw new MessageExcept ("Invalid number of players pushing!", inMessage);
                }
                if (inMessage.getN_players() < 0) {
                    throw new MessageExcept ("Invalid number of players!", inMessage);
                }
                break;
            case ContestantPlaygroundMessage.AMDONE:
                if (inMessage.getN_players_pushing() < 0) {
                    throw new MessageExcept ("Invalid number of players pushing!", inMessage);
                }
                break;
            case ContestantPlaygroundMessage.SEATDOWN:
                if (inMessage.getN_players_pushing() < 0) {
                    throw new MessageExcept ("Invalid number of players pushing!", inMessage);
                }
                break;
            case ContestantPlaygroundMessage.TERMINATE:
                break;
            default:
                throw new MessageExcept ("Invalid type!", inMessage);
        }

        /*  process and reply to the messages  */
        switch (inMessage.getMsgType()) {
            case ContestantPlaygroundMessage.PULLROPE:
                playground.pullTheRope(inMessage.getTeam_id(), inMessage.getStrength(),
                        inMessage.getContestant_id(), inMessage.getN_players_pushing(), inMessage.getN_players());
                outMessage = new ContestantPlaygroundMessage(ContestantPlaygroundMessage.PULLROPE_ANS);
                break;
            case ContestantPlaygroundMessage.AMDONE:
                playground.iAmDone(inMessage.getN_players_pushing());
                outMessage = new ContestantPlaygroundMessage(ContestantPlaygroundMessage.AMDONE_ANS);
                break;
            case ContestantPlaygroundMessage.SEATDOWN:
                playground.seatDown(inMessage.getN_players_pushing());
                outMessage = new ContestantPlaygroundMessage(ContestantPlaygroundMessage.SEATDOWN_ANS);
                break;
            case ContestantPlaygroundMessage.TERMINATE:
                n_terminates++;
                if (n_terminates == n_terminates_to_end) {
                    System.out.println("Playground terminated!");
                    System.exit(0);
                }
                break;
        }

        return outMessage;
    }




    /**
     * Process and reply the incoming messages from the referee
     * @param inMessage Incoming message
     * @return Outgoing message
     * @throws MessageExcept Exception that shows that the incoming message is not valid
     */
    private Message processAndReplyRefereeMessage(RefereePlaygroundMessage inMessage) throws MessageExcept {

        RefereePlaygroundMessage outMessage = null;

        /*  validate incoming messages  */
        switch (inMessage.getMsgType()) {
            case RefereePlaygroundMessage.ATD:
                if (inMessage.getN_players_pushing() < 0) {
                    throw new MessageExcept ("Invalid number of players pushing!", inMessage);
                }
                if (inMessage.getKnockDif() < 0) {
                    throw new MessageExcept ("Invalid knockout difference!", inMessage);
                }
                break;
            case RefereePlaygroundMessage.TERMINATE:
                break;
            default:
                throw new MessageExcept ("Invalid type!", inMessage);
        }

        /*  process and reply to the messages  */
        switch (inMessage.getMsgType()) {
            case RefereePlaygroundMessage.ATD:
                TrialStat trialStat = playground.assertTrialDecision(inMessage.getN_players_pushing(),
                        inMessage.getKnockDif());
                outMessage = new RefereePlaygroundMessage(RefereePlaygroundMessage.ATD_ANS, trialStat);
                break;
            case RefereePlaygroundMessage.TERMINATE:
                n_terminates++;
                if (n_terminates == n_terminates_to_end) {
                    System.out.println("Playground terminated!");
                    System.exit(0);
                }
                break;
        }

        return outMessage;
    }

}
