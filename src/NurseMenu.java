import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class NurseMenu extends Menu {

    Menu menu;
    Scanner scanner;
    Nurse nurse;

    public NurseMenu(Menu mneu, Nurse nurse) {
        this.menu = mneu;
        this.nurse = nurse;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void show() {
        System.out.println("Nurse menu");
        if (nurse.sex == Sex.MALE) {
            System.out.print("Welcome mr. ");
        } else {
            System.out.print("Welcome ms. ");
        }
        System.out.println(nurse.getFullName());
        System.out.println("Check the patient state (state)");
        System.out.println("Exit (exit)");

        switch (scanner.nextLine().toLowerCase().trim()) {
            case "state":
                this.showState();
                break;
            case "exit":
                menu.show();
                break;
            default:
                System.out.println("Please enter a valid command");
                this.show();
        }
    }

    private void showState() {
        System.out.println("Select the option:");
        System.out.println("No doctor assigned (NDA)");
        System.out.println("Checked in (checked)");
        System.out.println("Get prescription (prescription)");
        System.out.println("Discharge (discharge)");
        System.out.println("Show medicine (medicine)");
        System.out.println("Exit (exit)");
        switch (scanner.nextLine().toLowerCase().trim()) {
            case "nda":
                this.showNoDoctorAssignedPatients();
                break;
            case "checked":
                this.checkedin();
                break;
            case "prescription":
                this.getPrescription();
                break;
            case "discharge":
                this.dischargedPatients();
                break;
            case "medicine":
                this.showMedicine();
                break;
            case "exit":
                this.show();
                break;
            default:
                this.showState();
        }
    }

    private void showNoDoctorAssignedPatients() {
        ArrayList<Patient> patients = Database.getPatients();
        ArrayList<Patient> noDoctorAssigned = new ArrayList<>();

        for (Patient patient : patients) {
            if (!patient.isPicked()) {
                noDoctorAssigned.add(patient);
            }
        }

        if (noDoctorAssigned.size() > 0) {
            System.out.println("No doctor assigned to these patients:");
            for (Patient patient : noDoctorAssigned) {
                System.out.println(patient);
            }
        } else {
            System.out.println("No patient found.");
        }

        System.out.println("Exit (exit)");
        String response = scanner.nextLine().toLowerCase().trim();

        if (response.equals("exit")) {
            this.showState();
        } else {
            this.showNoDoctorAssignedPatients();
        }
    }

    private void dischargedPatients() {
        ArrayList<Patient> patients = Database.getPatients();
        ArrayList<Patient> dischargedPatients = new ArrayList<>();

        for (Patient patient : patients) {
            if (patient.isDischarged()) {
                dischargedPatients.add(patient);
            }
        }

        if (dischargedPatients.size() > 0) {
            System.out.println("Discharged patients: ");

            for (Patient patient : dischargedPatients) {
                System.out.println(patient);
            }
        } else {
            System.out.println("No patient found.");
        }

        System.out.println("Exit (exit)");
        String response = scanner.nextLine().toLowerCase().trim();

        if (response.equals("exit")) {
            this.showState();
        } else {
            this.dischargedPatients();
        }
    }

    private void showMedicine() {
        System.out.println("Show (show)");
        System.out.println("Exit (exit)");

        switch (scanner.nextLine().toLowerCase().trim()) {
            case "show":
                break;
            case "exit":
                this.showState();
                break;
            default:
                System.out.println("Enter a valid input");
                this.showMedicine();
        }

        ArrayList<Patient> patients = new ArrayList<>();

        for (Patient patient : Database.pickedPatients()) {
            if (patient.getPrescription() != null && patient.getPrescription().getMedicine() != null) {
                patients.add(patient);
            }
        }

        if (patients.size() > 0) {
            System.out.println("Patients: ");
            for (Patient patient : patients) {
                System.out.println(patient.getId() + " : " + patient);
            }

            System.out.print("Enter patient's id: ");
            int patientId = scanner.nextInt();

            Patient patient = Database.getPatientById(patientId);

            if (patient != null) {
                System.out.println("Medicine: ");
                System.out.println(patient.getPrescription().getMedicine()
                        + (patient.getPrescription().isConfirmed() ? " (Confirmed)" : " (Confirmation needed)"));
            } else {
                System.out.println("Patient was not found.");
                this.showMedicine();
            }
        } else {
            System.out.println("No patient was found.");
            this.showMedicine();
        }

        System.out.println("Exit (exit)");
        scanner.nextLine();
        String response = scanner.nextLine().toLowerCase().trim();

        if (response.equals("exit")) {
            this.showState();
        } else {
            this.showMedicine();
        }
    }

    private void getPrescription() {
        System.out.println("Prescription (p)");
        System.out.println("Exit (exit)");
        switch (scanner.nextLine().toLowerCase().trim()) {
            case "p":
                break;
            case "exit":
                this.showState();
                break;
            default:
                System.out.println("Enter a valid input");
                this.getPrescription();
        }
        ArrayList<Patient> patients = Database.pickedPatients();
        if (patients.size() > 0) {
            System.out.println("Choose a patient: ");
            for (Patient patient : patients) {
                System.out.println(patient.getId() + " : " + patient
                        + (patient.getPrescription() != null && patient.getPrescription().isConfirmed() ? " (Confirmed)" : ""));
            }
            System.out.print("Enter patient's id: ");
            int patientId = scanner.nextInt();
            Patient patient = Database.getPatientById(patientId);
            scanner.nextLine();

            if (patient != null) {
                if (patient.isPicked() && patient.getPrescription() == null) {
                    System.out.println("Enter your message: ");
                    String messageText = scanner.nextLine();
                    Prescription prescription = new Prescription(nurse, patient, patient.getPhysician(), messageText);
                    Database.addPrescription(prescription);
                    patient.setPrescription(prescription);
                    patient.getPhysician().addPrescriptions(prescription);
                } else if (patient.isPicked() && patient.getPrescription() != null) {
                    System.out.print("Is the patient takes the medicine properly? (y/n): ");
                    if (scanner.nextLine().toLowerCase().trim().equals("y")) {
                        patient.getPrescription().confirm();
                        System.out.println("Confirmed successfully.");
                    }
                    this.getPrescription();
                } else {
                    System.out.println("No doctor is assigned to this patient.");
                    this.getPrescription();
                }
            } else {
                System.out.println("Patient is not found.");
                this.getPrescription();
            }
        } else {
            System.out.println("No patient is found.");
            this.getPrescription();
        }

        System.out.println("Exit (exit)");
        String response = scanner.nextLine().toLowerCase().trim();

        if (response.equals("exit")) {
            this.showState();
        } else {
            this.getPrescription();
        }
    }

    private void checkedin() {
        System.out.println("Enter first Date:");
        LocalDate firstDate = getDate();
        System.out.println("Enter second date:");
        LocalDate secondDate = getDate();
        ArrayList<Patient> patients = Database.patientsBetweenDates(firstDate, secondDate);

        if (patients.size() > 0) {
            System.out.println("Patients: ");
            for (Patient patient : patients) {
                System.out.println(patient.getId() + " : "+ patient);
            }
        } else {
            System.out.println("No patient was found.");
        }

        System.out.println("Exit (exit)");
        String response = scanner.nextLine().toLowerCase().trim();

        if (response.equals("exit")) {
            this.showState();
        } else {
            this.dischargedPatients();
        }

    }

    private LocalDate getDate() {
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        System.out.print("Enter month: ");
        int month = scanner.nextInt();
        System.out.print("Enter day: ");
        int day = scanner.nextInt();
        scanner.nextLine();
        return LocalDate.of(year, month, day);
    }
}
