import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        /* Test data */

        Physician p1 = new Physician("doc1", "doc1", "heart", "Record1", Sex.MALE);
        Physician p2 = new Physician("doc2", "doc2", "orthopedic", "Record2", Sex.MALE);
        Physician p3 = new Physician("doc3", "doc3", "heart", "Record3", Sex.FEMALE);
        Physician p4 = new Physician("doc4", "doc4", "heart", "Record4", Sex.MALE);
        Database.addPhysician(p1);
        Database.addPhysician(p2);
        Database.addPhysician(p3);
        Database.addPhysician(p4);

        Nurse n1 = new Nurse("nurse1", "nurse1", "Record1", Sex.FEMALE);
        Nurse n2 = new Nurse("nurse2", "nurse2", "Record2", Sex.MALE);
        Database.addNurse(n1);
        Database.addNurse(n2);

        Patient pa1 = new Patient("patient1", "patient1", 22, Sex.MALE, "heart_attack", Mode.VIP);
        pa1.setArrival(LocalDate.of(2023, 1, 1));
        Patient pa2 = new Patient("patient2", "patient2", 45, Sex.MALE, "knee_ache", Mode.INSURANCE);
        pa2.setArrival(LocalDate.of(2023, 1, 15));
        Patient pa3 = new Patient("patient3", "patient3", 34, Sex.FEMALE, "heart_attack", Mode.NORMAL);
        pa3.setArrival(LocalDate.of(2023, 1, 10));
        Patient pa4 = new Patient("patient4", "patient4", 52, Sex.MALE, "blood_pressure", Mode.INSURANCE);
        pa4.setArrival(LocalDate.of(2022, 12, 30));
        Patient pa5 = new Patient("patient5", "patient5", 18, Sex.FEMALE, "elbow_ache", Mode.INSURANCE);
        pa5.setArrival(LocalDate.of(2023, 1, 16));
        Database.addPatient(pa1);
        Database.addPatient(pa2);
        Database.addPatient(pa3);
        Database.addPatient(pa4);
        Database.addPatient(pa5);

        /* Test data */
        Menu menu = new Menu();
        menu.show();
    }
}
