package ma.medical_app.medical_app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ma.medical_app.medical_app.entities.CreneauTimeSlot;
import ma.medical_app.medical_app.repositories.CreneauTimeSlotRepository;


@Service
@Transactional
public class CreneauTimeSlotService {
    
    @Autowired 
    CreneauTimeSlotRepository creneauTimeSlotRepository;

    public CreneauTimeSlot save(CreneauTimeSlot creneauTimeSlot){
        return creneauTimeSlotRepository.save(creneauTimeSlot);
    }

    public CreneauTimeSlot update(CreneauTimeSlot creneauTimeSlot){
        return creneauTimeSlotRepository.save(creneauTimeSlot);
    }

    public List<CreneauTimeSlot> findAll(){
        return creneauTimeSlotRepository.findAll();
    }

    public CreneauTimeSlot findById(Long id){
        return creneauTimeSlotRepository.findById(id).orElse(null);
    }

    public boolean delete(Long id){
        if(creneauTimeSlotRepository.existsById(id)){
            creneauTimeSlotRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
