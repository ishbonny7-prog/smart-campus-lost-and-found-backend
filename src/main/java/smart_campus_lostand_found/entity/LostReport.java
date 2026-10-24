package smart_campus_lostand_found.entity;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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

 private LocalDate reportDate;
 private String itemDescription;

 public LostReport(){

 }

 public UUID getId() {
    return id;
 }

 public void setId(UUID id) {
    this.id = id;
 }

 public LocalDate getReporDate() {
    return reporDate;
 }

 public void setReporDate(LocalDate reporDate) {
    this.reporDate = reporDate;
 }

 public ReportStatus getStatus() {
    return status;
 }

 public void setStatus(ReportStatus status) {
    this.status = status;
 }

 public String getIteamDescription() {
    return iteamDescription;
 }

 public void setIteamDescription(String iteamDescription) {
    this.iteamDescription = iteamDescription;
 }

 public String getLostLocation() {
    return lostLocation;
 }

 public void setLostLocation(String lostLocation) {
    this.lostLocation = lostLocation;
 }

 public Owner getOwner() {
    return owner;
 }

 public void setOwner(Owner owner) {
    this.owner = owner;
 }

 public DeskOfficer getDeskOfficer() {
    return deskOfficer;
 }

 public void setDeskOfficer(DeskOfficer deskOfficer) {
    this.deskOfficer = deskOfficer;
 }

 public LocalDate getReportDate() {
    return reportDate;
 }

 public void setReportDate(LocalDate reportDate) {
    this.reportDate = reportDate;
 }

 public String getItemDescription() {
    return itemDescription;
 }

 public void setItemDescription(String itemDescription) {
    this.itemDescription = itemDescription;
 }
 


}
