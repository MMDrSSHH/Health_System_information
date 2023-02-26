import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class AdminMenu extends Menu {

    private Menu menu;
    private Scanner scanner;

    public AdminMenu(Menu menu) {
        this.menu = menu;
        scanner = new Scanner(System.in);
    }

    @Override
    public void show() {
        System.out.println("List all users (users)");
        System.out.println("Search for user (user)");
        System.out.println("Add physician (physician)");
        System.out.println("Add nurse (nurse)");
        System.out.println("Add patient (patient)");
        System.out.println("Delete user (delete)");
        System.out.println("Change password (password)");
        System.out.println("Exit (exit)");
        String response = scanner.nextLine();
        switch (response.toLowerCase()) {
            case "users":
                this.listAllUsers();
                break;
            case "user":
                this.searchUser();
                break;
            case "physician":
                this.addPhysician();
                break;
            case "nurse":
                this.addNurse();
                break;
            case "patient":
                this.addPatient();
                break;
            case "delete":
                this.deleteUser();
                break;
            case "password":
                this.changePassword();
                break;
            case "exit":
                menu.show();
                break;
            default:
                this.show();
        }
    }

    private void listAllUsers() {
        ArrayList<User> users = Database.getUsers();
        if (users.size() == 0) {
            System.out.println("There is no user in the system");
        } else {
            for (User user : users) {
                System.out.println(user.getId() + " : " + user);
            }
        }

        System.out.println("Admin menu (admin)");
        System.out.println("Exit (exit)");
        String response = scanner.nextLine();

        if (response.toLowerCase().equals("admin")) {
            this.show();
        } else if (response.toLowerCase().equals("exit")) {
            menu.show();
        } else {
            this.listAllUsers();
        }
    }

    private void searchUser() {
        System.out.print("Enter physician's lastname: ");
        String lName = scanner.nextLine();
        ArrayList<User> users = Database.getUsers(lName);

        if (users.size() == 0) {
            System.out.println("No user found containing *" + lName + "* portion");
        } else {
            for (User user : users) {
                System.out.println(user.getId() + " : " + user);
            }
        }

        System.out.println("Admin menu (admin)");
        System.out.println("Exit (exit)");
        String response = scanner.nextLine();
        if (response.toLowerCase().equals("admin")) {
            this.show();
        } else if (response.toLowerCase().equals("exit")) {
            menu.show();
        } else {
            this.searchUser();
        }
    }

    private void addPhysician() {
        System.out.println("Adding new physician");
        System.out.print("Enter firstname: ");
        String fName = scanner.nextLine();
        System.out.print("Enter lastname: ");
        String lName = scanner.nextLine();
        System.out.println("Enter Field: ");
        for (String field : Field.getFieldsKey()) {
            System.out.println(field + "(" + field.toLowerCase() + ")");
        }
        String field = scanner.nextLine();
        System.out.println("Enter physician's record: ");
        String record = scanner.nextLine();
        System.out.println("Enter sex: (f/m)");
        String sexStr = scanner.nextLine();
        Sex sex;
        if (sexStr.toLowerCase().equals("f")) {
            sex = Sex.FEMALE;
        } else {
            sex = Sex.MALE;
        }

        Database.addPhysician(new Physician(fName, lName, field, record, sex));

        System.out.println("Admin menu (admin)");
        System.out.println("Exit (exit)");
        String response = scanner.nextLine();
        if (response.toLowerCase().equals("admin")) {
            this.show();
        } else if (response.toLowerCase().equals("exit")) {
            menu.show();
        } else {
            this.addPhysician();
        }
    }

    private void addNurse() {
        System.out.println("Adding new nurse");
        System.out.print("Enter firstname: ");
        String fName = scanner.nextLine();
        System.out.print("Enter lastname: ");
        String lName = scanner.nextLine();

        System.out.println("Enter nurse's record: ");
        String record = scanner.nextLine();
        System.out.println("Enter sex: (f/m)");
        String sexStr = scanner.nextLine();
        Sex sex;
        if (sexStr.toLowerCase().equals("f")) {
            sex = Sex.FEMALE;
        } else {
            sex = Sex.MALE;
        }

        Database.addNurse(new Nurse(fName, lName, record, sex));

        System.out.println("Admin menu (admin)");
        System.out.println("Exit (exit)");
        String response = scanner.nextLine();
        if (response.toLowerCase().equals("admin")) {
            this.show();
        } else if (response.toLowerCase().equals("exit")) {
            menu.show();
        } else {
            this.addNurse();
        }
    }

    private void addPatient() {
        System.out.println("Adding new physician");
        System.out.print("Enter firstname: ");
        String fName = scanner.nextLine();
        System.out.print("Enter lastname: ");
        String lName = scanner.nextLine();
        System.out.print("Enter patient's age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter sex: (f/m)");
        String sexStr = scanner.nextLine();
        Sex sex;
        if (sexStr.toLowerCase().equals("f")) {
            sex = Sex.FEMALE;
        } else {
            sex = Sex.MALE;
        }
        System.out.println("Enter patient's disease: ");
        System.out.print("( ");
        for (String field : Field.getFieldsKey()) {
            for (String disease : Objects.requireNonNull(Field.getFieldsValues(field))) {
                System.out.print(disease + " ");
            }
        }
        System.out.println(")");
        String disease = scanner.nextLine();

        System.out.println("Enter patient's mode: (v/n/i)");
        Mode mode = Mode.NORMAL;
        boolean loop = true;
        while (loop) {
            switch (scanner.nextLine().toLowerCase()) {
                case "v":
                    mode = Mode.VIP;
                    loop = false;
                    break;
                case "i":
                    mode = Mode.INSURANCE;
                    loop = false;
                    break;
                case  "n":
                    mode = Mode.NORMAL;
                    loop = false;
                    break;
                default:
                    System.out.println("Type the valid input (v/n/i)");
            }
        }


        Database.addPatient(new Patient(fName, lName, age, sex, disease, mode));

        System.out.println("Admin menu (admin)");
        System.out.println("Exit (exit)");
        String response = scanner.nextLine();
        if (response.toLowerCase().equals("admin")) {
            this.show();
        } else if (response.toLowerCase().equals("exit")) {
            menu.show();
        } else {
            this.addPatient();
        }
    }

    private void deleteUser() {
        System.out.print("Enter id of the user you want to delete: ");
        int id = scanner.nextInt();
        switch (Database.deleteUserById(id)) {
            case 0:
                System.out.println("User " + id + " has been deleted successfully");
                break;
            case -1:
                System.out.println("User " + id + " not found");
                break;
        }

        System.out.println("Admin menu (admin)");
        System.out.println("Exit (exit)");
        scanner.nextLine();
        String response = scanner.nextLine();
        if (response.toLowerCase().equals("admin")) {
            this.show();
        } else if (response.toLowerCase().equals("exit")) {
            menu.show();
        } else {
            this.deleteUser();
        }
    }

    private void changePassword() {
        System.out.println("Which one do you want to change?");
        System.out.println("Admin (admin)\nPhysician (physician)" +
                "\nNurse (nurse)");
        int res;
        User user;
        int id;
        switch (scanner.nextLine().toLowerCase()) {
            case "admin":
                System.out.print("Enter new password: ");
                res = Admin.changePassword(scanner.nextLine());
                if (res == 0) {
                    System.out.println("Admin's password changed successfully");
                } else {
                    System.out.println("Your password must include " +
                            "at least one of these characters !@#$%&*");
                    this.changePassword();
                }
                break;
            case "physician":
                System.out.print("Enter physician's id: ");
                id = scanner.nextInt();
                scanner.nextLine();
                user = Database.getUserById(id);
                if (user != null) {
                    System.out.print("Enter new password: ");
                    res = user.changePassword(scanner.nextLine());
                    if (res == 0) {
                        System.out.println("Physician's password changed successfully");
                    } else {
                        System.out.println("Your password must include " +
                                "at least one of these characters !@#$%&*");
                        this.changePassword();
                    }
                } else {
                    System.out.println("Physician not found.");
                    this.changePassword();
                }
                break;
            case "nurse":
                System.out.print("Enter nurse's id: ");
                id = scanner.nextInt();
                scanner.nextLine();
                user = Database.getUserById(id);
                if (user != null) {
                    System.out.print("Enter new password: ");
                    res = user.changePassword(scanner.nextLine());
                    if (res == 0) {
                        System.out.println("Nurse's password changed successfully");
                    } else {
                        System.out.println("Your password must include " +
                                "at least one of these characters !@#$%&*");
                        this.changePassword();
                    }
                } else {
                    System.out.println("Nurse not found.");
                    this.changePassword();
                }
                break;
            default:
                System.out.println("Try again");
                this.changePassword();
        }
    }
}
