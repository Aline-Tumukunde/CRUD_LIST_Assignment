package auca.rw.registration.AucaRegistration.Repository;

import auca.rw.registration.AucaRegistration.Domain.AcademicUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicUnitRepository extends JpaRepository<AcademicUnit, String> {
}
