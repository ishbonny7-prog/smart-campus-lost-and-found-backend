package smart_campus_lostand_found.service;

import org.springframework.stereotype.Service;
import smart_campus_lostand_found.entity.ReturnRecord;
import smart_campus_lostand_found.repository.ReturnRecordRepository;
import java.util.List;
import java.util.UUID;

@Service
public class ReturnRecordService {
    private final ReturnRecordRepository repository;
    public ReturnRecordService(ReturnRecordRepository repository){
        this.repository= repository;
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

}
