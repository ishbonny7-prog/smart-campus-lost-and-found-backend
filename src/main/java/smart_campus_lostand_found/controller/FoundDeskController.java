package smart_campus_lostand_found.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import smart_campus_lostand_found.entity.FoundDesk;
import smart_campus_lostand_found.service.FoundDeskService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/found-desks")
public class FoundDeskController {

    private final FoundDeskService service;
    public FoundDeskController(FoundDeskService service){
        this.service=service;
    }

    @PostMapping("/save")

    public ResponseEntity<String> save(@RequestBody FoundDesk desk){

        service.save(desk);
        return ResponseEntity.status(HttpStatus.CREATED)
        .body("Found desk saved successfully");
    }

    @GetMapping("/all")
    public List<FoundDesk> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id){
        FoundDesk desk=service.findById(id);

        if(desk==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("The found desk with that id does not exit");
        }
        return ResponseEntity.ok(desk);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id,@RequestBody FoundDesk desk){
        FoundDesk existing =service.findById(id);
        if (existing== null) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("The found desk with that id does not exit");
        }
        existing.setDeskNumber(desk.getDeskNumber());
        existing.setName(desk.getName());
        return ResponseEntity.ok(service.save(existing));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id){
        FoundDesk desk = service.findById(id);

        if (desk== null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("The found desk with  taht id does not exist");
        }
        service.deleteById(id);
        return ResponseEntity.ok("Found desk deleted successfully");
    }

    @GetMapping("/free")
    public List<FoundDesk> findFreeDesks(){
        return service.findFreeDesks();
    }


    
}
