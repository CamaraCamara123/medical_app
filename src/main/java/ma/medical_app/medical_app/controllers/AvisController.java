package ma.medical_app.medical_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ma.medical_app.medical_app.entities.Avis;
import ma.medical_app.medical_app.services.AvisService;

@RestController
@RequestMapping("/avis")
public class AvisController {

    @Autowired
    private AvisService avisService;

    @PostMapping
    public ResponseEntity<Avis> createAvis(@RequestBody Avis avis) {
        Avis savedAvis = avisService.save(avis);
        return new ResponseEntity<>(savedAvis, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avis> updateAvis(@PathVariable Long id, @RequestBody Avis avis) {
        avis.setId(id); 
        Avis updatedAvis = avisService.update(avis);
        return new ResponseEntity<>(updatedAvis, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Avis>> getAllAvis() {
        List<Avis> avisList = avisService.findAll();
        return new ResponseEntity<>(avisList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avis> getAvisById(@PathVariable Long id) {
        Avis avis = avisService.findById(id);
        if (avis != null) {
            return new ResponseEntity<>(avis, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvis(@PathVariable Long id) {
        boolean deleted = avisService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

