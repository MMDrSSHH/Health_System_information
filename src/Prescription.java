public class Prescription {

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    private String medicine;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    private String message;
    private boolean confirmation;
    private Nurse nurse;
    private Patient patient;
    private Physician physician;

    public Prescription(Nurse nurse, Patient patient, Physician physician, String message) {
        this.nurse = nurse;
        this.patient = patient;
        this.physician = physician;
        this.message = message;
        this.confirmation = false;
        this.medicine = null;
    }

    public void writeMedicine(String medicine) {
        this.medicine = medicine;
    }

    public void confirm() {
        this.confirmation = true;
    }

    public Physician getPhysician() {
        return this.physician;
    }

    public Nurse getNurse() {
        return this.nurse;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public boolean isConfirmed() {
        return this.confirmation;
    }
}
