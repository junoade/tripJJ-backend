package com.trip.qna;

import lombok.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardQnaDto {

	private int articleNo;
	private String userId; // fk
	private String title;
	private String content;
	private int hit;
	private String publishedDate;
	private List<ReplyDto> replies;
	
}