package auca.rw.registration.AucaRegistration.Repository;

import auca.rw.registration.AucaRegistration.Domain.AcademicUnit;
import auca.rw.registration.AucaRegistration.Domain.Semester;
import auca.rw.registration.AucaRegistration.Domain.StudentRegisteration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRegistrationRepository extends JpaRepository<StudentRegisteration,String> {
    boolean existsByDepartmentAndSemester(AcademicUnit department, Semester semester);
    boolean existsByStudentId(String studentId);
    StudentRegisteration findByStudentId(String studentId);

    List<StudentRegisteration> findBySemester(Semester semester);
    List<StudentRegisteration> findByDepartmentAndSemester(AcademicUnit department, Semester semester);
    List<StudentRegisteration> findBySemesterId(String semesterId);
}
