package pt.ua.sd.RopeGame.comInfo;

import java.io.Serializable;

/**
 * This data type defines the messages that are switched between clients and servers.
 * The communication is based in the switch of Message objects in a TCP channel.
 *
 */
public abstract class Message implements Serializable{

    /**
     * Serialization key
     * @serialField serialVersionUID
     */
    private static final long serialVersionUID = 200416L;

    /* Messages types */

    /**
     * ACK message
     */
    public static final int ACK = 0;

    /* Messages Fields */

    /**
     * Message type
     */
    private int msgType = -1;

    /**
     * Messages Instantiation.
     *
     * @param type message type
     */
    public Message(int type) {
        this.msgType = type;
    }

    /**
     * Get message type
     * @return message type
     */
    public int getMsgType() {
        return msgType;
    }
}
