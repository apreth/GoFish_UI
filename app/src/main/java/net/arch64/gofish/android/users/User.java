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
     *
     * Default constructor for User class.
     */
    public User() {

    }

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

    public int getId() { return id; }					/** getId: @return id */
    public String getUsername() { return username; }	/** getUsername: @return username */
    public String getPassword() { return password; }	/** getPassword: @return password */
    public String getEmail() { return email; }			/** getEmail: @return email */
    public String getFname() { return fname; }			/** getFname: @return fname */
    public String getLname() { return lname; }			/** getLname: @return lname */
    public boolean getEmNotify() { return em_notify; }	/** getEmNotify: @return em_notify */
    public double getRep() { return rep; }				/** getRep: @return rep */

    public void setId(int id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setEmail(String email) { this.email = email; }
    public void setFname(String fname) { this.fname = fname; }
    public void setLname(String lname) { this.lname = lname; }
    public void setEmNotify(boolean em_notify) { this.em_notify = em_notify; }
    public void setRep(double rep) { this.rep = rep; }
}
