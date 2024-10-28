package com.aiiagcu.air.repository;

import com.aiiagcu.air.entity.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {


}
