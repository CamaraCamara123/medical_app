package ma.medical_app.medical_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ma.medical_app.medical_app.entities.RendezVous;
import ma.medical_app.medical_app.services.RendezVousService;

@RestController
@RequestMapping("/rendezvous")
public class RendezVousController {

    @Autowired
    private RendezVousService rendezVousService;

    @PostMapping
    public ResponseEntity<RendezVous> createRendezVous(@RequestBody RendezVous rendezVous) {
        RendezVous savedRendezVous = rendezVousService.save(rendezVous);
        return new ResponseEntity<>(savedRendezVous, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RendezVous> updateRendezVous(@PathVariable Long id, @RequestBody RendezVous rendezVous) {
        rendezVous.setId(id);
        RendezVous updatedRendezVous = rendezVousService.update(rendezVous);
        return new ResponseEntity<>(updatedRendezVous, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RendezVous>> getAllRendezVous() {
        List<RendezVous> rendezVousList = rendezVousService.findAll();
        return new ResponseEntity<>(rendezVousList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RendezVous> getRendezVousById(@PathVariable Long id) {
        RendezVous rendezVous = rendezVousService.findById(id);
        if (rendezVous != null) {
            return new ResponseEntity<>(rendezVous, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRendezVous(@PathVariable Long id) {
        boolean deleted = rendezVousService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

