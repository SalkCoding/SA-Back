package com.aiiagcu.air.repository;

import com.aiiagcu.air.entity.ApplicationForm;
import com.aiiagcu.air.entity.Recruit;
import com.aiiagcu.air.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationFormRepository extends JpaRepository<ApplicationForm, Long> {
    List<ApplicationForm> findByUser(User user);
    List<ApplicationForm> findByRecruit(Recruit recruit);
    List<ApplicationForm> findByUserAndRecruit(User user, Recruit recruit);
}
