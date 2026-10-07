package com.village.service;

import com.village.model.VillageUser;
import com.village.model.Family;
import com.village.repository.UserRepository;
import com.village.repository.FamilyRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private final UserRepository users;
    private final FamilyRepository families;
    private final PasswordEncoder encoder;
    public UserService(UserRepository users, FamilyRepository families, PasswordEncoder encoder){this.users=users;this.families=families;this.encoder=encoder;}

    @Transactional
    public VillageUser register(Map<String,String> in){
        String username=req(in,"username"), email=req(in,"email"), name=req(in,"name");
        if(users.existsByUsername(username)) throw new ResponseStatusException(HttpStatus.CONFLICT,"Username already exists");
        if(users.existsByEmail(email)) throw new ResponseStatusException(HttpStatus.CONFLICT,"Email already exists");
        String password=req(in,"password");
        long next=users.countByRole("USER")+1;
        String familyId;
        do { familyId=String.format("FAM-%04d", next++); } while(users.existsByFamilyId(familyId));
        VillageUser u=new VillageUser(); u.setName(name); u.setEmail(email); u.setPhone(in.getOrDefault("phone",""));
        u.setUsername(username); u.setPassword(encoder.encode(password)); u.setRole("USER"); u.setStatus("ACTIVE"); u.setFamilyId(familyId);
        u=users.save(u);
        Family f=new Family(); f.setFamilyId(familyId); f.setHouseNumber(""); f.setHeadName(name); f.setStatus("PENDING"); f.setUserId(u.getId()); families.save(f);
        return u;
    }
    private String req(Map<String,String> m,String k){String v=m.get(k);if(v==null||v.isBlank())throw new ResponseStatusException(HttpStatus.BAD_REQUEST,k+" is required");return v.trim();}
    public VillageUser byUsername(String username){return users.findByUsername(username).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));}
    public Map<String,Object> safe(VillageUser u){Map<String,Object> m=new HashMap<>();m.put("id",u.getId());m.put("name",u.getName());m.put("email",u.getEmail());m.put("phone",u.getPhone());m.put("username",u.getUsername());m.put("role",u.getRole());m.put("status",u.getStatus());m.put("familyId",u.getFamilyId());m.put("profilePhoto",u.getProfilePhoto());return m;}
}
