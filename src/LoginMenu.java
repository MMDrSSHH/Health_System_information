import java.util.Scanner;

public class LoginMenu extends Menu {

    private Menu menu;

    public LoginMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void show() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Login (login)");
        System.out.println("Exit (exit)");
        switch (scanner.nextLine().toLowerCase()) {
            case "exit":
                menu.quit();
                break;
            case "login":
                System.out.println("Please login");
                System.out.print("Username: ");
                String username = scanner.nextLine();

                System.out.print("Password: ");
                String password = scanner.nextLine();

                int loginResult = Login.login(username, password);
                if (loginResult == 0) {
                    AdminMenu adminMenu = new AdminMenu(menu);
                    adminMenu.show();
                } else if (loginResult != -1) {
                    switch (Database.userType(loginResult)) {
                        case "physician":
                            PhysicianMenu physicianMenu = new PhysicianMenu(menu,
                                    Database.getPhysicianById(loginResult));
                            physicianMenu.show();
                            break;
                        case "nurse":
                            NurseMenu nurseMenu = new NurseMenu(menu,
                                    Database.getNurseById(loginResult));
                            nurseMenu.show();
                            break;
                        case "patient":
                            PatientMenu patientMenu = new PatientMenu(menu,
                                    Database.getPatientById(loginResult));
                            patientMenu.show();
                            break;
                    }
                } else {
                    System.out.println("Access denied");
                }
                break;
            default:
                this.show();
        }

    }
}
