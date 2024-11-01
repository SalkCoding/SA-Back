package com.aiiagcu.air.repository;

import com.aiiagcu.air.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM post ORDER BY RAND() LIMIT 5", nativeQuery = true)
    List<Post> findRandomPosts();

    long count();
}
