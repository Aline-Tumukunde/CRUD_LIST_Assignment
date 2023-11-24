package auca.rw.registration.AucaRegistration.Service;

import auca.rw.registration.AucaRegistration.Domain.Student;
import auca.rw.registration.AucaRegistration.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public String saveStudent(Student student){
        if(student != null){
            studentRepository.save(student);
            return "Student Saved ";
        }else{
            return null;
        }
    }

    public String saveStudentWithCourses(Student student) {
        if (student != null) {
            studentRepository.save(student);
            return "Student with courses";
        } else {
            return null;
        }
    }


    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(String id) {
        return studentRepository.findById(id).orElse(null);
    }

    public String updateStudent(Student student) {
        if (student != null && studentRepository.existsById(student.getId())){
            studentRepository.save(student);
            return "Student updated";
        } else {
            return "Student not found";
        }
    }

    public String deleteStudent(String id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return "Student deleted ";
        } else {
            return "Not found";
        }
    }

}
