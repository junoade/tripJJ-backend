	package com.trip.qna;

import lombok.*;

import java.lang.reflect.Member;

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
	
}