package ma.medical_app.medical_app.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.medical_app.medical_app.security.entities.User;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "patient")
    private List<RendezVous> rendezVous;
}
