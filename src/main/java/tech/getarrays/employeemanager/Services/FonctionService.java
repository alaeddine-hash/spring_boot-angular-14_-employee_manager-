package tech.getarrays.employeemanager.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.employeemanager.Entities.Fonction;
import tech.getarrays.employeemanager.Repositories.FonctionRepo;
import tech.getarrays.employeemanager.exception.UserNotFoundException;

import java.util.List;

@Service
public class FonctionService {
    private final FonctionRepo fonctionRepo;
    @Autowired
    public FonctionService(FonctionRepo fonctionRepo) {
        this.fonctionRepo = fonctionRepo;
    }
    public Fonction addFonction(Fonction fonction) {
        return fonctionRepo.save(fonction);
    }

    public List<Fonction> findAllFonction() {
        return fonctionRepo.findAll();
    }

    public Fonction updateFonction(Fonction fonction) {
        return fonctionRepo.save(fonction);
    }

    public Fonction findFonctionById(int id) {
        return fonctionRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Leave by id " + id + " was not found"));
    }

    public void deleteFonction(Integer id){
        fonctionRepo.deleteById(id);
    }


    public Integer find_ID_Fonction_By_Nom(String nomFonction){
        Fonction fonction = fonctionRepo.findByName(nomFonction) ;
        Integer res = fonction.getId_fonction() ;
        return res ;
    }
}



