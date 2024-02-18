package org.tutorial.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.tutorial.pojo.Comment;
import org.tutorial.repository.CommentRepository;
import org.tutorial.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepository commentRepository;

	// 使用MongoDB特有的CRUD
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void saveComment(Comment comment) {
		// 如果需要自定義主鍵，可以在這裡指定主鍵；否則MongoDB會自動生成主鍵
		// 也可以設置一些默認初始值...
		commentRepository.save(comment);
	}

	@Override
	public void updateComment(Comment comment) {
		commentRepository.save(comment);
	}

	@Override
	public void deleteCommentById(String id) {
		commentRepository.deleteById(id);
	}

	@Override
	public List<Comment> findCommentList() {
		return commentRepository.findAll();
	}

	@Override
	public Comment findCommentById(String id) {
		return commentRepository.findById(id).get();
	}

	@Override
	public Page<Comment> findCommentListByParentId(String parentId, int page, int size) {
		return commentRepository.findByParentid(parentId, PageRequest.of(page - 1, size));
	}

	@Override
	public void updateCommentLikenum(String id) {
		// 查詢物件，Criteria可以一直接下去...
		Query query = Query.query(Criteria.where("_id").is(id));
		
		// 更新物件
		Update update = new Update();
		
		// 局部更新，相當於$set
		// update.set(key,value)
		
		// 遞增$inc
		// update.inc("likenum",1);
		update.inc("likenum");
		
		// 參數1：查詢物件
		// 參數2：更新物件
		// 參數3：集合的名字或實體類的類型Comment.class
		mongoTemplate.updateFirst(query, update, "comment");
	}
}
