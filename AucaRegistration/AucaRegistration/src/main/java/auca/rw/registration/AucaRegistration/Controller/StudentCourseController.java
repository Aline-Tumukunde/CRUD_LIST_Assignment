package auca.rw.registration.AucaRegistration.Controller;

import auca.rw.registration.AucaRegistration.Domain.Course;
import auca.rw.registration.AucaRegistration.Domain.StudentCourse;
import auca.rw.registration.AucaRegistration.Domain.StudentRegisteration;
import auca.rw.registration.AucaRegistration.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/StudCourse" , produces = (MediaType.APPLICATION_JSON_VALUE), consumes = (MediaType.APPLICATION_JSON_VALUE))
public class StudentCourseController {
    private static final long serialVersionUID = 1L;
    @Autowired
    private StudCourseService studCourseService;
    @Autowired
    private SemesterService semesterService;
    @Autowired
    private StudentRegistrationService registrationService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseDefinitionService courseDefinitionService;
    //creating
    @PostMapping(value = "/saveStudCourse")
    public ResponseEntity<?> createStudCrs(@RequestBody StudentCourse studCrs){
        if(studCrs != null ){
            String message = studCourseService.saveStudCourse(studCrs);
            if(message != null){
                return new ResponseEntity<>("Student Course Saved", HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>("Not Saved",HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            return new ResponseEntity<>("Error",HttpStatus.BAD_GATEWAY);
        }
    }

    //list
    @GetMapping(value = "/listStudCourse")
    public ResponseEntity<List<StudentCourse>> listStudCrs() {
        List<StudentCourse> results = studCourseService.listStudentsCourse();
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
    //update
    @PutMapping(value = "/updateStudCourse/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Integer id, @RequestBody StudentCourse studCrs) {
        if (studCrs != null) {
            String results = studCourseService.updateStudCourse(id, studCrs);
            if (results != null) {
                return new ResponseEntity<>("Student Course Updated ", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(" Not Updated ", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }
    //delete
    @DeleteMapping(value = "/deleteStudCourse/{id}")
    public ResponseEntity<String> deleteStudCrs(@PathVariable Integer id) {
        if (id != null) {
            String message = studCourseService.deleteStudCourse(id);
            if (message != null) {
                return new ResponseEntity<>("Student Course Deleted ", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Student Course Not Deleted ", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/listByCourse/{studentId}")
    public ResponseEntity<List<StudentCourse>> listByCourse(@PathVariable String studentId){
        StudentRegisteration stud = registrationService.getStudentRegistrationById(studentId);

        if (stud != null) {
            List<StudentCourse> crs = studCourseService.getCoursesByStudentId(studentId);
            return new ResponseEntity<>(crs, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listByCourseAndSemester/{courseCode}/{semesterId}")
    public ResponseEntity<List<StudentCourse>> listByCourseAndSemester(@PathVariable String courseCode, @PathVariable String semesterId) {
        Course course = courseService.getCourseById(courseCode);

        if (course != null) {
            List<StudentCourse> crs = studCourseService.getStudentByCourseAndSemester(course, semesterId);
            return new ResponseEntity<>(crs, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
