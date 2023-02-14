package com.whitelabellingSpringBoot.whitelabellingSpringBoot;

import com.whitelabellingSpringBoot.whitelabellingSpringBoot.entity.WhitelabelStyling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WhitelabelRepository extends JpaRepository<WhitelabelStyling, Long> {
    @Query(value = "SELECT * FROM whitelabel_styling WHERE user_id=:id", nativeQuery = true)
    Optional<WhitelabelStyling> findByUserId(@Param("id") Long id);
}
