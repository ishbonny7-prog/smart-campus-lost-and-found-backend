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

   public int getDeskNumber(){
      return deskNumber;

   }
   public void setDeskNumber(int deskNumber){
      this.deskNumber= deskNumber;
   }

   public String getName(){
      return name;
   }

   public void setName(String name){
      this.name = name;
   }



}
