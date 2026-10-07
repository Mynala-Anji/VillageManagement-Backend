package com.village.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name="announcements")
public class Announcement {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank private String title;
 @NotBlank @Column(length=3000) private String message;
 private String eventDate;
 private String type = "NOTICE";
 @Column(nullable=false) private LocalDateTime createdAt;
 @PrePersist void created(){ if(createdAt==null) createdAt=LocalDateTime.now(); }
 public Long getId(){return id;} public void setId(Long v){id=v;}
 public String getTitle(){return title;} public void setTitle(String v){title=v;}
 public String getMessage(){return message;} public void setMessage(String v){message=v;}
 public String getEventDate(){return eventDate;} public void setEventDate(String v){eventDate=v;}
 public String getType(){return type;} public void setType(String v){type=v;}
 public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
}
