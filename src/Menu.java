import java.util.Scanner;

public class Menu {
    private boolean isQuit;

    public Menu() {
        isQuit = false;
    }

    protected void quit() {
        isQuit = true;
    }


    public void show() {
        System.out.println("Welcome to Health Information System.");
        while (!isQuit) {
            LoginMenu loginMenu = new LoginMenu(this);
            loginMenu.show();
        }
    }
}
