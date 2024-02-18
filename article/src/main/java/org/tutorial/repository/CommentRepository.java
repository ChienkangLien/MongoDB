package org.tutorial.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.tutorial.pojo.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {

	Page<Comment> findByParentid(String parentid, Pageable pageable);
}
