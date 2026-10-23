package smart_campus_lostand_found.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "return_record")
public class ReturnRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String itemName;
    private String verificationNote;
    private int quantity;
    private LocalDate returDate;

    @ManyToOne
    @JoinColumn(name ="lost_report_id")
    private LostReport lostReport;

    public ReturnRecord(){
        
    }
    
}
