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
	private String userId;
	private String contentId;
	private String location;
	private String tag;	
	private String startDate;
	private String endDate;
	private String publishedDate;
}
