package auca.rw.registration.AucaRegistration.Repository;

import auca.rw.registration.AucaRegistration.Domain.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, String> {
}
