package com.village.model;
import jakarta.persistence.*; import jakarta.validation.constraints.*;
@Entity @Table(name="families")
public class Family {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank(message="Family ID is required") @Column(unique=true,nullable=false) private String familyId;
 private String houseNumber=""; @NotBlank(message="Head of family is required") private String headName;
 @Min(0) private int maleCount; @Min(0) private int femaleCount; @Min(0) private int childrenCount; private int totalMembers;
 private boolean ownsLand; @PositiveOrZero private double landArea; private String occupation="";
 @Column(nullable=false, columnDefinition="varchar(20) default 'PENDING'") private String status="PENDING"; private Long userId; @Column(length=1000) private String rejectionReason;
 @PrePersist @PreUpdate void calculate(){totalMembers=maleCount+femaleCount+childrenCount;if(!ownsLand)landArea=0;}
 public Long getId(){return id;} public void setId(Long v){id=v;} public String getFamilyId(){return familyId;} public void setFamilyId(String v){familyId=v;} public String getHouseNumber(){return houseNumber;} public void setHouseNumber(String v){houseNumber=v;} public String getHeadName(){return headName;} public void setHeadName(String v){headName=v;} public int getMaleCount(){return maleCount;} public void setMaleCount(int v){maleCount=v;} public int getFemaleCount(){return femaleCount;} public void setFemaleCount(int v){femaleCount=v;} public int getChildrenCount(){return childrenCount;} public void setChildrenCount(int v){childrenCount=v;} public int getTotalMembers(){return totalMembers;} public void setTotalMembers(int v){totalMembers=v;} public boolean isOwnsLand(){return ownsLand;} public void setOwnsLand(boolean v){ownsLand=v;} public double getLandArea(){return landArea;} public void setLandArea(double v){landArea=v;} public String getOccupation(){return occupation;} public void setOccupation(String v){occupation=v;} public String getStatus(){return status;} public void setStatus(String v){status=v;} public Long getUserId(){return userId;} public void setUserId(Long v){userId=v;} public String getRejectionReason(){return rejectionReason;} public void setRejectionReason(String v){rejectionReason=v;}
}
