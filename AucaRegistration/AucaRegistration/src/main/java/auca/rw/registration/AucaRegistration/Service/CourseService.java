package auca.rw.registration.AucaRegistration.Service;

import auca.rw.registration.AucaRegistration.Domain.AcademicUnit;
import auca.rw.registration.AucaRegistration.Domain.Course;
import auca.rw.registration.AucaRegistration.Domain.CourseDefinition;
import auca.rw.registration.AucaRegistration.Domain.Semester;
import auca.rw.registration.AucaRegistration.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public String saveCourse(Course course) {
        if (course != null) {
            courseRepository.save(course);
            return "Course Saved";
        } else {
            return null;
        }
    }
    public String saveCourseOfStudents(Course course) {
        if (course != null) {
            courseRepository.save(course);
            return "Course of students Saved";
        } else {
            return null;
        }
    }

    public boolean isCourseExist(AcademicUnit department, Semester semester) {
        return courseRepository.existsByDepartmentAndSemester(department, semester);
    }
    public boolean isCourseIdExist(String id) {
        return courseRepository.existsById(id);
    }

    public boolean isCourseDefinitionExist(CourseDefinition courseDefinition) {
        return courseRepository.existsByCourseDefinition(courseDefinition);
    }

    public Iterable<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(String id) {
        return courseRepository.findById(id).orElse(null);
    }

    public String updateCourse(Course course) {
        if (course != null && courseRepository.existsById(course.getId())) {
            courseRepository.save(course);
            return "Course updated";
        } else {
            return "Course not found";
        }
    }

    public List<Course> getCoursesByDepartmentAndSemester(AcademicUnit department, Semester semester) {
        return courseRepository.findByDepartmentAndSemester(department, semester);
    }

    public String deleteCourse(String id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return "Course deleted ";
        } else {
            return "Course Not found";
        }
    }
}
