package smart_campus_lostand_found.service;

import org.springframework.stereotype.Service;
import smart_campus_lostand_found.entity.DeskOfficer;
import smart_campus_lostand_found.entity.FoundDesk;
import smart_campus_lostand_found.repository.DeskOfficerRepository;
import smart_campus_lostand_found.repository.FoundDeskRepository;

import java.util.List;
import java.util.UUID;
import smart_campus_lostand_found.entity.ItemCategory;
import smart_campus_lostand_found.repository.ItemCategoryRepository;

@Service
public class DeskOfficerService {
    private final DeskOfficerRepository repository;
    private final ItemCategoryRepository categoryRepository;
    private final FoundDeskRepository foundDeskRepository;

    public DeskOfficerService(DeskOfficerRepository repository, ItemCategoryRepository categoryRepository,FoundDeskRepository foundDeskRepository){
        this.repository= repository;
        this.categoryRepository= categoryRepository;
        this.foundDeskRepository=foundDeskRepository;
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
     public List<DeskOfficer> findByItemCategoryName(String name){
        return repository.findByItemCategoryName(name);
     }

     public String addCategory(UUID officerId, UUID categoryId){
        DeskOfficer officer = findById(officerId);
        ItemCategory category = categoryRepository.findById(categoryId).orElse(null);

        if (officer == null) return "the desk officer with that id does not exit";
        if (category == null) return "that item category does not exist";
        if (officer.getItemCategories().contains(category))
        return "the desk officer already has that item category";

        officer.getItemCategories().add(category);
        repository.save(officer);
        

       return "item category added to the desk officer";

     }

     public String changeDesk(UUID officeId, int deskNumber){
        DeskOfficer officer = findById(officeId);

        if (officer == null) return "the desk with that id does nto exist";
        FoundDesk desk = foundDeskRepository.findByDeskNumber(deskNumber).orElse(null);
        if (desk == null) return "the found desk with that deskNumber does not exist";

        if (repository.existsByFoundDeskAndIdNot(desk, officeId))
        return "that found desk is taken by another desk officer";

        officer.setFoundDesk(desk);
        repository.save(officer);
        return "found desk changed successfully";

     }
}
