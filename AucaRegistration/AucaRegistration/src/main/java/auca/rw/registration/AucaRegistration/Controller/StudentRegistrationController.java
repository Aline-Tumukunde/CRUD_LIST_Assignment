package auca.rw.registration.AucaRegistration.Controller;

import auca.rw.registration.AucaRegistration.Domain.AcademicUnit;
import auca.rw.registration.AucaRegistration.Domain.Semester;
import auca.rw.registration.AucaRegistration.Domain.StudentRegisteration;
import auca.rw.registration.AucaRegistration.Repository.StudentRepository;
import auca.rw.registration.AucaRegistration.Service.AcademicUnitService;
import auca.rw.registration.AucaRegistration.Service.SemesterService;
import auca.rw.registration.AucaRegistration.Service.StudentRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/student-registration", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
public class StudentRegistrationController {
    @Autowired
    private StudentRegistrationService studentRegistrationService;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AcademicUnitService academicUnitService;

    @Autowired
    private SemesterService semesterService;

    @PostMapping(value = "/saveRegistration")
    public ResponseEntity<?> createReg(@RequestBody StudentRegisteration studentReg){
        if(studentReg != null ){
            String result = studentRegistrationService.saveStudentRegistration(studentReg);
            if(result != null){
                return new ResponseEntity<>("Student Saved", HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>(" Not Saved",HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            return new ResponseEntity<>("Error",HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/listByDepartmentAndSemester/{departmentCode}/{semesterId}")
    public ResponseEntity<List<StudentRegisteration>> listRegistrationsByDepartmentAndSemester(
            @PathVariable String departmentCode,
            @PathVariable String semesterId) {

        AcademicUnit department = academicUnitService.getAcademicUnitById(departmentCode);
        Semester semester = semesterService.getSemesterById(semesterId);

        if (department != null && semester != null) {
            List<StudentRegisteration> registrations = studentRegistrationService.getRegistrationsByDepartmentAndSemester(department, semester);
            return new ResponseEntity<>(registrations, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/listBySemester/{semesterId}")
    public ResponseEntity<List<StudentRegisteration>> listRegistrationsBySemester(

            @PathVariable String semesterId) {
        Semester semester = semesterService.getSemesterById(semesterId);

        if (semester != null) {
            List<StudentRegisteration> registrations = studentRegistrationService.getRegistrationsBySemester(semester);
            return new ResponseEntity<>(registrations, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/getAllStudentRegistrations")
    public ResponseEntity<Iterable<StudentRegisteration>> getAllStudentRegistrations() {
        Iterable<StudentRegisteration> studentRegistrations = studentRegistrationService.getAllStudentRegistrations();
        return new ResponseEntity<>(studentRegistrations, HttpStatus.OK);
    }

    @GetMapping(value = "/getStudentRegistrationById/{id}")
    public ResponseEntity<StudentRegisteration> getStudentRegistrationById(@PathVariable String id) {
        StudentRegisteration studentRegistrations = studentRegistrationService.getStudentRegistrationById(id);
        if (studentRegistrations != null) {
            return new ResponseEntity<>(studentRegistrations, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/updateStudentRegistration")
    public ResponseEntity<String> updateStudentRegistration(@RequestBody StudentRegisteration studentRegistration) {
        String message = studentRegistrationService.updateStudentRegistration(studentRegistration);
        if (message.equals("Student registration updated ")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deleteStudentRegistration/{id}")
    public ResponseEntity<String> deleteStudentRegistration(@PathVariable String id) {
        String result = studentRegistrationService.deleteStudentRegistration(id);
        if (result.equals("Student registration deleted ")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }
}
