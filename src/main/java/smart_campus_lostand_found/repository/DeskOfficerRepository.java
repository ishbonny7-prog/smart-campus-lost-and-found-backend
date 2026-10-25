package smart_campus_lostand_found.repository;

import smart_campus_lostand_found.entity.DeskOfficer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import smart_campus_lostand_found.entity.FoundDesk;

public interface DeskOfficerRepository extends JpaRepository<DeskOfficer,UUID> {
    @Query ("SELECT DISTINCT d FROM DeskOfficer d JOIN d.itemCategories c WHERE LOWER(C.name)= LOWER(:name)")
    List <DeskOfficer>  findByItemCategoryName(@Param("name")String name);

    boolean existsByFoundDesk(FoundDesk foundDesk);
}
