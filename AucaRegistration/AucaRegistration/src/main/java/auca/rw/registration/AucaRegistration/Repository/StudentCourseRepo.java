package auca.rw.registration.AucaRegistration.Repository;

import auca.rw.registration.AucaRegistration.Domain.Course;
import auca.rw.registration.AucaRegistration.Domain.StudentCourse;
import auca.rw.registration.AucaRegistration.Domain.StudentRegisteration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCourseRepo extends JpaRepository<StudentCourse, Integer> {
    boolean existsByCourse(Course course);
    List<StudentCourse> findByStudentRegistration(StudentRegisteration studentRegistration);
    List<StudentCourse> findByCourseAndStudentRegistration(Course course, StudentRegisteration studentRegistration);
}
