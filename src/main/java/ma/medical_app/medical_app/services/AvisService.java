package ma.medical_app.medical_app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ma.medical_app.medical_app.entities.Avis;
import ma.medical_app.medical_app.repositories.AvisRepository;

@Service
@Transactional
public class AvisService {
    
    @Autowired 
    AvisRepository avisRepository;
    public Avis save(Avis avis){
        return avisRepository.save(avis);
    }

    public Avis update(Avis avis){
        return avisRepository.save(avis);
    }

    public List<Avis> findAll(){
        return avisRepository.findAll();
    }

    public Avis findById(Long id){
        return avisRepository.findById(id).orElse(null);
    }

    public boolean delete(Long id){
        if(avisRepository.existsById(id)){
            avisRepository.deleteById(id);

            return true;
        }
        return false;
    }
}
