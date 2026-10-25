package smart_campus_lostand_found.repository;

import smart_campus_lostand_found.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;
import java.util.List;
import smart_campus_lostand_found.entity.DeskOfficer;



public interface OwnerRepository extends JpaRepository<Owner,UUID> {
    List<Owner> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);

    @Query("SELECT DISTINCT r.owner FROM LostReport r WHERE r.deskOfficer.id= :id")
    List <Owner> findOwnerByDeskOfficerId(UUID id);
}
