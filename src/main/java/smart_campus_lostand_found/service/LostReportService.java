package smart_campus_lostand_found.service;

import org.springframework.stereotype.Service;
import smart_campus_lostand_found.entity.LostReport;
import smart_campus_lostand_found.repository.LostReportRepository;
import smart_campus_lostand_found.entity.DeskOfficer;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import smart_campus_lostand_found.entity.ReportStatus;

@Service
public class LostReportService {

    private final LostReportRepository repository;

    public LostReportService(LostReportRepository repository){
        this.repository=repository;
    }



    public List<LostReport> findAll(){
        return repository.findAll();
    }

    public LostReport findById(UUID id){
        return repository.findById(id).orElse(null);
    }

    public void deleteById(UUID id){
        repository.deleteById(id);
    }

    public List<LostReport> findByStatus(ReportStatus status) {
        return repository.findByStatusOrderByReportDateAsc(status);
    }

    public List <LostReport> findBetween(LocalDate start,LocalDate end){
        return repository .findByReportDateBetweenOrderByReportDateAsc(start, end);
    }


    public String validateAndSave(LostReport report){
        if (report.getReporDate().isBefore(LocalDate.now())){
            return "Lost report date cannot be in the past";
        }
        DeskOfficer officer = report.getDeskOfficer();

        if(repository.existsByDeskOfficerAndReportDateAndStatusNot(officer, report.getReporDate(),ReportStatus.CLOSED)){
            return "Desk officer already handles a report on that date";
        }
        report.setStatus(ReportStatus.REPORTED);
        repository.save(report);
        return "Lost report saved successfully";
    }

    public String save(LostReport report){
        if (report.getReporDate().isBefore(LocalDate.now())){
            return "lost report date cannot be in the past ";

        }
        if (repository.existsByDeskOfficerAndReportDateAndStatusNot(report.getDeskOfficer(),report.getReporDate(),ReportStatus.CLOSED)){
            return "Desk officer already handles a repot on that date";
        }

        report.setStatus(ReportStatus.REPORTED);
        repository.save(report);
        return "Lost report saved successfully";
    }


    public String updateStatus(UUID id, ReportStatus status){
        LostReport report = findById(id);

        if (report == null){
            return "The lost report with that id does not exist";

        }
        ReportStatus old = report.getStatus();
        if ((old== ReportStatus.REPORTED && (status==ReportStatus.MATCHED || status == ReportStatus.CLOSED)) || (old == ReportStatus.MATCHED && (status ==ReportStatus.RECOVERED|| status == ReportStatus.CLOSED))) {
            report.setStatus(status);
            repository.save(report);
            return "Lost report status updated successfully";

        }
        return "Cannot change status form "+old+"to "+ status;
    }
    



    
}
