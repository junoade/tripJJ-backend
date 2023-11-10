package com.trip.qna;

import lombok.*;

import java.lang.reflect.Member;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Reply {

	private int replyNo;
	private String comment; 
	private String publishedDate;
	private int articleNo;			// fk
	private String userId;			// fk
	
}