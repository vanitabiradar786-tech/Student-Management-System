import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        StudentService service = new StudentService();
        StudentFileService fileService =
            new StudentFileService("students.dat");

       // Load previously saved students
        try {
            service.loadFromFile(fileService);
        } catch (FileNotFoundException e) {
            System.out.println(
                "No saved records found. Starting a new system."
            );
        } catch (IOException e) {
            System.out.println(
                "Could not load records: " + e.getMessage()
            );
        }

        int choice = -1;

        while (choice != 0) {

            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Search Student by Name");
            System.out.println("5. Update Marks");
            System.out.println("6. Update Attendance");
            System.out.println("7. Calculate Grade");
            System.out.println("8. Remove Student");
            System.out.println("9. Display Topper");
            System.out.println("10. Save Students to File");
            System.out.println("11. Load Students from File");
            System.out.println("0. Save and Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());

                switch (choice) {

                    case 1: {
                        System.out.print("Enter student ID: ");
                        int id = Integer.parseInt(sc.nextLine().trim());

                        System.out.print("Enter student name: ");
                        String name = sc.nextLine().trim();

                        System.out.print("Enter email: ");
                        String email = sc.nextLine().trim();

                        System.out.print("Enter course ID: ");
                        int courseId =
                            Integer.parseInt(sc.nextLine().trim());

                        System.out.print("Enter course name: ");
                        String courseName = sc.nextLine().trim();

                        System.out.print("Enter course duration in months: ");
                        int duration =
                            Integer.parseInt(sc.nextLine().trim());

                        System.out.print("Enter marks (0-100): ");
                        double marks =
                            Double.parseDouble(sc.nextLine().trim());

                        System.out.print("Enter attendance (0-100): ");
                        int attendance =
                            Integer.parseInt(sc.nextLine().trim());

                        System.out.println(
                            "Status options: ACTIVE, INACTIVE, COMPLETED"
                        );
                        System.out.print("Enter student status: ");

                        StudentStatus status = StudentStatus.valueOf(
                            sc.nextLine().trim().toUpperCase()
                        );

                        Course course = new Course(
                            courseId, courseName, duration
                        );

                        Student student = new Student(
                            id, name, email, marks, attendance,
                            course, status
                        );

                        service.addStudent(student);
                        break;
                    }

                    case 2:
                        service.displayAllStudents();
                        break;

                    case 3: {
                        System.out.print("Enter student ID: ");
                        int id = Integer.parseInt(sc.nextLine().trim());

                        System.out.println(service.searchById(id));
                        break;
                    }

                    case 4: {
                        System.out.print("Enter student name: ");
                        String name = sc.nextLine().trim();

                        service.searchByName(name);
                        break;
                    }

                    case 5: {
                        System.out.print("Enter student ID: ");
                        int id = Integer.parseInt(sc.nextLine().trim());

                        System.out.print("Enter new marks (0-100): ");
                        double marks =
                            Double.parseDouble(sc.nextLine().trim());

                        service.updateMarks(id, marks);
                        break;
                    }

                    case 6: {
                        System.out.print("Enter student ID: ");
                        int id = Integer.parseInt(sc.nextLine().trim());

                        System.out.print("Enter attendance (0-100): ");
                        int attendance =
                            Integer.parseInt(sc.nextLine().trim());

                        service.updateAttendance(id, attendance);
                        break;
                    }

                    case 7: {
                        System.out.print("Enter student ID: ");
                        int id = Integer.parseInt(sc.nextLine().trim());

                        service.displayGrade(id);
                        break;
                    }

                    case 8: {
                        System.out.print("Enter student ID to remove: ");
                        int id = Integer.parseInt(sc.nextLine().trim());

                        service.removeStudent(id);
                        break;
                    }

                    case 9:
                        service.displayTopper();
                        break;

                    case 10:
                        service.saveToFile(fileService);
                        break;

                    case 11:
                        service.loadFromFile(fileService);
                        break;

                    case 0:
                        service.saveToFile(fileService);
                        System.out.println("Exiting application...");
                        break;

                    default:
                        System.out.println(
                            "Invalid choice. Please select 0 to 11."
                        );
                }

            } catch (NumberFormatException e) {
                System.out.println(
                    "Invalid number. Please enter a valid number."
                );

            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());

            } catch (IOException e) {
                System.out.println(
                    "File error: " + e.getMessage()
                );

            } catch (Exception e) {
                System.out.println(
                    "Unexpected error: " + e.getMessage()
                );
            }
        }

        sc.close();
    }
}
