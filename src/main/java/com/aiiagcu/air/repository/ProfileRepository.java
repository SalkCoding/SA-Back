package com.aiiagcu.air.repository;

import com.aiiagcu.air.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Long>, JpaSpecificationExecutor<Profile> {
    @Query("SELECT r FROM Profile r WHERE r.startAt <= :currentDateTime AND r.endAt >= :currentDateTime " +
            "AND r.isOpen = true AND r.isArchived = false")
    List<Profile> findOpenProfiles(@Param("currentDateTime") LocalDateTime currentDateTime);
}
