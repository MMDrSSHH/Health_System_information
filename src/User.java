import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class User {
    protected int id;
    protected String fName;
    protected String lName;
    protected String username;
    protected String password;
    protected boolean isSuspended;

    protected Sex sex;

   /* public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessage(Message message) {
        this.messages.add(message);
    }

    protected ArrayList<Message> messages;
*/

    public User() {
    }

    public User(String fName, String lName, Sex sex) {
        this.fName = fName;
        this.lName = lName;
        this.sex = sex;
        this.username = fName + lName;
        this.password = fName + lName;
//        this.messages = new ArrayList<>();
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getFullName() {
        return getfName() + " " + getlName();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean suspendStatus() {
        return isSuspended;
    }

    public void suspend() {
        isSuspended = true;
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                isSuspended = false;
            }
        };

        timer.schedule(task, 2 * 60 * 1000);
    }

    public int changePassword(String newPassword) {
        char[] symbols = {'!', '@', '#', '$', '%', '&', '*'};
        for (char symbol : symbols) {
            if (newPassword.contains("" + symbol)) {
                this.password = newPassword;
                return 0;
            }
        }
        return -1;
    }

    /*public int sendMessage(String text, int receiverId) {
        Message message = new Message(text, this.id);
        if (message.sendMessage(receiverId) != -1) {
            setMessage(message);
            return 0;
        }
        return -1;
    }*/

    @Override
    public String toString() {
        return this.getFullName();
    }
}
