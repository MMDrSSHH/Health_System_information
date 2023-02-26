import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Iterator;

public class Database {
    static private ArrayList<User> users = new ArrayList<>();
    static private ArrayList<Physician> physicians = new ArrayList<>();
    static private ArrayList<Nurse> nurses = new ArrayList<>();
    static private ArrayList<Patient> patients = new ArrayList<>();

    static private ArrayList<Prescription> prescriptions = new ArrayList<>();
    static int currentId = 100;

    static public Physician getPhysicianById(int id) {
        for (Physician physician : physicians) {
            if (physician.getId() == id) {
                return physician;
            }
        }
        return null;
    }

    static public Nurse getNurseById(int id) {
        for (Nurse nurse : nurses) {
            if (nurse.getId() == id) {
                return nurse;
            }
        }
        return null;
    }

    static public Patient getPatientById(int id) {
        for (Patient patient : patients) {
            if (patient.getId() == id) {
                return patient;
            }
        }
        return null;
    }

    static public void addUser(User user) {
        users.add(user);
    }

    static public void addPhysician(Physician physician) {
        physician.setId(currentId++);
        physicians.add(physician);
        addUser(physician);
    }

    static public void addNurse(Nurse nurse) {
        nurse.setId(currentId++);
        nurses.add(nurse);
        addUser(nurse);
    }

    static public void addPatient(Patient patient) {
        patient.setId(currentId++);
        patients.add(patient);
        addUser(patient);
    }

    static public ArrayList<User> getUsers() {
        return users;
    }

    static public ArrayList<User> getUsers(String str) {
        ArrayList<User> usersFound = new ArrayList<>();
        for (User user : users) {
            if (user.getlName().toLowerCase().contains(str)) {
                usersFound.add(user);
            }
        }

        return usersFound;
    }

    /*
     * Checks if there is a match for given username and password
     * if any returns the user
     * otherwise returns null
     */
    static public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }

        return null;
    }

    static public int deleteUserById(int id) {
        Iterator<User> iu = users.iterator();

        while (iu.hasNext()) {
            User user = iu.next();
            if (user.getId() == id) {
                iu.remove();
                return 0;
            }
        }

        Iterator<Physician> ip = physicians.iterator();
        while (ip.hasNext()) {
            Physician physician = ip.next();
            if (physician.getId() == id) {
                physicians.remove(physician);
                return 0;
            }
        }


        Iterator<Nurse> in = nurses.iterator();
        while (in.hasNext()) {
            Nurse nurse = in.next();
            if (nurse.getId() == id) {
                physicians.remove(nurse);
                return 0;
            }
        }

        Iterator<Patient> ipa = patients.iterator();
        while (ipa.hasNext()) {
            Patient patient = ipa.next();
            if (patient.getId() == id) {
                physicians.remove(patient);
                return 0;
            }
        }

        return -1;
    }

    static public String userType(int id) {
        for (Physician physician : physicians) {
            if (physician.getId() == id) {
                return "physician";
            }
        }

        for (Nurse nurse : nurses) {
            if (nurse.getId() == id) {
                return "nurse";
            }
        }

        for (Patient patient : patients) {
            if (patient.getId() == id) {
                return "patient";
            }
        }

        return null;
    }

    static public ArrayList<Patient> unpickedPatients() {
        ArrayList<Patient> unpickedPatients = new ArrayList<>();
        for (Patient patient : patients) {
            if (!patient.isPicked()) {
                unpickedPatients.add(patient);
            }
        }

        return unpickedPatients;
    }


    static public ArrayList<Patient> patientsByDisease(String disease) {
        ArrayList<Patient> unpickedPatients = unpickedPatients();
        ArrayList<Patient> matched = new ArrayList<>();

        for (Patient patient : unpickedPatients) {
            if (patient.getDisease().toLowerCase().equals(disease.toLowerCase())) {
                matched.add(patient);
            }
        }

        return matched;
    }

    static public ArrayList<Patient> patientsMatchField(String field) {
        ArrayList<String> diseases = Field.getFieldsValues(field);
        ArrayList<Patient> matchField = new ArrayList<>();
        for (String disease : diseases) {
            matchField.addAll(patientsByDisease(disease));
        }

        return matchField;
    }

    static public ArrayList<Patient> getPatients() {
        return patients;
    }

    static public ArrayList<Patient> getPatientsByName(String name) {
        ArrayList<Patient> resultPatients = new ArrayList<>();

        for (Patient patient : patients) {
            if (patient.getfName().equals(name) || patient.getlName().equals(name)) {
                resultPatients.add(patient);
            }
        }

        return resultPatients;
    }

    static public User getUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    static public ArrayList<Physician> getPhysicians() {
        return physicians;
    }

    static public ArrayList<Patient> patientsBetweenDates(LocalDate d1, LocalDate d2) {
        int p1 = Math.abs(Period.between(d1, d2).getDays());
        ArrayList<Patient> patientsBetweenDates = new ArrayList<>();
        for (Patient patient : patients) {
            LocalDate pArrival = patient.getArrival();
            int p2 = Math.abs(Period.between(pArrival, d1).getDays());
            int p3 = Math.abs(Period.between(pArrival, d2).getDays());
            if (p2 <= p1 && p3 <= p1) {
                patientsBetweenDates.add(patient);
            }
        }

        return patientsBetweenDates;
    }

    static public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
    }

    static public ArrayList<Patient> pickedPatients() {
        ArrayList<Patient> pickedPatients = new ArrayList<>();
        for (Patient patient : patients) {
            if (patient.isPicked()) {
                pickedPatients.add(patient);
            }
        }

        return pickedPatients;
    }
}
