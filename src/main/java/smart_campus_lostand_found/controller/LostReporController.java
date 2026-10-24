package smart_campus_lostand_found.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import smart_campus_lostand_found.entity.LostReport;
import smart_campus_lostand_found.service.LostReportService;

import java.util.UUID;

@RestController
@RequestMapping("/api/lost-reports")
public class LostReporController {
    public final LostReportService service;

    public LostReporController(LostReportService service){
        this.service= service;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody LostReport report){
        service.save(report);
        return ResponseEntity.status(HttpStatus.CREATED)
        .body("Lost report saved successfully");
    }

    @GetMapping("/{id}")
public ResponseEntity<?> findById(@PathVariable UUID id )   {
    LostReport report = service.findById(id);

    if (report == null){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body("The lost report with that id does not exist");
    }
    return ResponseEntity.ok(report);
}

@PutMapping("/update/{id}")
 public ResponseEntity<?> update(@PathVariable UUID id , @RequestBody LostReport report){
    LostReport existing= service.findById(id);
    if (existing == null){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body("THE lost report with that id does not exist");
    }
    existing.setReportDate(report.getReportDate());
    existing.setItemDescription(report.getItemDescription());
    existing.setLostLocation(report.getLostLocation());
    existing.setOwner(report.getOwner());
    existing.setDeskOfficer(report.getDeskOfficer());

    return ResponseEntity.ok(service.save(existing));

 }


 @DeleteMapping("/delete/{id}}")
 public ResponseEntity<String> delete(@PathVariable UUID id){
    LostReport report= service.findById(id);

    if (report == null){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body("the lsot report with that id does not exist");
    }
    service.deleteById(id);
    return ResponseEntity.ok("lost report delete success fully ");
 }
}
