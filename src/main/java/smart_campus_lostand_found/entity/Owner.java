package smart_campus_lostand_found.entity;
import jakarta.persistence.*;
import java.util.UUID;
@Entity
@Table(name = "owner")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String campusId;

    private String email;
    private String phone;
    public Owner(){

    }

    
    
}
