package smart_campus_lostand_found.repository;

import smart_campus_lostand_found.entity.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;
import org.springframework.data.jpa.repository.Query;










public interface ItemCategoryRepository extends JpaRepository<ItemCategory, UUID> {
    @Query ("SELECT c FROM ItemCategory c WHERE c.deskOfficers IS EMPTY")
    List <ItemCategory> findUnusedCategories();


   

    
}
