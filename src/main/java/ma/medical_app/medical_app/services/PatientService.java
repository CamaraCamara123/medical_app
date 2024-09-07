package ma.medical_app.medical_app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ma.medical_app.medical_app.entities.Creneau;
import ma.medical_app.medical_app.entities.Patient;
import ma.medical_app.medical_app.repositories.PatientRepository;

@Service
@Transactional
public class PatientService {
    @Autowired 
    PatientRepository PatientRepository;

    public Patient save(Patient patient){
        return PatientRepository.save(patient);
    }

    public Patient update(Patient patient){
        return PatientRepository.save(patient);
    }

    public List<Patient> findAll(){
        return PatientRepository.findAll();
    }

    public Patient findById(Long id){
        return PatientRepository.findById(id).orElse(null);
    }

    public boolean delete(Long id){
        if(PatientRepository.existsById(id)){
            PatientRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Get a rendez vous with a doctor

    public void takeRendezVous(Patient patient,Creneau creneau) {
        // TODO: implement method
    }
}
