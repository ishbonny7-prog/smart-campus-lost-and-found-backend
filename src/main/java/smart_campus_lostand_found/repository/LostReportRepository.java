package smart_campus_lostand_found.repository;
import smart_campus_lostand_found.entity.LostReport;
import org.springframework.data.jpa.repository.JpaRepository;
import java .util.UUID;
import java.util.List;
import java.time.LocalDate;
import smart_campus_lostand_found.entity.ReportStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface LostReportRepository extends JpaRepository< LostReport,UUID> {
    List<LostReport> findByStatusOrderByReportDateAsc(ReportStatus status);

    List<LostReport> findByReportDateBetweenOrderByReportDateAsc(LocalDate start , LocalDate end);
@Modifying
@Transactional
@Query( """
    UPDATE LostReport r
    SET r.status = smart_campus_lostand_found.entity.ReportStatus.CLOSED
    WHERE r.deskOfficer.id = :officerId
    AND r.reportDate = :date
    AND r.status<> smart_campus_lostland_found.entity.ReportStatus.RECOVERED
        
        """)

       int closeReports( @Param("officeId") UUID offUuid,@Param("date") LocalDate date);
       

    
}
