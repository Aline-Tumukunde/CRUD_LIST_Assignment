package auca.rw.registration.AucaRegistration.Repository;

import auca.rw.registration.AucaRegistration.Domain.AcademicUnit;
import auca.rw.registration.AucaRegistration.Domain.Course;
import auca.rw.registration.AucaRegistration.Domain.CourseDefinition;
import auca.rw.registration.AucaRegistration.Domain.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
        boolean existsByDepartmentAndSemester(AcademicUnit department, Semester semester);
        public boolean existsByCourseDefinition(CourseDefinition courseDefinition);
        List<Course> findByDepartmentAndSemester(AcademicUnit department, Semester semester);
}
