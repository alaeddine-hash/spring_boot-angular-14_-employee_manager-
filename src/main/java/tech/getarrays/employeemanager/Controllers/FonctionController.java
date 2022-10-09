package tech.getarrays.employeemanager.Controllers;


import org.springframework.web.bind.annotation.*;
import tech.getarrays.employeemanager.Entities.Fonction;
import tech.getarrays.employeemanager.Services.FonctionService;

import java.util.List;

@RestController
@RequestMapping("/Fonctions")
public class FonctionController {
    final
    FonctionService FonctionService ;

    public FonctionController(FonctionService FonctionService) {
        this.FonctionService = FonctionService;
    }
    @PostMapping("/add")
    @ResponseBody
    public Fonction add_New_Fonction(@RequestBody Fonction fonction) {
        FonctionService.addFonction(fonction);
        return fonction;
    }


    @GetMapping("/all")
    @ResponseBody
    public List<Fonction> all_Fonctions(){
        return FonctionService.findAllFonction();
    }


    @GetMapping("/id/{id}")
    @ResponseBody
    public Fonction mployee_id(@PathVariable("id") Integer id){
        return FonctionService.findFonctionById(id);
    }


    @PutMapping("/update")
    @ResponseBody
    public void Mettre_A_jours_Fonction(@RequestBody Fonction fonction){FonctionService.updateFonction(fonction); }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void supp_Fonction(@PathVariable("id") Integer id){FonctionService.deleteFonction(id); }


}
