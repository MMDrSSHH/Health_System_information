import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Physician extends User {
    private String field;

    private String record;
    private ArrayList<Patient> patients;

    public ArrayList<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void addPrescriptions(Prescription prescription) {
        prescriptions.add(prescription);
    }

    private ArrayList<Prescription> prescriptions;
    public Physician(String fName, String lName, String field, String record, Sex sex) {
        super(fName, lName, sex);
        this.patients = new ArrayList<>();
        this.field = field;
        this.record = record;
        this.prescriptions = new ArrayList<>();
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
    }

    public void pickPatient(@NotNull Patient patient) {
        patient.pick(this);
        patients.add(patient);
    }

    @Override
    public String toString() {
        return "Name: " + this.getFullName() + "\t" + "Record: " + this.record + "\t" + "Field: " + this.field;
    }

}
