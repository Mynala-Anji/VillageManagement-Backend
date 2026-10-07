package com.village.controller;
import com.village.model.Announcement;
import com.village.repository.AnnouncementRepository;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController @RequestMapping("/api/announcements")
public class AnnouncementController {
 private final AnnouncementRepository repo;
 public AnnouncementController(AnnouncementRepository repo){this.repo=repo;}
 private void admin(Authentication a){if(a.getAuthorities().stream().noneMatch(x->x.getAuthority().equals("ROLE_ADMIN")))throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Admin only");}
 @GetMapping public List<Announcement> all(){return repo.findAllByOrderByCreatedAtDesc();}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public Announcement create(@Valid @RequestBody Announcement in,Authentication a){admin(a);in.setId(null);return repo.save(in);}
 @PutMapping("/{id}") public Announcement update(@PathVariable Long id,@Valid @RequestBody Announcement in,Authentication a){admin(a);Announcement old=repo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Announcement not found"));old.setTitle(in.getTitle());old.setMessage(in.getMessage());old.setEventDate(in.getEventDate());old.setType(in.getType());return repo.save(old);}
 @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id,Authentication a){admin(a);if(!repo.existsById(id))throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Announcement not found");repo.deleteById(id);}
}
