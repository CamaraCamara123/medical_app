package ma.medical_app.medical_app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ma.medical_app.medical_app.entities.RendezVous;
import ma.medical_app.medical_app.repositories.RendezVousRepository;

@Service
@Transactional
public class RendezVousService {
    
    @Autowired 
    RendezVousRepository RendezVousRepository;

    public RendezVous save(RendezVous rendezVous){
        return RendezVousRepository.save(rendezVous);
    }

    public RendezVous update(RendezVous rendezVous){
        return RendezVousRepository.save(rendezVous);
    }

    public List<RendezVous> findAll(){
        return RendezVousRepository.findAll();
    }

    public RendezVous findById(Long id){
        return RendezVousRepository.findById(id).orElse(null);
    }

    public boolean delete(Long id){
        if(RendezVousRepository.existsById(id)){
            RendezVousRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
