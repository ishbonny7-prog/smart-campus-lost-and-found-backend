package smart_campus_lostand_found.service;

import org.springframework.stereotype.Service;
import smart_campus_lostand_found.entity.ReturnRecord;
import smart_campus_lostand_found.repository.ReturnRecordRepository;
import java.util.List;
import java.util.UUID;
import java.time.LocalDate;
import smart_campus_lostand_found.entity.LostReport;
import smart_campus_lostand_found.entity.ReportStatus;
import smart_campus_lostand_found.repository.LostReportRepository;

@Service
public class ReturnRecordService {
    private final ReturnRecordRepository repository;
    private final LostReportRepository lostReportRepository;
    public ReturnRecordService(ReturnRecordRepository repository,LostReportRepository lostReportRepository){
        this.repository= repository;
        this.lostReportRepository= lostReportRepository;
    }
    

   public ReturnRecord save(ReturnRecord record){
    return repository.save(record);

   }

   public List<ReturnRecord> findAll(){
    return repository.findAll();
   }

   public ReturnRecord findById(UUID id){
    return repository.findById(id).orElse(null);
   }

   public void deleteById(UUID id){
    repository.deleteById(id);
   }

   public String saveReturnRecord(ReturnRecord record , UUID reportId){
    
    LostReport report = lostReportRepository.findById(reportId).orElse(null);

    if (report == null) return "the lost report with that id does not exist";
    if (report.getStatus() != ReportStatus.RECOVERED)
       return "Return records are only for RECOVERED lost reports";

       record.setLostReport(report);
       record.setReturnedDate(LocalDate.now());
       repository.save(record);
       
       return "Return record saved successfully";
   }
   
   public List<ReturnRecord> findByOwnerCampusId(String campusId){
    return repository.findByOwnerCampusId(campusId);
   }

}
