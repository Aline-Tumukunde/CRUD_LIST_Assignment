package auca.rw.registration.AucaRegistration.Controller;

import auca.rw.registration.AucaRegistration.Domain.Semester;
import auca.rw.registration.AucaRegistration.Service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/semester", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
public class SemesterController {
    @Autowired
    private SemesterService semesterService;

    @PostMapping(value = "/saveSemester")
    public ResponseEntity<?> saveSemester(@RequestBody Semester semester) {
        if (semester != null) {
            String message = semesterService.saveSemester(semester);
            if (message != null) {
                return new ResponseEntity<>(message, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not Saved", HttpStatus.NOT_ACCEPTABLE);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/getAllSemesters")
    public ResponseEntity<Iterable<Semester>> getAllSemesters() {
        Iterable<Semester> semester = semesterService.getAllSemesters();
        return new ResponseEntity<>(semester, HttpStatus.OK);
    }

    @GetMapping(value = "/getSemesterById/{id}")
    public ResponseEntity<Semester> getSemesterById(@PathVariable String id) {
        Semester semesters = semesterService.getSemesterById(id);
        if (semesters != null) {
            return new ResponseEntity<>(semesters, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/updateSemester")
    public ResponseEntity<String> updateSemester(@RequestBody Semester semester) {
        String results = semesterService.updateSemester(semester);
        if (results.equals("Semester updated ")) {
            return new ResponseEntity<>(results, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(results, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deleteSemester/{id}")
    public ResponseEntity<String> deleteSemester(@PathVariable String id) {
        String results = semesterService.deleteSemester(id);
        if (results.equals("Semester deleted ")) {
            return new ResponseEntity<>(results, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(results, HttpStatus.NOT_FOUND);
        }
    }
}
