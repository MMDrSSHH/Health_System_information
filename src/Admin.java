public class Admin {
    static private String password = "admin";
    static public int changePassword(String newPassword) {
        char[] symbols = {'!', '@', '#', '$', '%', '&', '*'};
        for (char symbol : symbols) {
            if (newPassword.contains("" + symbol)) {
                password = newPassword;
                return 0;
            }
        }
        return -1;
    }

    static public String getPassword() {
        return password;
    }
}
