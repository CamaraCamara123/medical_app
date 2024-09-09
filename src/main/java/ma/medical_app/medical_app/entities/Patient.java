package ma.medical_app.medical_app.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.medical_app.medical_app.security.entities.User;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("Patient")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Patient extends User{

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/
    @OneToMany(fetch = FetchType.EAGER ,mappedBy = "patient")
    private List<RendezVous> rendezVous;
}
