package ma.medical_app.medical_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ma.medical_app.medical_app.entities.CreneauTimeSlot;
import ma.medical_app.medical_app.services.CreneauTimeSlotService;

@RestController
@RequestMapping("/creneau-timeslots")
public class CreneauTimeSlotController {

    @Autowired
    private CreneauTimeSlotService creneauTimeSlotService;

    @PostMapping
    public ResponseEntity<CreneauTimeSlot> createCreneauTimeSlot(@RequestBody CreneauTimeSlot creneauTimeSlot) {
        CreneauTimeSlot savedCreneauTimeSlot = creneauTimeSlotService.save(creneauTimeSlot);
        return new ResponseEntity<>(savedCreneauTimeSlot, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreneauTimeSlot> updateCreneauTimeSlot(@PathVariable Long id, @RequestBody CreneauTimeSlot creneauTimeSlot) {
        creneauTimeSlot.setId(id);
        CreneauTimeSlot updatedCreneauTimeSlot = creneauTimeSlotService.update(creneauTimeSlot);
        return new ResponseEntity<>(updatedCreneauTimeSlot, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CreneauTimeSlot>> getAllCreneauTimeSlots() {
        List<CreneauTimeSlot> creneauTimeSlots = creneauTimeSlotService.findAll();
        return new ResponseEntity<>(creneauTimeSlots, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreneauTimeSlot> getCreneauTimeSlotById(@PathVariable Long id) {
        CreneauTimeSlot creneauTimeSlot = creneauTimeSlotService.findById(id);
        if (creneauTimeSlot != null) {
            return new ResponseEntity<>(creneauTimeSlot, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCreneauTimeSlot(@PathVariable Long id) {
        boolean deleted = creneauTimeSlotService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

