package auca.rw.registration.AucaRegistration.Controller;

import auca.rw.registration.AucaRegistration.Domain.CourseDefinition;
import auca.rw.registration.AucaRegistration.Service.CourseDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/coursedefinition", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
public class CourseDefinitionController {
    @Autowired
    private CourseDefinitionService courseDefinitionService;

    @PostMapping(value = "/saveCourseDefinition")
    public ResponseEntity<?> saveCourseDefinition(@RequestBody CourseDefinition courseDefinition) {
        if (courseDefinition != null) {
            String courses  = courseDefinitionService.saveCourseDefinition(courseDefinition);
            if (courses  != null) {
                return new ResponseEntity<>(courses , HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not Saved", HttpStatus.NOT_ACCEPTABLE);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/getAllCourseDefinitions")
    public ResponseEntity<Iterable<CourseDefinition>> getAllCourseDefinitions() {
        Iterable<CourseDefinition> courseDefinitions = courseDefinitionService.getAllCourseDefinitions();
        return new ResponseEntity<>(courseDefinitions, HttpStatus.OK);
    }

    @GetMapping(value = "/getCourseDefinitionById/{id}")
    public ResponseEntity<CourseDefinition> getCourseDefinitionById(@PathVariable Long id) {
        CourseDefinition courseDefinition = courseDefinitionService.getCourseDefinitionById(id);
        if (courseDefinition != null) {
            return new ResponseEntity<>(courseDefinition, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/updateCourseDefinition")
    public ResponseEntity<String> updateCourseDefinition(@RequestBody CourseDefinition courseDefinition) {
        String courses  = courseDefinitionService.updateCourseDefinition(courseDefinition);
        if (courses .equals("Course definition updated ")) {
            return new ResponseEntity<>(courses , HttpStatus.OK);
        } else {
            return new ResponseEntity<>(courses , HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deleteCourseDefinition/{id}")
    public ResponseEntity<String> deleteCourseDefinition(@PathVariable Long id) {
        String courses  = courseDefinitionService.deleteCourseDefinition(id);
        if (courses .equals("Course definition deleted ")) {
            return new ResponseEntity<>(courses , HttpStatus.OK);
        } else {
            return new ResponseEntity<>(courses , HttpStatus.NOT_FOUND);
        }
    }
}
