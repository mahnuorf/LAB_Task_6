import java.util.HashMap;
import java.util.Map;

public class StudentMarks {
    public static void main(String[] args) {
        HashMap<String, Integer> marks = new HashMap<>();

        // 1. Insert five student records.
        marks.put("Mahnoor", 85);
        marks.put("Hadia", 80);
        marks.put("Laiba", 78);
        marks.put("Nimra", 88);
        marks.put("Khadija", 82);

        // 2. Display all student records.
        System.out.println("All student records:");

        for (Map.Entry<String, Integer> student : marks.entrySet()) {
            System.out.println(
                student.getKey() + " = " + student.getValue()
            );
        }

        // 3. Update the marks of one student.
        marks.put("Mahnoor", 95);

        System.out.println(
            "Updated marks of Mahnoor: " + marks.get("Mahnoor")
        );

        // 4. Retrieve the marks of a specific student.
        System.out.println("Marks of Hadia: " + marks.get("Hadia"));

        // 5. Display the total number of students.
        System.out.println("Total students: " + marks.size());
    }
}