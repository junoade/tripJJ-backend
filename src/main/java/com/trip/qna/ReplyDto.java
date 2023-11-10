package com.trip.qna;

import lombok.*;

import java.lang.reflect.Member;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReplyDto {

	private int replyNo;
	private String comment; 
	private String publishedDate;
	private int articleNo;			// fk
	private String userId;			// fk
	
}