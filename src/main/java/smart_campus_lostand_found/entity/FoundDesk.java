package smart_campus_lostand_found.entity;

import jakarta.persistence.*;
import java.util.UUID;
@Entity
@Table(name = "found_desk")
public class FoundDesk {
   @Id
   @GeneratedValue(strategy = GenerationType.UUID) 
   private UUID id;

   private int deskNumber;
   private String name;

   public FoundDesk(){
    
   }



}
