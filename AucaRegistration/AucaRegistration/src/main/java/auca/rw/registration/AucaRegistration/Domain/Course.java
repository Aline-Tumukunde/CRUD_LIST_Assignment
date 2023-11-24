package auca.rw.registration.AucaRegistration.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {
    @Id
    private String id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "semesterId")
    private Semester semester;

    @OneToMany(mappedBy = "course")
    @JsonBackReference
    private List<StudentCourse> studentCourses;

    @ManyToOne
    @JoinColumn(name = "course_definition_id")
    private CourseDefinition courseDefinition;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private AcademicUnit department;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students;

    public Course() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Course(String id, String name, Semester semester, List<StudentCourse> studentCourses, CourseDefinition courseDefinition, AcademicUnit department, List<Student> students) {
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.studentCourses = studentCourses;
        this.courseDefinition = courseDefinition;
        this.department = department;
        this.students = students;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public List<StudentCourse> getStudentCourses() {
        return studentCourses;
    }

    public void setStudentCourses(List<StudentCourse> studentCourses) {
        this.studentCourses = studentCourses;
    }

    public CourseDefinition getCourseDefinition() {
        return courseDefinition;
    }

    public void setCourseDefinition(CourseDefinition courseDefinition) {
        this.courseDefinition = courseDefinition;
    }

    public AcademicUnit getDepartment() {
        return department;
    }

    public void setDepartment(AcademicUnit department) {
        this.department = department;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
