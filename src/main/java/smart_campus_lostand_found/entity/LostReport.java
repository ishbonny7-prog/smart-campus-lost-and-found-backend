package smart_campus_lostand_found.entity;
import jakarta.persistence.*;
import java.util.UUID;
import java.time.LocalDate;

@Entity
@Table(name = "lost_report")
public class LostReport {
 @Id
 @GeneratedValue(strategy = GenerationType.UUID) 
 
 private UUID id;
 private LocalDate reporDate;

 @Enumerated(EnumType.STRING)
 private ReportStatus status;

 private String iteamDescription;
 private String lostLocation;

 @ManyToOne
 @JoinColumn(name = "owner_id")
 private  Owner owner;

 @ManyToOne
 @JoinColumn(name = "desk_officer_id")
 private DeskOfficer deskOfficer;

 public LostReport(){

 }


}
