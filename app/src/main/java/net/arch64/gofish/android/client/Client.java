/* Package */
package net.arch64.gofish.android.client;

/* Imports */
import net.arch64.gofish.android.users.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/* Class: Client */
public class Client {
    private Socket sock;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    /**
     * Client
     * @param addr - address of server to connect to
     * @param port - port of server to connect to
     *
     * Constructor for the client class.
     */
    public Client(String addr, int port) {
        Socket sock = null;
        out = null;
        in = null;
        try {
            sock = new Socket(addr, port);

            if (sock != null) {
                out = new ObjectOutputStream(sock.getOutputStream());
                //in = new ObjectInputStream(sock.getInputStream());
            }
        } catch (IOException e) {}
        this.sock = sock;
    }

    /**
     * authenticate
     * @param user - the user to be authenticated
     * @param pass - the password of that user
     *
     * Connects back to Seasick Server and tries to
     * authenticate the user.
     */
    public boolean authenticate(String user, String pass) {
        Message msg = new Message("auth", new User(user, pass));
        try {
            out.writeObject(msg);
        } catch (IOException e) { e.printStackTrace(); }
        return false;
    }

    /**
     * msgServer
     *
     * Function to test server connection.
     */
    public void msgServer() {
        try {
            out.writeObject(new Message("Hello World!", null));
        } catch (IOException e) { e.printStackTrace(); }
    }

    /**
     * close
     *
     * Closes the socket connection with the server.
     */
    public void close() {
        try {
            if (out != null) { out.close(); }
            if (in != null) { in.close(); }
            if (sock != null) { sock.close(); }
        } catch (IOException e) {}
    }
}
