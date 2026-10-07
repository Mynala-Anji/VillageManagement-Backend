package com.village.repository;
import com.village.model.Family; import org.springframework.data.jpa.repository.*; import org.springframework.data.repository.query.Param; import java.util.*;
public interface FamilyRepository extends JpaRepository<Family,Long>{
 interface Stats{Long getTotalFamilies();Long getTotalMembers();Long getMale();Long getFemale();Long getChildren();}
 boolean existsByFamilyId(String v); boolean existsByFamilyIdAndIdNot(String v,Long id); List<Family> findByUserId(Long userId); long countByStatus(String status); void deleteAllByUserId(Long userId);
 @Query("select f from Family f where lower(f.familyId) like lower(concat('%',:q,'%')) or lower(f.houseNumber) like lower(concat('%',:q,'%')) or lower(f.headName) like lower(concat('%',:q,'%')) or lower(f.occupation) like lower(concat('%',:q,'%')) order by f.familyId") List<Family> search(@Param("q") String q);
 @Query("select count(f) as totalFamilies, coalesce(sum(f.totalMembers),0L) as totalMembers, coalesce(sum(f.maleCount),0L) as male, coalesce(sum(f.femaleCount),0L) as female, coalesce(sum(f.childrenCount),0L) as children from Family f") Stats stats();
}
