package tech.getarrays.employeemanager.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.getarrays.employeemanager.Entities.Fonction;

import java.util.List;

@Repository
public interface FonctionRepo extends JpaRepository<Fonction,Integer> {
   @Query(value = " SELECT f from Fonction f where f.name = ?1 ")
   public List<Fonction> findAllByName(String nom) ;

   Fonction findByName(String nomFonction);
}
