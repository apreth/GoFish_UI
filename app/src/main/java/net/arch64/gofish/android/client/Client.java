/* Package */
package net.arch64.gofish.android.client;

/* Imports */

import net.arch64.gofish.android.users.User;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import com.google.gson.Gson;

/* Class: Client */
public class Client {
    private Socket sock;
    private BufferedReader in;
    private PrintWriter out;
    //private DataInputStream dis;
    private Gson gson;

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
        //in = null;
        try {
            sock = new Socket(addr, port);

            if (sock != null) {
                in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                out = new PrintWriter(sock.getOutputStream());
                //dis = new DataInputStream(sock.getInputStream());
            }
        } catch (IOException e) {}
        this.sock = sock;
        gson = new Gson();
    }

    /**
     * authenticate
     * @param email - the user to be authenticated
     * @param pass - the password of that user
     *
     * Connects back to Seasick Server and tries to
     * authenticate the user.
     */
    public Message authenticate(String email, String pass) {
        Message msg = new Message("auth", new User(email, pass));
        out.write(gson.toJson(msg) + "\n");
        out.flush();
        Message authd = new Message();
        String line = "";
        try {
            line = in.readLine();
            authd = gson.fromJson(line, authd.getClass());
        } catch (IOException e) { e.printStackTrace(); }
        return authd;
    }

    public boolean register(String user, String pass, String fname, String lname, String email, boolean em_notify) {
        Message msg = new Message("registration", new User(user, pass, email, fname, lname, em_notify));
        out.write(gson.toJson(msg) + "\n");
        out.flush();
        String registered = "";
        try {
            registered = in.readLine();
        } catch (IOException e) {}
        if (registered.equals("true")) {
            return true;
        }
        return false;
    }

    /**
     * msgServer
     *
     * Function to test server connection.
     */
    public void msgServer() {
        String json = gson.toJson(new Message("Hello World!", null));
        System.out.println("JSON STRING: " + json);
        out.write(gson.toJson(new Message("Hello World!", null)));
        out.flush();
    }

    /**
     * close
     *
     * Closes the socket connection with the server.
     */
    public void close() {
        try {
            if (in != null) { in.close(); }
            if (out != null) { out.close(); }
            //if (dis != null) { dis.close(); }
            if (sock != null) { sock.close(); }
        } catch (IOException e) {}
    }
}
