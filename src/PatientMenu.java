import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class PatientMenu extends Menu {
    Menu menu;

    Patient patient;

    Scanner scanner;

    public PatientMenu(Menu mneu, Patient patient) {
        this.menu = mneu;
        this.patient = patient;
        scanner = new Scanner(System.in);
    }
    @Override
    public void show() {
        System.out.println("Patient menu");
        if (patient.sex == Sex.MALE) {
            System.out.print("Welcome mr. ");
        } else {
            System.out.print("Welcome ms. ");
        }
        System.out.println(patient.getFullName());
        if (patient.isDischarged() && !patient.isPaid()) {
            System.out.println("Checkout (checkout)");
        } else if (!patient.isDischarged()) {
            System.out.println("You haven't been discharged yet");
        } else if (patient.isPaid()){
            System.out.println("You paid your bills.");
        }
        System.out.println("Change password (password)");
        System.out.println("Exit (exit)");

        switch (scanner.nextLine().toLowerCase().trim()) {
            case "checkout":
                if (patient.isDischarged() && !patient.isPaid()) {
                    this.checkout();
                } else {
                    this.show();
                }
                break;
            case "password":
                this.changePassword();
                break;
            case "exit":
                menu.show();
                break;
            default:
                System.out.println("Enter a valid input.");
                this.show();
        }
    }

    private void checkout() {
        LocalDate now = LocalDate.now();
        int days = Math.abs(Period.between(now, patient.getArrival()).getDays());
        int perDayCost;
        switch (patient.getMode()) {
            case NORMAL:
                perDayCost = 70;
                break;
            case INSURANCE:
                perDayCost = 35;
                break;
            case VIP:
                perDayCost = 120;
                break;
            default:
                perDayCost = 0;
        }

        int totalCost = days * perDayCost;

        System.out.println("You have hospitalized for " + days + " days.");
        System.out.println("Pay the bill of " + totalCost + "$ (pay)");
        System.out.println("Exit (exit)");

        switch (scanner.nextLine().toLowerCase().trim()) {
            case "pay":
                patient.pay();
                this.show();
                break;
            case "exit":
                this.show();
            default:
                System.out.println("Enter a valid input");
                this.checkout();
        }

    }

    private void changePassword() {
        System.out.print("Enter new password: ");
        int res = patient.changePassword(scanner.nextLine());
        if (res == 0) {
            System.out.println("Patient's password changed successfully");
        } else {
            System.out.println("Your password must include " +
                    "at least one of these characters !@#$%&*");
            this.changePassword();
        }
    }
}
