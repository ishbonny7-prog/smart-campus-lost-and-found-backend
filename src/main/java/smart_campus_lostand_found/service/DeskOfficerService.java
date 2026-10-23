package smart_campus_lostand_found.service;

import org.springframework.stereotype.Service;
import smart_campus_lostand_found.entity.DeskOfficer;
import smart_campus_lostand_found.repository.DeskOfficerRepository;
import java.util.List;
import java.util.UUID;

@Service
public class DeskOfficerService {
    private final DeskOfficerRepository repository;

    public DeskOfficerService(DeskOfficerRepository repository){
        this.repository= repository;
    }

    public DeskOfficer save(DeskOfficer officer){
        return repository.save(officer);
    }
    public  List <DeskOfficer> findAll(){
        return repository.findAll();
    }

    public DeskOfficer findById(UUID id){
        return repository.findById(id).orElse(null);
    }


    public void deleteById(UUID id){
        repository.deleteById(id);
    }
    
}
