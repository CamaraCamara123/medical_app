package ma.medical_app.medical_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.medical_app.medical_app.entities.Avis;

@Repository
public interface AvisRepository extends JpaRepository<Avis, Long> {

}
