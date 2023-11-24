package auca.rw.registration.AucaRegistration.Service;

import auca.rw.registration.AucaRegistration.Domain.AcademicUnit;
import auca.rw.registration.AucaRegistration.Repository.AcademicUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcademicUnitService {
    @Autowired
    private AcademicUnitRepository academicUnitRepository;

    public String saveAcademicUnit(AcademicUnit academicUnit) {
        if (academicUnit != null) {
            academicUnitRepository.save(academicUnit);
            return "Academic unit Saved";
        } else {
            return null;
        }
    }

    public Iterable<AcademicUnit> getAllAcademicUnits() {
        return academicUnitRepository.findAll();
    }

    public AcademicUnit getAcademicUnitById(String id) {
        return academicUnitRepository.findById(id).orElse(null);
    }

    public String updateAcademicUnit(AcademicUnit academicUnit) {
        if (academicUnit != null && academicUnitRepository.existsById(academicUnit.getId())) {
            academicUnitRepository.save(academicUnit);
            return "Academic unit ";
        } else {
            return "Not found";
        }
    }

    public String deleteAcademicUnit(String id) {
        if (academicUnitRepository.existsById(id)) {
            academicUnitRepository.deleteById(id);
            return "Academic unit ";
        } else {
            return "Not found";
        }
    }

}
