package com.trip.snapshot.dto;

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
	private String tag;	
	private String startDate;
	private String endDate;
	private String publishedDate;
}
