package auca.rw.registration.AucaRegistration.Service;

import auca.rw.registration.AucaRegistration.Domain.StudentRegisteration;
import auca.rw.registration.AucaRegistration.Domain.AcademicUnit;
import auca.rw.registration.AucaRegistration.Domain.Semester;
import auca.rw.registration.AucaRegistration.Repository.SemesterRepository;
import auca.rw.registration.AucaRegistration.Repository.StudentRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentRegistrationService {
    @Autowired
    private StudentRegistrationRepository studentRegistrationRepository;
    private SemesterRepository semesterRepository;

    public String saveStudentRegistration(StudentRegisteration studentRegistration) {
        if (studentRegistration != null) {
            studentRegistrationRepository.save(studentRegistration);
            return "Student registration Saved";
        } else {
            return null;
        }
    }

    public List<StudentRegisteration> getRegistrationsByDepartmentAndSemester(AcademicUnit department, Semester semester) {
        return studentRegistrationRepository.findByDepartmentAndSemester(department, semester);
    }

    public Semester getSemesterById(String semesterId) {
        Optional<Semester> optionalSemester = semesterRepository.findById(semesterId);
        return optionalSemester.orElse(null);
    }

    public List<StudentRegisteration> getRegistrationsBySemester(Semester semester) {
        return studentRegistrationRepository.findBySemester(semester);
    }

    public boolean isRegistrationExists(AcademicUnit department, Semester semester) {
        return studentRegistrationRepository.existsByDepartmentAndSemester(department, semester);
    }

    public Iterable<StudentRegisteration> getAllStudentRegistrations() {
        return studentRegistrationRepository.findAll();
    }

    public StudentRegisteration getStudentRegistrationById(String id) {
        return studentRegistrationRepository.findById(id).orElse(null);
    }

    public String updateStudentRegistration(StudentRegisteration studentRegistration) {
        if (studentRegistration != null && studentRegistrationRepository.existsById(studentRegistration.getId())) {
            studentRegistrationRepository.save(studentRegistration);
            return "Student registration ";
        } else {
            return "Not found";
        }
    }

    public String deleteStudentRegistration(String id) {
        if (studentRegistrationRepository.existsById(id)) {
            studentRegistrationRepository.deleteById(id);
            return "Student registration deleted ";
        } else {
            return "Student registration not found";
        }
    }
}
