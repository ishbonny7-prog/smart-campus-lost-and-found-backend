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
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;

import smart_campus_lostand_found.entity.DeskOfficer;


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
    AND r.status<> smart_campus_lostand_found.entity.ReportStatus.RECOVERED
        
        """)

       int closeReports( @Param("officerId") UUID officerId,@Param("date") LocalDate date);

       boolean existsByDeskOfficerAndReportDateAndStatusNot(DeskOfficer officer, LocalDate date,ReportStatus status);
       
@Query ("SELECT r.status, COUNT(r) FROM LostReport r GROUP BY r.status")
List<Object[]> countReportsByStatus();

long countByReportDate(LocalDate reportDate);

long countReportsByStatus(ReportStatus status);


    
}
