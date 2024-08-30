package ma.medical_app.medical_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.medical_app.medical_app.entities.Creneau;
import ma.medical_app.medical_app.services.CreneauService;

@RestController
@RequestMapping("/creneaux")
public class CreneauController {

    @Autowired
    private CreneauService creneauService;

    @PostMapping
    public ResponseEntity<Creneau> createCreneau(@RequestBody Creneau creneau) {
        Creneau savedCreneau = creneauService.save(creneau);
        return new ResponseEntity<>(savedCreneau, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Creneau> updateCreneau(@PathVariable Long id, @RequestBody Creneau creneau) {
        creneau.setId(id);
        Creneau updatedCreneau = creneauService.update(creneau);
        return new ResponseEntity<>(updatedCreneau, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Creneau>> getAllCreneaux() {
        List<Creneau> creneaux = creneauService.findAll();
        return new ResponseEntity<>(creneaux, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Creneau> getCreneauById(@PathVariable Long id) {
        Creneau creneau = creneauService.findById(id);
        if (creneau != null) {
            return new ResponseEntity<>(creneau, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCreneau(@PathVariable Long id) {
        return ResponseEntity.ok(creneauService.delete(id));
    }
}

