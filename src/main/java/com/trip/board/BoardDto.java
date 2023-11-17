package com.trip.board;

import lombok.*;

import java.lang.reflect.Member;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardDto {

	private int articleNo;
	private String userId; // fk
	private String title;
	private String content;
	private int hit;
	private String publishedDate;
	private String visitedDate;
	private String visitedArea; // 추후 따로 빼주기

	// private Member user;
}