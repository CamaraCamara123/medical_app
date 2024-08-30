package ma.medical_app.medical_app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ma.medical_app.medical_app.entities.TimeSlot;
import ma.medical_app.medical_app.repositories.TimeSlotRepository;


@Service
@Transactional
public class TimeSlotService {
    
    @Autowired 
    TimeSlotRepository TimeSlotRepository;

    public TimeSlot save(TimeSlot timeSlot){
        return TimeSlotRepository.save(timeSlot);
    }

    public TimeSlot update(TimeSlot timeSlot){
        return TimeSlotRepository.save(timeSlot);
    }

    public List<TimeSlot> findAll(){
        return TimeSlotRepository.findAll();
    }

    public TimeSlot findById(Long id){
        return TimeSlotRepository.findById(id).orElse(null);
    }

    public boolean delete(Long id){
        if(TimeSlotRepository.existsById(id)){
            TimeSlotRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
