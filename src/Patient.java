import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Patient extends User {

    private boolean paid;
    private String disease;
    private Mode mode;

    private Physician physician;
    private int age;
    private boolean isPicked;

    private String dischargeSummery;

    public String getDischargeSummery() {
        return dischargeSummery;
    }

    public void setDischargeSummery(String dischargeSummery) {
        this.dischargeSummery = dischargeSummery;
    }

    public boolean isDischarged() {
        return discharged;
    }

    public void discharge() {
        this.discharged = true;
    }

    private boolean discharged;

    public LocalDate getArrival() {
        return arrival;
    }

    private LocalDate arrival;

    private Prescription prescription;

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public Patient(String fName, String lName, int age, Sex sex, String disease, Mode mode) {
        super(fName, lName, sex);
        this.age = age;
        this.disease = disease;
        this.mode = mode;
        this.isPicked = false;
        this.physician = null;
        this.discharged = false;
        this.dischargeSummery = null;
        this.arrival = LocalDate.now();
        this.paid = false;
        this.prescription = null;
    }

    @Override
    public String toString() {
        return "Name: " + this.getFullName() + "\t" + "Problem: "
                + this.getDisease() + "\t" + "Date of arrival: " + arrival.toString();
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isPicked() {
        return isPicked;
    }

    public void pick(Physician physician) {
        isPicked = true;
        this.physician = physician;
    }

    public void unpick() {
        isPicked = false;
        this.physician = null;
    }

    public Physician getPhysician() {
        return physician;
    }

    public boolean isPaid() {
        return this.paid;
    }

    public boolean pay() {
        if (!this.isPaid()) {
            this.paid = true;
        }
        return this.isPaid();
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

    public void setArrival(LocalDate date) {
        this.arrival = date;
    }


}
