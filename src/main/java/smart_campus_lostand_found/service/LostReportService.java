package smart_campus_lostand_found.service;

import org.springframework.stereotype.Service;
import smart_campus_lostand_found.entity.LostReport;
import smart_campus_lostand_found.repository.LostReportRepository;
import java.util.List;
import java.util.UUID;

@Service
public class LostReportService {

    private final LostReportRepository repository;

    public LostReportService(LostReportRepository repository){
        this.repository=repository;
    }

    public LostReport save(LostReport report){
        return repository.save(report);
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



    
}
