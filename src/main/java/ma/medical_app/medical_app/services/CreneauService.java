package ma.medical_app.medical_app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ma.medical_app.medical_app.entities.Creneau;
import ma.medical_app.medical_app.repositories.CreneauRepository;

@Service
@Transactional
public class CreneauService {

    @Autowired 
    CreneauRepository creneauRepository;

    public Creneau save(Creneau creneau){
        return creneauRepository.save(creneau);
    }

    public Creneau update(Creneau creneau){
        return creneauRepository.save(creneau);
    }

    public List<Creneau> findAll(){
        return creneauRepository.findAll();
    }

    public Creneau findById(Long id){
        return creneauRepository.findById(id).orElse(null);
    }

    public boolean delete(Long id){
        if(creneauRepository.existsById(id)){
            creneauRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

