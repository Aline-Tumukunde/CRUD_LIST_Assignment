package auca.rw.registration.AucaRegistration.Service;

import auca.rw.registration.AucaRegistration.Domain.Teacher;
import auca.rw.registration.AucaRegistration.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public String saveTeacher(Teacher teacher) {
        if (teacher != null) {
            teacherRepository.save(teacher);
            return "Teacher Saved";
        } else {
            return null;
        }
    }

    public Iterable<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(String id) {
        return teacherRepository.findById(id).orElse(null);
    }

    public String updateTeacher(Teacher teacher) {
        if (teacher != null && teacherRepository.existsById(teacher.getId())) {
            teacherRepository.save(teacher);
            return "Teacher updated";
        } else {
            return "Not found";
        }
    }

    public String deleteTeacher(String id) {
        if (teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
            return "Teacher deleted ";
        } else {
            return "Not found";
        }
    }
}
