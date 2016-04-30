package pt.ua.sd.RopeGame.comInfo;

/**
 * Defines the thrown exception when the message is not valid
 */
public class MessageExcept extends Exception {
    /**
     * invalid message
     */
    private Message msg;


    /**
     * Message init
     * @param errorMsg text identifying the error
     * @param msg message that originates the exception
     */
    public MessageExcept(String errorMsg, Message msg)
    {
        super(errorMsg);
        this.msg=msg;
    }

    /**
     * get intvalid message
     * @return invalid message
     */
    public Message getMsgVal()
    {
        return msg;
    }
}
