package auca.rw.registration.AucaRegistration.Domain;

import jakarta.persistence.*;
import java.util.List;
import java.time.LocalDate;

@Entity
@Table(name = "studentregisteration")
public class StudentRegisteration {
    @Id
    @Column(name = "reg_id")
    private String id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stu_id")
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "semester_id")
    private Semester semester;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dep_id")
    private AcademicUnit department;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;

    private LocalDate regDate;

    public StudentRegisteration() {
        super();
        // TODO Auto-generated constructor stub
    }

    public StudentRegisteration(String id, Student student, Semester semester, AcademicUnit department,
                                List<Course> courses, LocalDate regDate) {
        super();
        this.id = id;
        this.student = student;
        this.semester = semester;
        this.department = department;
        this.courses = courses;
        this.regDate = regDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public AcademicUnit getDepartment() {
        return department;
    }

    public void setDepartment(AcademicUnit department) {
        this.department = department;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

}
