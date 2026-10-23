package smart_campus_lostand_found.service;

import org.springframework.stereotype.Service;
import smart_campus_lostand_found.entity.Owner;
import smart_campus_lostand_found.repository.OwnerRepository;
import java.util.List;
import java.util.UUID;

@Service
public class OwnerService {
    private final OwnerRepository repository;

    public OwnerService(OwnerRepository repository) {
        this.repository= repository;
    }

    public Owner save(Owner owner){
        return repository.save(owner);
    }

    public List <Owner> findAll(){
        return repository.findAll();
    }

    public Owner findById(UUID id){
        return repository.findById(id).orElse(null);
    }

    public void deleteById(UUID id){

        repository.deleteById(id);
    }

    
}
