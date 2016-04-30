package pt.ua.sd.RopeGame.shared_mem.RefereeSiteSide;

import pt.ua.sd.RopeGame.comInfo.*;
import pt.ua.sd.RopeGame.structures.GameStat;

/**
 * Interface to the Referee Site
 */
 class RefereeSiteSideInterface {

    /**
     * Referee Site
     * @serialField referee site
     */
    private final MRefereeSite ref_site;

    /**
     * Number of TERMINATE messages received
     * @serialField nTerminateMessages
     */
    private int nTerminateMessages;

    /**
     * Number of TERMINATE messages left
     * @serialField nTerminateMessagesToEnd
     */
    private final int nTerminateMessagesToEnd;

    /**
     * Referee site interface constructor method
     * @param ref_site Referee Site
     * @param nTerminateMessagesToEnd Number of TERMINATE messages left to end
     */
     RefereeSiteSideInterface(MRefereeSite ref_site, int nTerminateMessagesToEnd) {
        this.ref_site = ref_site;
        this.nTerminateMessages = 0;
        this.nTerminateMessagesToEnd = nTerminateMessagesToEnd;
    }

    /**
     * Process and reply the incoming messages
     * @param inMessage Incoming message
     * @return Outgoing message
     * @throws MessageExcept Exception that shows that the incoming message is not valid
     */
    public Message processAndReply (Message inMessage) throws MessageExcept {

        Message outMessage;

        /*  only the Referee is able to call methods from the referee site  */
        if (inMessage instanceof RefereeRefSiteMessage) {
            outMessage = processAndReplyRefereeMessage((RefereeRefSiteMessage) inMessage);
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
    private Message processAndReplyRefereeMessage(RefereeRefSiteMessage inMessage) throws MessageExcept {

        RefereeRefSiteMessage outMessage = null;

        /*  validate incoming messages  */
        switch (inMessage.getMsgType()) {
            case RefereeRefSiteMessage.ANG:
                break;
            case RefereeRefSiteMessage.DGW:
                if (inMessage.get_knock_out() == Integer.MAX_VALUE) {
                    throw new MessageExcept ("Invalid knockout difference!", inMessage);
                }
                if (inMessage.get_n_games() < 0) {
                    throw new MessageExcept ("Invalid game number!", inMessage);
                }
                if (inMessage.get_score_t1() < 0) {
                    throw new MessageExcept ("Invalid team 1 score!", inMessage);
                }
                if (inMessage.get_score_t2() < 0) {
                    throw new MessageExcept ("Invalid team 2 score!", inMessage);
                }
                break;
            case RefereeRefSiteMessage.GNGP:
                break;
            case RefereeRefSiteMessage.TERMINATE:
                break;
            default:
                throw new MessageExcept ("Invalid type!", inMessage);
        }

        /*  process and reply to the messages  */
        switch (inMessage.getMsgType()) {
            case RefereeRefSiteMessage.ANG:
                ref_site.announceNewGame();
                outMessage = new RefereeRefSiteMessage(RefereeRefSiteMessage.ANG_ANS);
                break;
            case RefereeRefSiteMessage.DGW:
                GameStat stat = ref_site.declareGameWinner(inMessage.get_score_t1(), inMessage.get_score_t2(),
                        inMessage.get_knock_out(), inMessage.get_n_games());
                outMessage = new RefereeRefSiteMessage(RefereeRefSiteMessage.DGW_ANS, stat);
                break;
            case RefereeRefSiteMessage.GNGP:
                /*  talk to tiago about this  */
                int games_played = ref_site.getN_games_played();
                outMessage = new RefereeRefSiteMessage(RefereeRefSiteMessage.GNGP_ANS, games_played);
                break;
            case RefereeRefSiteMessage.TERMINATE:
                nTerminateMessages++;
                if (nTerminateMessages == nTerminateMessagesToEnd) {
                    System.out.println("Referee site terminated!");
                    System.exit(0);
                }
                break;
        }

        return outMessage;
    }

}
