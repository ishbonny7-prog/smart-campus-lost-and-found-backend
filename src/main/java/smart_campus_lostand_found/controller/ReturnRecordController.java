package smart_campus_lostand_found.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import smart_campus_lostand_found.entity.ReturnRecord;
import smart_campus_lostand_found.service.ReturnRecordService;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/api/return-records")
public class ReturnRecordController {

    private final ReturnRecordService service;

    public ReturnRecordController(ReturnRecordService service){
        this.service=service;
    }
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody ReturnRecord record, @RequestParam UUID lostReportId){
        String message = service.saveReturnRecord(record, lostReportId);

        if(message.equals("the lost report with taht id does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        if(message.equals("Return record arae only for RECOVERED lost reports")){
            return ResponseEntity.badRequest().body(message);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
    
    @GetMapping("/owner/{campusId}")
    public List<ReturnRecord> findByOwnerCampusId(@PathVariable String campusId){
        return service.findByOwnerCampusId(campusId);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete (@PathVariable UUID id){
        if (service.findById(id)==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("the return record with that id does not exist");
        }
        service.deleteById(id);
        return ResponseEntity.ok("Return record deleted successfully");
    }
}
