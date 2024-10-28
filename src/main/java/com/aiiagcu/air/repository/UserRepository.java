package com.aiiagcu.air.repository;


import com.aiiagcu.air.entity.Cell;
import com.aiiagcu.air.entity.Level;
import com.aiiagcu.air.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select u from User u where u.studentNumber=:studentNumber and u.password=:password")
    User findByStudentNumberAndPassword(@Param("studentNumber")String studentNumber, @Param("password")String password);

    //모든 사용자를 조회하는 쿼리
    @Query("select u from User u where u.studentNumber =:studentNumber")
    List<User> findAllById(@Param("studentNumber")String studentNumber);

    //List<User> findByUsername(String username);

    //댓글 레포지토리에서 사용
    Optional<User> findByStudentNumber(String studentNumber);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.cell = :cell where u.id = :id")
    void updateUserCell(@Param(value="cell") Cell cell, @Param(value="id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.level = :level where u.id = :id")
    void updateUserLevel(@Param(value="level")Level level, @Param(value="id") Long id);

    Optional<User> findByname(String name);
    
    Boolean existsByStudentNumber(String studentNumber);
//    Boolean existsByEmail (String email);
}
