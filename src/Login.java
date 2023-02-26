
public class Login {
    private static int attempts = 0;
    private static String lastUsernameEntered = "";

    /*
     * login method checks if the entered username and password exists
     * if username == "admin" and also password == "admin"
     * return 0
     * which will grant access to admin menu
     * if there is another username and password it will grant access to
     * it's corresponding menu according to returned userId
     * if none of above happens
     * it will return -1
     * which is access denied
     * */
    static public int login(String username, String password) {
        lastUsernameEntered = username;
        if (username.equals("admin") && password.equals(Admin.getPassword())) {
            return 0;
        }

        User user = Database.getUserByUsername(username);
        if (user != null) {
            if (password.equals(user.getPassword()) && !user.suspendStatus()) {
                attempts = 0;
                return user.getId();
            } else {
                if (lastUsernameEntered.equals(username) && !user.suspendStatus()) {
                    if (++attempts == 3) {
                        attempts = 0;
                        user.suspend();
                    }
                } /*else if (!lastUsernameEntered.equals(username)) {
                    attempts = 0;
                }*/
            }
        }
        return -1;
    }
}
