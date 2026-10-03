import java.util.HashMap;
import java.util.Map;

public class StudentService {

    private Map<Integer, Student> students = new HashMap<>();

    
    public void addStudent(Student student) {   // Add student
        if (students.containsKey(student.getId())) {
            throw new IllegalArgumentException(
                "Student ID already exists."
            );
        }

        students.put(student.getId(), student);
        System.out.println("Student added successfully.");
    }

    
    public void displayAllStudents() {    // Display all students
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        for (Student student : students.values()) {
            System.out.println(student);
        }
    }

    
    public Student searchById(int id) {   // Search by ID
        Student student = students.get(id);

        if (student == null) {
            throw new IllegalArgumentException(
                "Student not found with ID: " + id
            );
        }

        return student;
    }

    
    public void searchByName(String name) { // Search by name
        boolean found = false;

        for (Student student : students.values()) {
            if (student.getName().equalsIgnoreCase(name.trim())) {
                System.out.println(student);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No student found with that name.");
        }
    }

    
    public void updateMarks(int id, double marks) {  // Update marks
        Student student = searchById(id);
        student.setMarks(marks);

        System.out.println("Marks updated successfully.");
    }

    
    public void updateAttendance(int id, int attendance) {  // Update attendance
        Student student = searchById(id);
        student.setAttendance(attendance);

        System.out.println("Attendance updated successfully.");
    }

    
    public void displayGrade(int id) { // Calculate grade
        Student student = searchById(id);

        System.out.println("Student: " + student.getName());
        System.out.println("Marks: " + student.getMarks());
        System.out.println("Grade: " + student.calculateGrade());
    }

    
    public void removeStudent(int id) {  // Remove student
        Student removed = students.remove(id);

        if (removed == null) {
            throw new IllegalArgumentException(
                "Student not found with ID: " + id
            );
        }

        System.out.println("Student removed successfully.");
    }

    
    public void displayTopper() {  // Display topper
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        Student topper = null;

        for (Student student : students.values()) {
            if (topper == null
                    || student.getMarks() > topper.getMarks()) {
                topper = student;
            }
        }

        System.out.println("===== TOPPER =====");
        System.out.println(topper);
    }

    
    public void saveToFile(StudentFileService fileService) // Save current records
            throws java.io.IOException {

        fileService.saveStudents(students);
        System.out.println("Student records saved successfully.");
    }

    
    public void loadFromFile(StudentFileService fileService)  // Load records from file
            throws java.io.IOException {

        Map<Integer, Student> loaded = fileService.loadStudents();
        students = new HashMap<>(loaded);

        System.out.println("Student records loaded successfully.");
    }
}
