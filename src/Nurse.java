public class Nurse extends User {

    String record;

    public Nurse(String fName, String lName, String record, Sex sex) {
        super(fName, lName, sex);
        this.record = record;
    }

    @Override
    public String toString() {
        return "Name: " + this.getFullName() + "\t" + "Record: " + this.record;
    }
}
