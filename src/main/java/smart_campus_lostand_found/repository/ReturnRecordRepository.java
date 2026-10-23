package smart_campus_lostand_found.repository;
import smart_campus_lostand_found.entity.ReturnRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import smart_campus_lostand_found.entity.LostReport;

public interface ReturnRecordRepository extends JpaRepository<ReturnRecord,UUID>{
    boolean exitsByLostReport( LostReport lostReport);
    @Query ("SELECT r FROM Return Record r WHERE r.lostReport.owner.campusId= :campusId ORDER BY r. returnedDate Desc")
    List<ReturnRecord> findByOwnerCampusId(String campusId );
    
}
