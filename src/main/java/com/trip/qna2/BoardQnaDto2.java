package com.trip.qna2;

import java.util.List;

import lombok.*;

@Getter
@Setter
@ToString
//@ApiModel(value = "BoardQnaDto : 게시글정보", description = "게시글의 상세 정보를 나타낸다.")
public class BoardQnaDto2 {

	private int articleNo;
	private String userId; // fk
	private String title;
	private String content;
	private int hit;
	private String publishedDate;
	private List<FileInfoDto2> fileInfos;
	private List<ReplyDto2> replyInfos;
	
}
