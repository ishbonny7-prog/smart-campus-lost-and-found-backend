package smart_campus_lostand_found.service;
import org.springframework.stereotype.Service;
import smart_campus_lostand_found.repository.ItemCategoryRepository;
import smart_campus_lostand_found.entity.ItemCategory;
import java.util.List;


@Service
public class ItemCategoryService {
    public final ItemCategoryRepository repository;

    public ItemCategoryService(ItemCategoryRepository repository){
        this.repository=repository;
    }
    public ItemCategory save (ItemCategory category){
        return repository.save(category);
    }

    public List < ItemCategory> findAll (){
        return repository.findAll();
    }

    
}
