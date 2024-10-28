package com.aiiagcu.air.repository;

import com.aiiagcu.air.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RecruitRepository extends JpaRepository<Recruit, Long>, JpaSpecificationExecutor<Recruit> {
    @Query("SELECT r FROM Recruit r WHERE r.startAt <= :currentDateTime AND r.endAt >= :currentDateTime " +
            "AND r.isOpen = true AND r.isArchived = false")
    List<Recruit> findOpenRecruits(@Param("currentDateTime") LocalDateTime currentDateTime);
}
