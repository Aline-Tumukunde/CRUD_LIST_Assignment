package auca.rw.registration.AucaRegistration.Controller;

import auca.rw.registration.AucaRegistration.Domain.Student;
import auca.rw.registration.AucaRegistration.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/student", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
public class studentController {
    @Autowired
    public StudentService studentService;
    @PostMapping(value = "/saveStudent")
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        if(student != null){
            String results = studentService.saveStudent(student);
            if(results != null){
                return new ResponseEntity<>("Student saved ", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Student NOT saved ", HttpStatus.NOT_ACCEPTABLE);
            }
        }
        return null;
    }
    @GetMapping(value = "/getAllStudents")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping(value = "/getStudentById/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable String id) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/updateStudent")
    public ResponseEntity<String> updateStudent(@RequestBody Student student) {
        String results= studentService.updateStudent(student);
        if (results.equals("Student updated")) {
            return new ResponseEntity<>(results, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(results, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deleteStudent/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable String id) {
        String results = studentService.deleteStudent(id);
        if (results.equals("Student deleted ")) {
            return new ResponseEntity<>(results, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(results, HttpStatus.NOT_FOUND);
        }
    }
}
