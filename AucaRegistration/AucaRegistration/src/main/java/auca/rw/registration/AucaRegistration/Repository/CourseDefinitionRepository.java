package auca.rw.registration.AucaRegistration.Repository;

import auca.rw.registration.AucaRegistration.Domain.CourseDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDefinitionRepository extends JpaRepository<CourseDefinition, Long> {
}
