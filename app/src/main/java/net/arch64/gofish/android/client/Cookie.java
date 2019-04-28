package net.arch64.gofish.android.client;

import net.arch64.gofish.android.users.User;

public class Cookie {
    private static User user;

    public static void setUser(User inUser) {
        user = inUser;
    }

    public static int getUserId() {
        return user.getId();
    }
}
