package auca.rw.registration.AucaRegistration.Domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "academic_unit")
public class AcademicUnit {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private EAcademicUnit type;


    @ManyToMany
    @JoinTable(
            name = "teacher_academicunit",
            joinColumns = @JoinColumn(name = "academicunit_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private List<Teacher> teachers;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "unit")
    private AcademicUnit unit;



    public AcademicUnit() {
        super();
        // TODO Auto-generated constructor stub
    }

    public AcademicUnit(String id, String name, EAcademicUnit type, List<Teacher> teachers, AcademicUnit unit) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.teachers = teachers;
        this.unit = unit;
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

    public EAcademicUnit getType() {
        return type;
    }

    public void setType(EAcademicUnit type) {
        this.type = type;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public AcademicUnit getUnit() {
        return unit;
    }

    public void setUnit(AcademicUnit unit) {
        this.unit = unit;
    }


}
