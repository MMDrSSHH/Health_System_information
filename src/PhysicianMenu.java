import java.util.ArrayList;
import java.util.Scanner;

public class PhysicianMenu extends Menu {

    Scanner scanner;
    Menu menu;
    Physician physician;

    public PhysicianMenu(Menu menu, Physician physician) {
        this.menu = menu;
        this.physician = physician;
        scanner = new Scanner(System.in);
    }

    @Override
    public void show() {

        System.out.println("Physician menu");
        System.out.println("Welcome dr." + physician.getFullName());

        System.out.println("Pick patients (pick)");
        System.out.println("List of all patients (patients)");
        System.out.println("View patient info (patient)");
        System.out.println("Write medicine (medicine)");
        System.out.println("Discharge patient (discharge)");
        System.out.println("Exit (exit)");

        switch (scanner.nextLine().toLowerCase()) {
            case "pick":
                this.pickPatient();
                break;
            case "patients":
                this.viewAllPatients();
                break;
            case "patient":
                this.viewPatient();
                break;
            case "medicine":
                this.wrtieMedicine();
                this.show();
                break;
            case "discharge":
                this.discharge();
                break;
            case "exit":
                menu.show();
            default:
                System.out.println("Enter a valid input");
                this.show();
        }
    }

    private void pickPatient() {
        ArrayList<Patient> availablePatients = Database.patientsMatchField(physician.getField());

        if (availablePatients.size() > 0) {
            System.out.println("Available patients:");
            System.out.println();
            for (Patient patient : availablePatients) {
                System.out.println(patient.getId() + " : " + patient);
            }
            System.out.println("Pick one (pick)");
            System.out.println("Physician menu (physician)");
            System.out.println("Exit (exit)");

            switch (scanner.nextLine()) {
                case "pick":
                    System.out.print("Enter Patient's id: (at least 3 digits)");
                    int id = scanner.nextInt();
                    Patient patient = Database.getPatientById(id);
                    if (patient != null) {
                        physician.pickPatient(patient);
                        System.out.println("Patient picked successfully");
                    } else {
                        System.out.println("Patient did not found");
                    }
                    break;
                case "physician":
                    this.show();
                    break;
                case "exit":
                    menu.show();
                    break;
                default:
                    this.pickPatient();
            }
        }

        System.out.println("Physician menu (physician)");
        System.out.println("Exit (exit)");
        String response = scanner.nextLine();
        if (response.toLowerCase().equals("physician")) {
            this.show();
        } else if (response.toLowerCase().equals("exit")) {
            menu.show();
        } else {
            this.pickPatient();
        }
    }

    private void viewAllPatients() {
        ArrayList<Patient> patients = Database.getPatients();
        if (patients != null) {
            System.out.println("All patients: ");
            for (Patient patient : patients) {
                System.out.println(patient);
            }
        } else {
            System.out.println("There is no patient at the moment.");
        }

        System.out.println("Physician menu (physician)");
        System.out.println("Exit (exit)");
        String response = scanner.nextLine();
        if (response.toLowerCase().equals("physician")) {
            this.show();
        } else if (response.toLowerCase().equals("exit")) {
            menu.show();
        } else {
            this.viewAllPatients();
        }
    }

    private void viewPatient() {
        System.out.print("Search patient: (by name or id)");
        String searchString = scanner.nextLine();
        ArrayList<Patient> patients = new ArrayList<>();
        try {
            int id = Integer.parseInt(searchString);
            patients.add(Database.getPatientById(id));
        } catch (NumberFormatException e) {
            String name = searchString;
            patients.addAll(Database.getPatientsByName(name));
        }
        if (patients.size() > 0) {
            System.out.println("Patients found: ");
            for (Patient patient : patients) {
                System.out.println(patient);
            }
        } else {
            System.out.println("No patient found");
        }

        System.out.println("Physician menu (physician)");
        System.out.println("Exit (exit)");
        String response = scanner.nextLine();
        if (response.toLowerCase().equals("physician")) {
            this.show();
        } else if (response.toLowerCase().equals("exit")) {
            menu.show();
        } else {
            this.viewPatient();
        }
    }

    public void discharge() {
        ArrayList<Patient> patients = physician.getPatients();
        if (patients.size() > 0) {
            System.out.println("Patients: ");
            for (Patient patient : patients) {
                System.out.print(patient.getId() + " : " + patient);
                if (patient.isDischarged()) {
                    System.out.println(" (DISCHARGED)");
                }
                System.out.println();
            }
            System.out.print("Enter patient's id to discharge: ");
            int patientId = scanner.nextInt();
            scanner.nextLine();
            Patient patient = Database.getPatientById(patientId);

            if (patient != null) {
                patient.discharge();
                System.out.println("Patient is successfully discharged.");
            } else {
                System.out.println("Patient was not found.");
            }

        } else {
            System.out.println("No patient was found");
            this.show();
        }
        System.out.println("Physician menu (physician)");
        System.out.println("Exit (exit)");
        String response = scanner.nextLine();
        if (response.toLowerCase().equals("physician")) {
            this.show();
        } else if (response.toLowerCase().equals("exit")) {
            menu.show();
        } else {
            this.discharge();
        }
    }

    public void wrtieMedicine() {
        ArrayList<Prescription> prescriptions = this.physician.getPrescriptions();

        System.out.println("Write medicine (medicine)");
        System.out.println("Exit (exit)");

        switch (scanner.nextLine().toLowerCase().trim()) {
            case "medicine":
                break;
            case "exit":
                this.show();
                break;
            default:
                System.out.println("Enter a valid input");
                this.wrtieMedicine();
        }

        if (prescriptions.size() > 0) {
            System.out.println("Prescriptions: ");
            for (Prescription prescription : prescriptions) {
                Patient patient = prescription.getPatient();
                System.out.print(patient.getId() + " : " + patient);
                if (prescription.isConfirmed()) {
                    System.out.println(" (confirmed)");
                } else {
                    System.out.println();
                }
            }

            System.out.print("Enter patient id to write medicine for: ");
            int patientId = scanner.nextInt();
            scanner.nextLine();
            Patient patient = Database.getPatientById(patientId);
            if (patient != null) {
                Prescription prescription = patient.getPrescription();
                System.out.println(prescription.getMessage());
                System.out.println("Write down medicine prescription: ");
                String medicine = scanner.nextLine();
                prescription.writeMedicine(medicine);
                System.out.println("Medicine wrote successfully");
                this.wrtieMedicine();
            } else {
                System.out.println("Patient was not found");
                this.wrtieMedicine();
            }

            System.out.println("Physician menu (physician)");
            System.out.println("Exit (exit)");
            String response = scanner.nextLine();
            if (response.toLowerCase().equals("physician")) {
                this.show();
            } else if (response.toLowerCase().equals("exit")) {
                menu.show();
            } else {
                this.wrtieMedicine();
            }
        } else {
            System.out.println("There is no prescription found for ypu.");
            this.show();
        }

        System.out.println("Physician menu (physician)");
        System.out.println("Exit (exit)");
        String response = scanner.nextLine();
        if (response.toLowerCase().equals("physician")) {
            this.show();
        } else if (response.toLowerCase().equals("exit")) {
            menu.show();
        } else {
            this.wrtieMedicine();
        }
    }

}
