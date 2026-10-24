package smart_campus_lostand_found.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import smart_campus_lostand_found.entity.Owner;
import smart_campus_lostand_found.service.OwnerService;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/owners")
public class OwnerController {
    private  final OwnerService service ;

    public OwnerController(OwnerService service){
        this.service= service;
    }

   @PostMapping("save")
   public ResponseEntity<String> save (@RequestBody Owner owner){
    service.save(owner);
    return ResponseEntity.status(HttpStatus.CREATED)
    .body("Owner saved successfully");
   }

   @GetMapping("/all")
   public List<Owner> findAll(){
    return service.findAll();
   }

   @GetMapping("/{id}")
   public ResponseEntity<?> findById(@PathVariable UUID id){
    Owner owner = service.findById(id);

    if (owner== null){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body("The owner with that id does not exist");
    }
    return ResponseEntity.ok(owner);
   }

   @PutMapping("/update/{id}")
   public ResponseEntity<?> update(@PathVariable UUID id,@RequestBody Owner owner){
   
    Owner existing = service.findById(id);

    if(existing== null){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body("The owner with that id does not exits");

    }

    existing.setFirstName(owner.getFirstName());
    existing.setLastName(owner.getLastName());
    existing.setCampusId(owner.getCampusId());
    existing.setEmail(owner.getEmail());
    existing.setPhone(owner.getPhone());

    return ResponseEntity.ok(service.save(existing));

   }
 @DeleteMapping("/delete/{id}")
 public ResponseEntity<String> delete(@PathVariable UUID id){
    Owner owner = service.findById(id);
    if (owner== null){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body("The owner with that id does not exist");
    }
    service.deleteById(id);
    return ResponseEntity.ok( "Owner deleted successfully");
 }

    
}
