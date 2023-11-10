package com.trip.qna2;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDto2 {

	private int replyNo;
	private String comment; 
	private String publishedDate;
	private int articleNo;			// fk
	private String userId;			// fk
	
}
