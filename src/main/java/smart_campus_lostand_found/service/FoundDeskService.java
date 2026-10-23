package smart_campus_lostand_found.service;
import org.springframework.stereotype.Service;
import smart_campus_lostand_found.entity.FoundDesk;
import smart_campus_lostand_found.repository.FoundDeskRepository;
import java.util.List;
import java.util.UUID;

@Service
public class FoundDeskService {

    private final FoundDeskRepository repository ;

    public FoundDeskService(FoundDeskRepository repository){
        this.repository = repository;
    }
    
    public List<FoundDesk> findAll(){
        return repository.findAll();
    }

    public FoundDesk findById( UUID id ){
        return repository.findById(id).orElse(null);
    }

    public void deleteById(UUID id){
        repository.deleteById(id);
    }
}
