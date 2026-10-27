package smart_campus_lostand_found.service;

import org.springframework.stereotype.Service;
import smart_campus_lostand_found.repository.LostReportRepository;
import smart_campus_lostand_found.repository.OwnerRepository;
import smart_campus_lostand_found.repository.DeskOfficerRepository;
import smart_campus_lostand_found.repository.FoundDeskRepository;
import smart_campus_lostand_found.repository.ReturnRecordRepository;
import smart_campus_lostand_found.entity.ReportStatus;
import java.time.LocalDate;
import java.util.Map;
import  java.util.LinkedHashMap;




@Service

public class DashboardService {
    private final LostReportRepository reportRepository;
    private final OwnerRepository ownerRepository;
    private final DeskOfficerRepository officerRepository;
    private final FoundDeskRepository deskRepository;
    private final ReturnRecordRepository returnRepository;

    public DashboardService( LostReportRepository reportRepository,OwnerRepository ownerRepository, DeskOfficerRepository officerRepository, FoundDeskRepository deskRepository, ReturnRecordRepository returnRepository){

        this.reportRepository = reportRepository;
        this.ownerRepository = ownerRepository;
        this.officerRepository = officerRepository;
        this.deskRepository= deskRepository;
        this.returnRepository= returnRepository;
    }

    public Map<String, Long> getSummary(){
        Map<String,Long> summary = new LinkedHashMap<>();
        summary.put("ownner", ownerRepository.count());
        summary.put("deskOfficers",officerRepository.count());
        summary.put("freeFoundDesks",(long) deskRepository.findFreeDesks().size());
        summary.put("reportsToday",reportRepository.countByReportDate(LocalDate.now()));
        summary.put("reported", reportRepository.countReportsByStatus(ReportStatus.REPORTED));
        summary.put("returnRecords",returnRepository.count());

        return summary;

    }
    
}
