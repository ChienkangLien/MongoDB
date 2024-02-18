package org.tutorial.pojo;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Document(collection = "comment") // 若此類名小寫有mapping到collection名，也可省略
//複合索引
//@CompoundIndex( def = "{'userid': 1, 'nickname': -1}")
@Data
public class Comment {
	// 主鍵標識，該屬性的值會自動對應mongodb的主鍵字段"_id"，如果該屬性名就叫id，也可省略
	@Id
	private String id;
	// @Field("content") 該屬性對應mongodb的字段的名字，如果一致，也可省略
	@Field("content")
	private String content;
	private Date publishtime;
	// 添加了一個單字段的索引
	@Indexed
	private String userid;
	private String nickname;
	private LocalDateTime createdatetime;
	private Integer likenum;
	private Integer replynum;
	private String display;
	private String parentid;
	private String articleid;

}
