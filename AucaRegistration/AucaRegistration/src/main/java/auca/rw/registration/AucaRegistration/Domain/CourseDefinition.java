package auca.rw.registration.AucaRegistration.Domain;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Course_Definition")
public class CourseDefinition implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long definitionId;

    @OneToOne
    @JoinColumn(name = "id")
    private Course course;

    private String name;
    private String description;

    public CourseDefinition() {
    }

    public CourseDefinition(Long definitionId, Course course, String name, String description) {
        this.definitionId = definitionId;
        this.course = course;
        this.name = name;
        this.description = description;
    }

    public Long getDefinitionId() {
        return definitionId;
    }

    public void setDefinitionId(Long definitionId) {
        this.definitionId = definitionId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
