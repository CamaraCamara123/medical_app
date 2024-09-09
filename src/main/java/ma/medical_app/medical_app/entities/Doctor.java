package ma.medical_app.medical_app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.medical_app.medical_app.security.entities.User;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("Doctor")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Doctor extends User {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/
    private String specialty;
    private String cabinetLocation;
    @OneToOne(mappedBy = "doctor")
    private Creneau creneau;
}
