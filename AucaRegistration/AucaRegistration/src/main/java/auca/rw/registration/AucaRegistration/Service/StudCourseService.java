package auca.rw.registration.AucaRegistration.Service;

import auca.rw.registration.AucaRegistration.Domain.StudentRegisteration;
import auca.rw.registration.AucaRegistration.Domain.Course;
import auca.rw.registration.AucaRegistration.Domain.StudentCourse;
import auca.rw.registration.AucaRegistration.Repository.StudentCourseRepo;
import auca.rw.registration.AucaRegistration.Repository.StudentRegistrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudCourseService {
    private static final Logger logger = LoggerFactory.getLogger(StudCourseService.class);

    @Autowired
    private StudentCourseRepo studCrsRepo;
    @Autowired
    private StudentRegistrationRepository studentRegistrationRepository;
    public String saveStudCourse(StudentCourse studentCourse) {
        if (studentCourse != null) {
            if (isCourseRegistered(studentCourse.getCourse())) {
                return "Course already exists";
            } else {
                studCrsRepo.save(studentCourse);
                return "Course saved";
            }
        } else {
            return null;
        }
    }
    public boolean isCourseRegistered(Course course) {
        return studCrsRepo.existsByCourse(course);
    }
    public boolean isStudentExists(String studentId) {
        return studentRegistrationRepository.existsByStudentId(studentId);
    }
    public boolean isStudentCrsExists(Integer id) {
        return studCrsRepo.existsById(id);
    }

    public List<StudentCourse> listStudentsCourse() {
        return studCrsRepo.findAll();
    }

    public String updateStudCourse(Integer id, StudentCourse studentCourse) {
        logger.info("Updating student with stdId: {}", id);
        try {
            if (studentCourse != null) {
                if (isStudentCrsExists(id)) {
                    studCrsRepo.save(studentCourse);
                    logger.info("Student Course updated ");
                    return "Student Course updated ";
                } else {
                    return "Student Not found";
                }
            } else {
                return "Incorrect Inputs";
            }
        }catch (Exception ex){
            logger.error("Not Updated", ex);
            return "Student Course Not updated";
        }
    }

    public String deleteStudCourse(Integer id) {
        logger.info("Delet student course with stdId: {}", id);
        try {
            if (id != null) {
                if (isStudentCrsExists(id)) {
                    studCrsRepo.deleteById(id);
                    logger.info("Student Course deleted ");
                    return "Student Course deleted";
                } else {
                    return " Not found";
                }
            } else {
                return "Incorrect Inputs";
            }
        } catch (Exception e) {
            logger.error("Not delleted", e);
            return "Student Course not deleted";
        }
    }
    public List<StudentCourse> getCourByStudent(String studentId) {
        StudentRegisteration student = studentRegistrationRepository.findByStudentId(studentId);
        return studCrsRepo.findByStudentRegistration(student);
    }
    @Autowired
    private StudentRegistrationRepository studentRegistrationRepo;

    public List<StudentCourse> getCoursesByStudentId(String studentId) {
        StudentRegisteration student = studentRegistrationRepository.findByStudentId(studentId);
        return studCrsRepo.findByStudentRegistration(student);
    }
    public List<StudentCourse> getStudentByCourseAndSemester(Course course, String semester) {
        List<StudentRegisteration> students = studentRegistrationRepository.findBySemesterId(semester);
        List<StudentCourse> msg = new ArrayList<>();
        for (StudentRegisteration student : students) {
            List<StudentCourse> coursesForStudent = studCrsRepo.findByCourseAndStudentRegistration(course, student);
            msg .addAll(coursesForStudent);
        }
        return msg ;
    }


}
