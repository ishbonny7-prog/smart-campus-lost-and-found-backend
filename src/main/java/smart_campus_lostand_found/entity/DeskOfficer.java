package smart_campus_lostand_found.entity;

import jakarta.persistence.*;
import java.util.*;
@Entity
@Table(name ="desk_officer")
public class DeskOfficer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;



    private String firstName;

    private String lastName;
    private String email;

    @ManyToOne
    @JoinColumn(name = "found_desk_id",nullable = true)
    private FoundDesk foundDesk;

    @ManyToMany
    @JoinTable(name = "desk_officer_item_category",
    joinColumns = @JoinColumn(name = "desk_officer_id"),
    inverseJoinColumns = @JoinColumn(name="item_category_id"))

    private List<ItemCategory> itemCategories = new ArrayList<>();

    public DeskOfficer(){

    }

    public UUID getid(){ return id;} 

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public FoundDesk getFoundDesk() {
        return foundDesk;
    }

    public void setFoundDesk(FoundDesk foundDesk) {
        this.foundDesk = foundDesk;
    }

    public List<ItemCategory> getItemCategories() {
        return itemCategories;
    }





    
}
