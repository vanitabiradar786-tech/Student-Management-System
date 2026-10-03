public class Course {
    private int courseId;
    private String courseName;
    private int durationMonths;

    public Course(int courseId, String courseName, int durationMonths) {
        if (courseId <= 0) {
            throw new IllegalArgumentException(
                "Course ID must be positive."
            );
        }

        if (courseName == null || courseName.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Course name cannot be empty."
            );
        }

        if (durationMonths <= 0) {
            throw new IllegalArgumentException(
                "Duration must be greater than zero."
            );
        }

        this.courseId = courseId;
        this.courseName = courseName;
        this.durationMonths = durationMonths;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getDurationMonths() {
        return durationMonths;
    }

    @Override
    public String toString() {
        return courseName + " (" + durationMonths + " months)";
    }
}
