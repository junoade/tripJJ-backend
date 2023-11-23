package com.trip.snapshot.dto;

import com.trip.member.MemberDto;
import com.trip.search.dto.AttractionInfo_DB;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Snapshot {
	private Integer id;
	private String userId;
	private Integer contentId; // 관광지정보 테이블 pk
	private String content;
	private String tag;	
	private String startDate;
	private String endDate;
	private String publishedDate;
	
	// mybatis - association 관계
	private MemberDto member; // 회원 정보 테이블
	private AttractionInfo_DB attractionInfo; // 관광지 정보 테이블
}
