package auca.rw.registration.AucaRegistration.Service;

import auca.rw.registration.AucaRegistration.Domain.Semester;
import auca.rw.registration.AucaRegistration.Repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SemesterService {
    @Autowired
    private SemesterRepository semesterRepository;

    public String saveSemester(Semester semester) {
        if (semester != null) {
            semesterRepository.save(semester);
            return "Semester created ";
        } else {
            return null;
        }
    }

    public Iterable<Semester> getAllSemesters() {
        return semesterRepository.findAll();
    }

    public Semester getSemesterById(String id) {
        return semesterRepository.findById(id).orElse(null);
    }

    public String updateSemester(Semester semester) {
        if (semester != null && semesterRepository.existsById(semester.getId())) {
            semesterRepository.save(semester);
            return "Semester updated ";
        } else {
            return "Semester not found";
        }
    }

    public String deleteSemester(String id) {
        if (semesterRepository.existsById(id)) {
            semesterRepository.deleteById(id);
            return "Semester deleted ";
        } else {
            return "Semester Not found";
        }
    }

}
