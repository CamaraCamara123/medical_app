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
    DoctorRepository doctorRepository;

    public Doctor save(Doctor doctor){
        return doctorRepository.save(doctor);
    }

    public Doctor update(Doctor doctor){
        return doctorRepository.save(doctor);
    }

    public List<Doctor> findAll(){
        return doctorRepository.findAll();
    }

    public Doctor findById(Long id){
        return doctorRepository.findById(id).orElse(null);
    }

    public boolean delete(Long id){
        if(doctorRepository.existsById(id)){
            doctorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
