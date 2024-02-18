package org.tutorial.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.tutorial.pojo.Comment;
import org.tutorial.service.CommentService;

@SpringBootTest
public class CommentServiceImplTest {
	@Autowired
	private CommentService commentService;

	@Test
	public void testSaveComment() {
		for (int i = 1; i <= 20; i++) {
			Comment comment = new Comment();
			comment.setArticleid("1000" + i);
			comment.setContent("測試" + i);
			comment.setUserid("1005");
			comment.setNickname("小王");
			comment.setCreatedatetime(LocalDateTime.now());
			comment.setDisplay("1");
			comment.setLikenum(0);
			comment.setReplynum(0);
			comment.setParentid(i % 2 == 0 ? "1" : "2");
			commentService.saveComment(comment);
		}
	}

	@Test
	public void testFindAll() {
		List<Comment> list = commentService.findCommentList();
		System.out.println(list);
	}

	@Test
	public void testFindCommentById() {
		Comment comment = commentService.findCommentById("65d1d07a1a78321112d2ede2");
		System.out.println(comment);
	}

	@Test
	public void testfindCommentListByParentId() {
		Page<Comment> page = commentService.findCommentListByParentId("1", 2, 3);
		System.out.println(page.getTotalElements());
		System.out.println(page.getContent());
	}

	@Test
	public void testUpdateCommentLikenum() {
		commentService.updateCommentLikenum("65d1d07a1a78321112d2ede2");
	}
}
