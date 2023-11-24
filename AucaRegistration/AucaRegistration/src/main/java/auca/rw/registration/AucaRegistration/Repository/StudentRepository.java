package auca.rw.registration.AucaRegistration.Repository;

import auca.rw.registration.AucaRegistration.Domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<Student,String>{
}
