import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Field {
    static ArrayList<String> fields = getFields();
    static ArrayList<String> fieldsKey = getFieldsKey();

    private static ArrayList<String> getFields() {
        ArrayList<String> fields = new ArrayList<>();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("G:/Java/Health_Information_System/src/config.txt"));
            String line = reader.readLine();

            while (line != null) {
                fields.add(line);
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return fields;
    }

    static ArrayList<String> getFieldsKey() {
        ArrayList<String> fieldsKey = new ArrayList<>();
        for (String field : fields) {
            String fieldKey = field.split(":")[0].trim().toUpperCase();
            fieldsKey.add(fieldKey);
        }

        return fieldsKey;
    }

    static ArrayList<String> getFieldsValues(String fieldKey) {
        ArrayList<String> fieldsValues = new ArrayList<>();

        for (String field : fields) {
            if (field.split(":")[0].trim().toLowerCase().equals(fieldKey.toLowerCase())) {
                for (String value : field.split(":")[1].split(",")) {
                    fieldsValues.add(value.trim().toUpperCase());
                }
                return fieldsValues;
            }
        }
        return null;
    }
}
