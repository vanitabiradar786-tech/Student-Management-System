import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StudentFileService {

    private final String fileName;

    public StudentFileService(String fileName) {
        this.fileName = fileName;
    }

    
    public void saveStudents(Map<Integer, Student> students)   // Save all students to a file
            throws IOException {

        try (DataOutputStream out = new DataOutputStream(
                new FileOutputStream(fileName))) {

            out.writeInt(students.size());

            for (Student s : students.values()) {
                out.writeInt(s.getId());
                out.writeUTF(s.getName());
                out.writeUTF(s.getEmail());
                out.writeDouble(s.getMarks());
                out.writeInt(s.getAttendance());

                out.writeInt(s.getCourse().getCourseId());
                out.writeUTF(s.getCourse().getCourseName());
                out.writeInt(s.getCourse().getDurationMonths());

                out.writeUTF(s.getStatus().name());
            }
        }
    }

    
    public Map<Integer, Student> loadStudents() // Load students from a file
            throws IOException {

        Map<Integer, Student> students = new HashMap<>();

        try (DataInputStream in = new DataInputStream(
                new FileInputStream(fileName))) {

            int count = in.readInt();

            if (count < 0 || count > 100000) {
                throw new IOException("Invalid student file.");
            }

            for (int i = 0; i < count; i++) {
                int id = in.readInt();
                String name = in.readUTF();
                String email = in.readUTF();
                double marks = in.readDouble();
                int attendance = in.readInt();

                int courseId = in.readInt();
                String courseName = in.readUTF();
                int duration = in.readInt();

                StudentStatus status =
                    StudentStatus.valueOf(in.readUTF());

                Course course = new Course(
                    courseId, courseName, duration
                );

                Student student = new Student(
                    id, name, email, marks, attendance,
                    course, status
                );

                if (students.putIfAbsent(id, student) != null) {
                    throw new IOException(
                        "Duplicate student ID in file: " + id
                    );
                }
            }

            if (in.read() != -1) {
                throw new IOException(
                    "Unexpected extra data in student file."
                );
            }

        } catch (java.io.EOFException e) {
            throw new IOException(
                "Student file is incomplete or corrupted.", e
            );
        } catch (IllegalArgumentException e) {
            throw new IOException(
                "Student file contains invalid data.", e
            );
        }

        return students;
    }
}
