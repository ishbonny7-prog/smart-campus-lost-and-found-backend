package smart_campus_lostand_found.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import smart_campus_lostand_found.entity.LostReport;
import smart_campus_lostand_found.entity.ReportStatus;
import smart_campus_lostand_found.service.LostReportService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
@RestController
@RequestMapping("/api/lost-reports")
public class LostReportController {
    public final LostReportService service;

    public LostReportController(LostReportService service){
        this.service= service;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody LostReport report){
        String message = service.save(report);

        if (message.equals("Lost report date cannot be in the past")){
            return ResponseEntity.badRequest().body(message);

        }
        if (message.equals("desk officer already handles a report on that date")){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);

        }
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
     
     
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



 @GetMapping("/by-status")
 public List<LostReport> findByStatus(@RequestParam ReportStatus status){
    return service.findByStatus(status);
 }

 @GetMapping ("/between")
 public List<LostReport> findBetween(@RequestParam String start,@RequestParam String end){
    return service.findBetween(LocalDate.parse(start),LocalDate.parse(end));
 }

 @PatchMapping("/{id}/status")
 public ResponseEntity<String> updateStatus(@PathVariable UUID id, @RequestParam ReportStatus status){
    String message = service.updateStatus(id, status);

    if (message.equals("The lost report with that id does not exist")){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
    if (message.startsWith("cannot change status")){
        return ResponseEntity.badRequest().body(message);
    }

    return ResponseEntity.ok(message);

 }

 @DeleteMapping("/delete/{id}")
 public ResponseEntity<String> delete(@PathVariable UUID id){
    String message = service.deleteById(id);

    if (message.equals("the lost report with that id does not exists")){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);

    }
    if(message.equals("that lost has return records , delete  them first")){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);

    }
    return ResponseEntity.ok(message);
 }

 @GetMapping("/stats/by-status") 
 public List<Object[]> countReportsByStatus(){
    return service.countReportsByStatus();
 }

 @PatchMapping("/cancel-day")
 public ResponseEntity<String> cancelReports(
    @RequestParam UUID deskOfficerId,
    @RequestParam String date 
 ){

    int count = service.cancelReports(deskOfficerId, LocalDate.parse(date));
    return ResponseEntity.ok(count + " lost reports cancelled");
 }

 @GetMapping("/page")
 public Page<LostReport> findPage(
    @RequestParam(defaultValue = "0" )int page,
    @RequestParam(defaultValue = "5")int size,
    @RequestParam(defaultValue = "reportDate,desc") String sort){

        String[] parts = sort.split(",");
        Pageable pageable = PageRequest.of(page,size,Sort.by(Sort.Direction.fromString(parts[1]),parts[0]));
        return service.findPage(pageable);
    }
 
}
