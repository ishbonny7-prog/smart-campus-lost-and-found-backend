package smart_campus_lostand_found.repository;

import smart_campus_lostand_found.entity.FoundDesk;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import smart_campus_lostand_found.entity.DeskOfficer;

public interface FoundDeskRepository extends JpaRepository<FoundDesk,UUID> {

    java.util.Optional<FoundDesk> findByDeskNumber(int deskNumber);

    boolean existsByDeskNumber(int deskNumber);
@Query("""
    SELECT f FROM FoundDesk f
    where NOT EXISTS (SELECT d FROM DeskOfficeer d 
    WHERE d.foundDesk = f)
    ORDER BY f.deskNumber
        
        """)

        List < FoundDesk> findFreeDesks();
     
      

  
  

 


            

            
    
}
