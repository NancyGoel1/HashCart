package com.example.hashcartapp.repository;

import com.example.hashcartapp.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

  /*  @Query("select c from comment c where c.advertisementId= :advertisementId")
    public List<CommentDTO> getCommentsByAdvertisementId(Long advertisementId);*/
}
