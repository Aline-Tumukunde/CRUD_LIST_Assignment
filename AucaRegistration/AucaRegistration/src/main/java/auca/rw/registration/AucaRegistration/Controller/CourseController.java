package auca.rw.registration.AucaRegistration.Controller;

import auca.rw.registration.AucaRegistration.Domain.AcademicUnit;
import auca.rw.registration.AucaRegistration.Domain.Course;
import auca.rw.registration.AucaRegistration.Domain.Semester;
import auca.rw.registration.AucaRegistration.Service.AcademicUnitService;
import auca.rw.registration.AucaRegistration.Service.CourseService;
import auca.rw.registration.AucaRegistration.Service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping(value = "/course", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private AcademicUnitService academicUnitService;
    @Autowired
    private SemesterService semesterService;

    @PostMapping(value = "/saveCourse")
    public ResponseEntity<?> saveCourse(@RequestBody Course course) {
        if (course != null) {
            String courses  = courseService.saveCourse(course);
            if (courses  != null) {
                return new ResponseEntity<>(courses , HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not Saved", HttpStatus.NOT_ACCEPTABLE);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/getAllCourses")
    public ResponseEntity<Iterable<Course>> getAllCourses() {
        Iterable<Course> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping(value = "/getCourseById/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable String id) {
        Course course = courseService.getCourseById(id);
        if (course != null) {
            return new ResponseEntity<>(course, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/updateCourse")
    public ResponseEntity<String> updateCourse(@RequestBody Course course) {
        String message = courseService.updateCourse(course);
        if (message.equals("Course updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deleteCourse/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable String id) {
        String courses  = courseService.deleteCourse(id);
        if (courses .equals("Course deleted successfully")) {
            return new ResponseEntity<>(courses , HttpStatus.OK);
        } else {
            return new ResponseEntity<>(courses , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/listByDepartmentAndSemester/{departmentCode}/{semesterId}")
    public ResponseEntity<List<Course>> listStudentsByDepartmentAndSemester(
            @PathVariable String departmentCode,
            @PathVariable String semesterId) {

        AcademicUnit department = academicUnitService.getAcademicUnitById(departmentCode);
        Semester semester = semesterService.getSemesterById(semesterId);

        if (department != null && semester != null) {
            List<Course> courses = courseService.getCoursesByDepartmentAndSemester(department, semester);
            return new ResponseEntity<>(courses , HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
