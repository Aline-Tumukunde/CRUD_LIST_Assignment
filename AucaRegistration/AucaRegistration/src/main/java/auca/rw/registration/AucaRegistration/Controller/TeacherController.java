package auca.rw.registration.AucaRegistration.Controller;

import auca.rw.registration.AucaRegistration.Domain.Teacher;
import auca.rw.registration.AucaRegistration.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/teacher", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @PostMapping(value = "/saveTeachers")
    public ResponseEntity<?> createTeacher(@RequestBody Teacher teacher) {
        if (teacher != null) {
            String result = teacherService.saveTeacher(teacher);
            if (result != null) {
                return new ResponseEntity<>("Teacher saved ", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not Saved", HttpStatus.NOT_ACCEPTABLE);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/getAllTeachers")
    public ResponseEntity<Iterable<Teacher>> getAllTeachers() {
        Iterable<Teacher> teachers = teacherService.getAllTeachers();
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @GetMapping(value = "/getTeacherById/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable String id) {
        Teacher teacher = teacherService.getTeacherById(id);
        if (teacher != null) {
            return new ResponseEntity<>(teacher, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/updateTeacher")
    public ResponseEntity<String> updateTeacher(@RequestBody Teacher teacher) {
        String result = teacherService.updateTeacher(teacher);
        if (result.equals("Teacher updated ")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deleteTeacher/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable String id) {
        String result = teacherService.deleteTeacher(id);
        if (result.equals("Teacher deleted ")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }
}
