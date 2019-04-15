/* Package */
package net.arch64.gofish.android.client;

/* Imports */
import net.arch64.gofish.android.users.User;

import java.io.Serializable;

/* Class: Message */
public class Message implements Serializable {
    private String msg;
    private User user;

    /**
     * Message
     * @param msg
     * //@param user
     *
     * Constructor for the Message class.
     * Used to send requests to seasick server.
     */
    public Message(String msg, User user) {
        this.msg = msg;
        this.user = user;
    }

    public String getMsg() { return msg; } /** getMsg: @return msg */
    public User getUser() { return user; } /** getUser: @return user */
}
