package smart_campus_lostand_found.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import smart_campus_lostand_found.entity.DeskOfficer;
import smart_campus_lostand_found.service.DeskOfficerService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/desk-officers")
public class DeskOfficerController {

    private final DeskOfficerService service;
    public DeskOfficerController(DeskOfficerService service){
        this.service= service;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody DeskOfficer officer){
        service.save(officer);
        return ResponseEntity.status(HttpStatus.CREATED)
        .body("Desk officer saved successfully");


    }

    @GetMapping("/all")
    public List <DeskOfficer> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id){
        DeskOfficer officer = service.findById(id);

        if (officer == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("THE DESK WITH OFFICER WITH TAHT ID DOES NOT EXIST");
        }
        return ResponseEntity.ok(officer);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id , @RequestBody DeskOfficer officer){
        DeskOfficer existing = service.findById(id);
        if (existing == null ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("The desk officer with that id does not exist");
        }
        existing.setFirstName(officer.getFirstName());
        existing.setLastName(officer.getLastName());
        existing.setEmail(officer.getEmail());
        existing.setFoundDesk(officer.getFoundDesk());

        return ResponseEntity.ok(service.save(existing));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id){

        DeskOfficer officer = service.findById(id);

        if (officer== null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("the desk officer with that id does not exits");

        }
        service.deleteById(id);
        return ResponseEntity.ok("Desk officer deleted successfully");
    }

    @GetMapping("/by-item-category")
    public List<DeskOfficer> findByItemCategoryName(@RequestParam String name){
        return service.findByItemCategoryName(name);
    }
    
}
