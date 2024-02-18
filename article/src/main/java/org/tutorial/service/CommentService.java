package org.tutorial.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.tutorial.pojo.Comment;

public interface CommentService {

	void saveComment(Comment comment);

	void updateComment(Comment comment);

	void deleteCommentById(String id);

	List<Comment> findCommentList();

	Comment findCommentById(String id);

	Page<Comment> findCommentListByParentId(String parentId, int page, int size);

	void updateCommentLikenum(String id);
}
