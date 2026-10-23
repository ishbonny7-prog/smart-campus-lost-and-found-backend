package smart_campus_lostand_found.entity;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.*;

@Entity
@Table(name = "item_category")

public class ItemCategory{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @ManyToMany(mappedBy = "itemCategories")
    @JsonIgnore
    private List<DeskOfficer> DeskOfficers = new ArrayList<>();

    public ItemCategory(){

        
    }
   
}




