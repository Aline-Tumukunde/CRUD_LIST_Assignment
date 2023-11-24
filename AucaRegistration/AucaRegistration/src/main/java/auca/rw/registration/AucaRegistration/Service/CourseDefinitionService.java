package auca.rw.registration.AucaRegistration.Service;

import auca.rw.registration.AucaRegistration.Domain.CourseDefinition;
import auca.rw.registration.AucaRegistration.Repository.CourseDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseDefinitionService {
    @Autowired
    private CourseDefinitionRepository courseDefinitionRepository;

    public String saveCourseDefinition(CourseDefinition courseDefinition) {
        if (courseDefinition != null) {
            courseDefinitionRepository.save(courseDefinition);
            return "Course definition ";
        } else {
            return null;
        }
    }

    public Iterable<CourseDefinition> getAllCourseDefinitions() {
        return courseDefinitionRepository.findAll();
    }

    public CourseDefinition getCourseDefinitionById(Long id) {
        return courseDefinitionRepository.findById(id).orElse(null);
    }

    public String updateCourseDefinition(CourseDefinition courseDefinition) {
        if (courseDefinition != null && courseDefinitionRepository.existsById(courseDefinition.getDefinitionId())) {
            courseDefinitionRepository.save(courseDefinition);
            return "Course definition updated";
        } else {
            return "Not found";
        }
    }

    public String deleteCourseDefinition(Long id) {
        if (courseDefinitionRepository.existsById(id)) {
            courseDefinitionRepository.deleteById(id);
            return "Course definition deleted";
        } else {
            return "Not found";
        }
    }

}
