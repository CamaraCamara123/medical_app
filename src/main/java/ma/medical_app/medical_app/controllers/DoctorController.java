package ma.medical_app.medical_app.controllers;

import java.util.List;

import ma.medical_app.medical_app.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ma.medical_app.medical_app.entities.Doctor;
import ma.medical_app.medical_app.services.DoctorService;

@RestController
@RequestMapping("/api/v1/auth/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private final DoctorRepository doctorRepository;

    public DoctorController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        Doctor savedDoctor = doctorService.save(doctor);
        return new ResponseEntity<>(savedDoctor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        doctor.setId(id); // S'assurer que le doctor à mettre à jour a l'ID approprié
        Doctor updatedDoctor = doctorService.update(doctor);
        return new ResponseEntity<>(updatedDoctor, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() { // Corrected return type
        List<Doctor> doctors = doctorRepository.findAll();
        System.out.println("doctors are : "+doctors);
        return new ResponseEntity<>(doctors, HttpStatus.OK); // Returning ResponseEntity
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorService.findById(id);
        if (doctor != null) {
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        boolean deleted = doctorService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

