package auca.rw.registration.AucaRegistration.Controller;

import auca.rw.registration.AucaRegistration.Domain.AcademicUnit;
import auca.rw.registration.AucaRegistration.Service.AcademicUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/academicUnit", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
public class AcademicUnitController {
    @Autowired
    private AcademicUnitService academicUnitService;

    @PostMapping(value = "/saveAcademicUnit")
    public ResponseEntity<?> saveAcademicUnit(@RequestBody AcademicUnit academicUnit) {
        if (academicUnit != null) {
            String result = academicUnitService.saveAcademicUnit(academicUnit);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not Saved", HttpStatus.NOT_ACCEPTABLE);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/getAllAcademicUnits")
    public ResponseEntity<Iterable<AcademicUnit>> getAllAcademicUnits() {
        Iterable<AcademicUnit> academicUnits = academicUnitService.getAllAcademicUnits();
        return new ResponseEntity<>(academicUnits, HttpStatus.OK);
    }

    @GetMapping(value = "/getAcademicUnitById/{id}")
    public ResponseEntity<AcademicUnit> getAcademicUnitById(@PathVariable String id) {
        AcademicUnit academicUnit = academicUnitService.getAcademicUnitById(id);
        if (academicUnit != null) {
            return new ResponseEntity<>(academicUnit, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/updateAcademicUnit")
    public ResponseEntity<String> updateAcademicUnit(@RequestBody AcademicUnit academicUnit) {
        String result = academicUnitService.updateAcademicUnit(academicUnit);
        if (result.equals("Academic unit updated ")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deleteAcademicUnit/{id}")
    public ResponseEntity<String> deleteAcademicUnit(@PathVariable String id) {
        String result = academicUnitService.deleteAcademicUnit(id);
        if (result.equals("Academic unit deleted ")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }
}
