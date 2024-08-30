package ma.medical_app.medical_app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ma.medical_app.medical_app.entities.Doctor;
import ma.medical_app.medical_app.repositories.DoctorRepository;

@Service
@Transactional
public class DoctorService {
    @Autowired 
    DoctorRepository DoctorRepository;

    public Doctor save(Doctor doctor){
        return DoctorRepository.save(doctor);
    }

    public Doctor update(Doctor doctor){
        return DoctorRepository.save(doctor);
    }

    public List<Doctor> findAll(){
        return DoctorRepository.findAll();
    }

    public Doctor findById(Long id){
        return DoctorRepository.findById(id).orElse(null);
    }

    public boolean delete(Long id){
        if(DoctorRepository.existsById(id)){
            DoctorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
