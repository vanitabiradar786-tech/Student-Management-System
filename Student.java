public class Student {
    private int id;
    private String name;
    private String email;
    private double marks;
    private int attendance;
    private Course course;
    private StudentStatus status;

    public Student(int id, String name, String email,
                   double marks, int attendance,
                   Course course, StudentStatus status) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "Student ID must be positive."
            );
        }

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Name cannot be empty."
            );
        }

        if (email == null || email.trim().isEmpty()
                || !email.contains("@")) {
            throw new IllegalArgumentException(
                "Please enter a valid email."
            );
        }

        validateMarks(marks);
        validateAttendance(attendance);

        if (course == null || status == null) {
            throw new IllegalArgumentException(
                "Course and status are required."
            );
        }

        this.id = id;
        this.name = name;
        this.email = email;
        this.marks = marks;
        this.attendance = attendance;
        this.course = course;
        this.status = status;
    }

    private void validateMarks(double marks) {
        if (Double.isNaN(marks) || Double.isInfinite(marks)
                || marks < 0 || marks > 100) {
            throw new IllegalArgumentException(
                "Marks must be between 0 and 100."
            );
        }
    }

    private void validateAttendance(int attendance) {
        if (attendance < 0 || attendance > 100) {
            throw new IllegalArgumentException(
                "Attendance must be between 0 and 100."
            );
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getMarks() {
        return marks;
    }

    public int getAttendance() {
        return attendance;
    }

    public Course getCourse() {
        return course;
    }

    public StudentStatus getStatus() {
        return status;
    }

    public void setMarks(double marks) {
        validateMarks(marks);
        this.marks = marks;
    }

    public void setAttendance(int attendance) {
        validateAttendance(attendance);
        this.attendance = attendance;
    }

    public void setStatus(StudentStatus status) {
        if (status == null) {
            throw new IllegalArgumentException(
                "Status cannot be null."
            );
        }
        this.status = status;
    }

    public String calculateGrade() {
        if (marks >= 90) {
            return "A+";
        } else if (marks >= 80) {
            return "A";
        } else if (marks >= 70) {
            return "B";
        } else if (marks >= 60) {
            return "C";
        } else if (marks >= 40) {
            return "D";
        } else {
            return "F";
        }
    }

    @Override
    public String toString() {
        return "ID: " + id
            + " | Name: " + name
            + " | Email: " + email
            + " | Course: " + course.getCourseName()
            + " | Marks: " + marks
            + " | Grade: " + calculateGrade()
            + " | Attendance: " + attendance + "%"
            + " | Status: " + status;
    }
}
