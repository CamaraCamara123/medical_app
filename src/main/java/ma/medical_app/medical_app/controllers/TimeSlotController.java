package ma.medical_app.medical_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ma.medical_app.medical_app.entities.TimeSlot;
import ma.medical_app.medical_app.services.TimeSlotService;

@RestController
@RequestMapping("/timeslots")
public class TimeSlotController {

    @Autowired
    private TimeSlotService timeSlotService;

    @PostMapping
    public ResponseEntity<TimeSlot> createTimeSlot(@RequestBody TimeSlot timeSlot) {
        TimeSlot savedTimeSlot = timeSlotService.save(timeSlot);
        return new ResponseEntity<>(savedTimeSlot, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TimeSlot> updateTimeSlot(@PathVariable Long id, @RequestBody TimeSlot timeSlot) {
        timeSlot.setId(id);
        TimeSlot updatedTimeSlot = timeSlotService.update(timeSlot);
        return new ResponseEntity<>(updatedTimeSlot, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TimeSlot>> getAllTimeSlots() {
        List<TimeSlot> timeSlotList = timeSlotService.findAll();
        return new ResponseEntity<>(timeSlotList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeSlot> getTimeSlotById(@PathVariable Long id) {
        TimeSlot timeSlot = timeSlotService.findById(id);
        if (timeSlot != null) {
            return new ResponseEntity<>(timeSlot, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimeSlot(@PathVariable Long id) {
        boolean deleted = timeSlotService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

