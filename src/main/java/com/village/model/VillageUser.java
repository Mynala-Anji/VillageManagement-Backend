package com.village.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class VillageUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank private String name;
    @Email @NotBlank @Column(unique = true, nullable = false) private String email;
    private String phone;
    @NotBlank @Column(unique = true, nullable = false) private String username;
    @Column(nullable = false) private String password;
    @Column(nullable = false) private String role = "USER";
    @Column(nullable = false) private String status = "ACTIVE";
    @Column(unique = true, nullable = false) private String familyId;
    @Lob @Column(columnDefinition = "LONGTEXT") private String profilePhoto;

    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getName(){return name;} public void setName(String v){name=v;}
    public String getEmail(){return email;} public void setEmail(String v){email=v;}
    public String getPhone(){return phone;} public void setPhone(String v){phone=v;}
    public String getUsername(){return username;} public void setUsername(String v){username=v;}
    public String getPassword(){return password;} public void setPassword(String v){password=v;}
    public String getRole(){return role;} public void setRole(String v){role=v;}
    public String getStatus(){return status;} public void setStatus(String v){status=v;}
    public String getFamilyId(){return familyId;} public void setFamilyId(String v){familyId=v;}
    public String getProfilePhoto(){return profilePhoto;} public void setProfilePhoto(String v){profilePhoto=v;}
}
