package com.village.repository;

import com.village.model.VillageUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<VillageUser, Long> {
    Optional<VillageUser> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByFamilyId(String familyId);
    long countByRole(String role);
}
