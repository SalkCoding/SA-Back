package com.aiiagcu.air.repository;

import com.aiiagcu.air.entity.ApplicationFormQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationFormQuestionRepository extends JpaRepository<ApplicationFormQuestion, Long> {
}
