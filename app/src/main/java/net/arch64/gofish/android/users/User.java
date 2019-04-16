/* Package */
package net.arch64.gofish.android.users;

/* Class: User */
public class User {
    private int 	id;
    private String 	username;
    private String	password;
    private String	email;
    private String	fname;
    private String	lname;
    private boolean	em_notify;
    private double	rep;

    /**
     * User
     * @param email - username
     * @param pass - password
     *
     * Constructor for the user class.
     * This one is primarily used for user auth.
     */
    public User(String email, String pass) {
        this.email = email;
        this.password = pass;
    }

    /**
     * User
     * @param user - username
     * @param pass - password
     * @param email - email
     * @param fname - first name (can optionally be blank: "")
     * @param lname - last name (can optionally be blank: "")
     * @param em_notify - email notify flag
     *
     * Constructor for the user class.
     * This one is primarily used for user registration.
     */
    public User(String user, String pass, String email, String fname, String lname, boolean em_notify) {
        this.username = user;
        this.password = pass;
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.em_notify = em_notify;
    }
}
